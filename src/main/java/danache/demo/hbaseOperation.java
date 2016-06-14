package danache.demo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.math.util.BigReal;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Hash;

import danache.demo.*;

public class hbaseOperation {

	public static Configuration configuration;
	static {
		configuration = HBaseConfiguration.create();

	}
	public final static String datatimequalifier = "datatime";
	public final static String stannamequalifier = "stanname";
	public final static String citynamequalifier = "cityname";
	public final static String pm25qualifier = "pm25";
	public final static String pm10qualifier = "pm10";
	public final static String timestamp = "timestamp";
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws IOException {
		
	}

	public static void QueryAll(String tableName) throws IOException {
		HTablePool pool = new HTablePool(configuration, 1000);
		HTable table = new HTable(configuration, tableName);
		try {
			ResultScanner rs = table.getScanner(new Scan());
			for (Result r : rs) {
				System.out.println("获得到rowkey:" + new String(r.getRow()));
				for (KeyValue keyValue : r.raw()) {
					System.out.println(
							"列：" + new String(keyValue.getFamily()) + "====值:" + new String(keyValue.getValue()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<dataStruct> QueryByRowkey(String city, String stanname, String begintime,String endtime) throws IOException, ParseException {
		List<dataStruct> result = new ArrayList<>();

		Configuration conf = HBaseConfiguration.create();

		HTable table = new HTable(configuration, "pmdata");

		Scan s = new Scan();
		String beginRowkey = city + stanname+sdf.parse(sdf.format( Long.parseLong(begintime))).getTime();
		String endRowkey = city + stanname+sdf.parse(sdf.format( Long.parseLong(endtime))).getTime();
		s.setStartRow(Bytes.toBytes(beginRowkey));

		s.setStopRow(Bytes.toBytes(endRowkey));

		ResultScanner rs = table.getScanner(s);

		for (Result r : rs) {
			dataStruct tmp = new dataStruct();
			boolean inRange = true;
			for (KeyValue keyValue : r.raw()) {
				String value = new String(keyValue.getQualifier());
				switch (value) {
				case datatimequalifier:
					String datetime = new String(keyValue.getValue());
					
					Date date1 = sdf.parse(datetime);
					
				
					Date begindate = sdf.parse(sdf.format( Long.parseLong(begintime)));
					Date enddate = sdf.parse(sdf.format( Long.parseLong(endtime)));
					if (date1.getTime()< begindate.getTime()
							|| date1.getTime() >enddate.getTime()){
						inRange = false;
					}
					tmp.setdataTime(datetime);
					break;
				case stannamequalifier:
					tmp.setstanname(new String(keyValue.getValue()));
					break;
				case citynamequalifier:
					tmp.setCityname(new String(keyValue.getValue()));
					break;
				case pm25qualifier:
					tmp.setpm25(new String(keyValue.getValue()));
					break;
				case pm10qualifier:
					tmp.setpm10(new String(keyValue.getValue()));
					break;
				case timestamp:
					tmp.setTimestamp(new String(keyValue.getValue()));
					break;
				default:
					break;
				}
			}
			if (inRange){
			result.add(tmp);
			}
		}

		table.close();
		return result;
	}



	public static List<stanPMdata> stanDataList(List<dataStruct> allData) {
		List<stanPMdata> result = new ArrayList<>();
		Iterator alldataIt = allData.iterator();

		while (alldataIt.hasNext()) {
			dataStruct singledata = (dataStruct)alldataIt.next();
			String stanname = singledata.getstanname();
			stanPMdata newdata = new stanPMdata();
			boolean flag = false;
			Iterator itresult = result.iterator();
			while (itresult.hasNext()) {
				 stanPMdata tmp = (stanPMdata)itresult.next();
				if (tmp.getStanname().equals(stanname))
				{
					flag = true;
					break;
				}
			}
			if(!flag){
				newdata = new stanPMdata(stanname);
				result.add(newdata);
			}
			
			stanPMdata tmpENd = new stanPMdata();
			Iterator itend = result.iterator();
			while (itend.hasNext()) {
				 stanPMdata tmp2 = (stanPMdata)itend.next();
				if (tmp2.getStanname().equals(stanname))
				{
					tmpENd = tmp2;
					break;
				}
			}
			tmpENd.judgeInsert(singledata.getdataTime(), singledata.getpm25());
			//result.add(tmpENd);
		}
		
		return result;
	}

	public static HashMap<String, String> mapsort(HashMap<String, String> datamap){
		HashMap< String, String> res = new HashMap<>();
		Collection<String> keyset= datamap.keySet();  
	     List<String> list = new ArrayList<String>(keyset);  
	       
	     //对key键值按字典升序排序  
	     Collections.sort(list);  
	       
	     for (int i = 0; i < list.size(); i++) {  
	         res.put(list.get(i),datamap.get(list.get(i)));  
	     }  
	     return res;
	}
	
}