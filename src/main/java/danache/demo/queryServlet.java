package danache.demo;

import java.awt.font.ImageGraphicAttribute;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.generated.regionserver.region_jsp;
import org.jdom2.JDOMException;

import com.sun.tools.classfile.StackMapTable_attribute.append_frame;

import danache.demo.imageJson;
import net.sf.json.JSONArray;

public class queryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = request.getSession();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset:utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String cityname = request.getParameter("city");
		String times1 = request.getParameter("beginDate");
		String times2 = request.getParameter("endDate");
		List<JSONArray> jsonl = new ArrayList<>();
		List<String> stanList;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); ;  
		    Long ti1=Long.valueOf(times1);
		    Long ti2=Long.valueOf(times2);
		    java.util.Date date1 = null;
		    java.util.Date date2 = null;
		    try {
				date1=sdf.parse(sdf.format(ti1));
				date2=sdf.parse(sdf.format(ti2));
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		String dt1 = sdf.format(date1);
		String dt2 = sdf.format(date2);
		
		try {
			stanList = exact.getStanList(cityname);

			Iterator it = stanList.iterator();

			while (it.hasNext()) {

				String stan = (String) it.next();
				String beginRowkey = (String) cityname + stan + times1;
				String endRowkey = (String) cityname + stan + times2;
				List<dataStruct> data = hbaseOperation.QueryByRowkey(cityname, stan, times1, times2);
				List<stanPMdata> stanDataList = hbaseOperation.stanDataList(data);
				Iterator itstan = stanDataList.iterator();
				while (itstan.hasNext()) {
					stanPMdata pMdata = (stanPMdata) itstan.next();
					HashMap<String, String> datamap = pMdata.getHashMap();
					/*
					Iterator iteratorRes = datamap.entrySet().iterator();
					while(iteratorRes.hasNext()){
						
						
						Map.Entry entry = (Map.Entry) iteratorRes.next();
						Object key = entry.getKey();
						Object val = entry.getValue();
						System.out.print("time : " + key);
						
					}*/
					if (!pMdata.getStanname().equals("南三环")) {

						String fileName = LineChartDemo1.generatePieChart((stan+"站点： "+dt1 + " ~ "+dt2) + " PM2.5数值变化图", datamap, session,
								new PrintWriter(out));
						String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;

						HashMap<String, String> tmp_datamap = stanDataList.get(stanDataList.size() - 1).getHashMap();
						Collection<String> keyset = tmp_datamap.keySet();
						List<String> list = new ArrayList<String>(keyset);

						// 对key键值按字典升序排序
						Collections.sort(list);
						String value = datamap.get(list.get(list.size() - 1));
						imageJson imageJson = new imageJson(stan, fileName, graphURL, value);
						JSONArray jsonArray = JSONArray.fromObject(imageJson);
						jsonl.add(jsonArray);
					}
				}

			}
			out.println(jsonl);
			out.flush();
			out.close();
		} catch (JDOMException | ParseException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}