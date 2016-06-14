package danache.demo;

public class gpsJson {
	private String stanname;
	private String gpsLat;
	private String gpsLog;
	public gpsJson(String _stan, String _Lat, String _Log) {
		// TODO Auto-generated constructor stub
		stanname = _stan;
		gpsLat = _Lat;
		gpsLog = _Log;
	}
	public String getStanname(){
		return stanname;
	}
	public String getgpsLog(){
		return gpsLog;
	}
	public String getgpsLat(){
		return gpsLat;
	}
	

}
