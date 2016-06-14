package danache.demo;

public class imageJson {
	private String stanname;
	private String filename;
	private String graphURL;
	private String latest;
	public imageJson(String _stan, String _file, String _graph, String _latest) {
		// TODO Auto-generated constructor stub
		stanname = _stan;
		filename = _file;
		graphURL = _graph;
		latest = _latest;
	}
	public String getStanname(){
		return stanname;
	}
	public String getGraphURL(){
		return graphURL;
	}
	public String getFilename(){
		return filename;
	}
	public String getlatest(){
		return latest;
	}
	

}
