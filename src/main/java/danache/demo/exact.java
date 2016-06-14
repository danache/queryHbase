package danache.demo;

import java.net.URL;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import java.io.*;
import java.util.*;

public class exact {
	public static void main(String[] args) throws Exception {
		getProList();

	}

	public static List<String> getCityList(String path) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("citys.xml");
		Element root = doc.getRootElement();
		XPath xpath = XPath.newInstance("//province[@name='" + path + "']/city");
		List list = xpath.selectNodes(root);
		List<String> result = new ArrayList<String>();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {

			Element item = (Element) iter.next();
			
			String name = item.getAttributeValue("name");
			result.add(name);

		}
		return result;
	}

	public static List<String> getStanList(String path) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("citys.xml");
		Element root = doc.getRootElement();
		String xp = "//city[@name='" + path + "']/stan";
		XPath xpath = XPath.newInstance(xp);
		List list = xpath.selectNodes(root);
		List<String> result = new ArrayList<String>();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {

			Element item = (Element) iter.next();

			String name = item.getChildTextTrim("name");

			result.add(name);

		}
		return result;
	}

	public static List<String> getProList() throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("citys.xml");
		Element root = doc.getRootElement();
		String xp = "//province";
		XPath xpath = XPath.newInstance(xp);
		List list = xpath.selectNodes(root);
		List<String> result = new ArrayList<String>();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Element item = (Element) iter.next();
			String name = item.getAttributeValue("name");
			result.add(name);

		}
		return result;
	}
}
