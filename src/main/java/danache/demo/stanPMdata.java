package danache.demo;

import java.util.HashMap;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

public class stanPMdata {
	private String stannName;
	static HashMap<String, String> Mapmap ;
	public stanPMdata(String _stanname) {
		// TODO Auto-generated constructor stub
		stannName = _stanname;
		Mapmap = new HashMap();
	}
	public stanPMdata(){};
	public static void judgeInsert(String time, String data){
		if (!Mapmap.containsKey(time)){
			Mapmap.put(time, data);
		}
	}
	public static  HashMap<String, String> getHashMap(){
		return Mapmap;
	}
	public String getStanname(){
		return stannName;
	}
	
	public void setStanName(String _stanname){
		stannName = _stanname;
	}
	
	

}
