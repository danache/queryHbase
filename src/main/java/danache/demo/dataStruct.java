package danache.demo;

import org.apache.hadoop.hbase.generated.regionserver.region_jsp;

public class dataStruct {
	private String cityname;
	private String dataTime;
	private String pm10;
	private String pm25;
	private String stanname;
	private String timestamp;
	public dataStruct(String _cityname, String _dataTime, String _pm10, String _pm25, String _stanname, String _timestamp) {
		cityname = _cityname;
		dataTime = _dataTime;
		pm10 = _pm10;
		pm25 = _pm25;
		stanname = _stanname;
		timestamp = _stanname;
	}
	public dataStruct(){};
	public String getCityname(){
		return cityname;
	}
	public String getdataTime(){
		return dataTime;
	}
	public String getstanname(){
		return stanname;
	}
	public String getTimestamp(){
		return timestamp;
	}
	public String getpm25(){
		return pm25;
	}
	public String getpm10(){
		return pm10;
	}
	
	public void setCityname(String _city){
		cityname = _city;
	}
	public void setdataTime(String _datatime){
		dataTime = _datatime;
	}
	public void setstanname(String _stanname){
		stanname = _stanname;
	}
	public void setTimestamp(String _timestamp){
		timestamp = _timestamp;
	}
	public void setpm25(String _pm25){
		pm25 = _pm25;
	}
	public void setpm10(String _pm10){
		pm10 = _pm10;
	}
	
	public void printData(){
		System.out.print("cityname: "+cityname);
		System.out.print("stanname: "+stanname);
		System.out.print("dataTime: "+cityname);
		System.out.print("pm25: "+pm25);
		System.out.print("pm10: "+pm10);
		System.out.println("timestamp: "+timestamp);
	}
	
	

}
