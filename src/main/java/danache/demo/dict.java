package danache.demo;

import java.util.HashMap;

import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.htrace.fasterxml.jackson.databind.PropertyNamingStrategy;

public class dict {
	final public static String[] ProName = {
			"北京","上海","天津","重庆","安徽","福建","甘肃","广西","贵州","陕西","山西","海南","青海","宁夏",
			"内蒙古","辽宁","吉林","浙江","云南","西藏","四川","山东","河北","黑龙江","河南","新疆","湖北","湖南",
			"江苏","江西","广州"
			};
	private String[] pinyin = {"beijing","shanghai","tianjin","chongqing","anhui","fujian",
			"ganshu","guangxi","guizhou","shanxi3","shanxi1","hainan",
			"qinghai","ningxia","neimenggu","liaoning","jilin","zhejiang",
			"yunnan","xizang","sichuan","shandong","hebei","heilongjiang",
			"henan","xinjiang","hubei","hunan","jiangsu","jiangxi","guangzhou"};
	public static HashMap<String, String> Mapmap ;

	private HashMap<String, String> init(){
		HashMap<String, String> Mapmap = new HashMap();
		for (int i = 0;i < ProName.length;i++){
			Mapmap.put(ProName[i], pinyin[i]);
		}
		return Mapmap;
	}
	public dict() {
		// TODO Auto-generated constructor stub
		Mapmap = init();
	}
}
