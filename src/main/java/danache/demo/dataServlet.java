package danache.demo;

import java.awt.font.ImageGraphicAttribute;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import com.sun.tools.classfile.StackMapTable_attribute.append_frame;

import danache.demo.imageJson;
import net.sf.json.JSONArray;


public class dataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		List<JSONArray> jsonl = new ArrayList<>();
		try {
			jsonl = getStanList();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset:utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.println(jsonl);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	public static List<JSONArray>  getStanList() throws JDOMException, IOException {
		List<JSONArray> tmp = new ArrayList<>();
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("beijing.xml");
		Element root = doc.getRootElement();
		String xp = "//city/site";
		XPath xpath = XPath.newInstance(xp);
		List list = xpath.selectNodes(root);
		List<String> result = new ArrayList<String>();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {

			Element item = (Element) iter.next();

			String name = item.getChildTextTrim("name");
			String gpsLat = item.getChildTextTrim("gpsLat");
			String gpsLog = item.getChildTextTrim("gpsLog");
			gpsJson gpsJson = new gpsJson(name, gpsLat, gpsLog);
			
			JSONArray jsonArray = JSONArray.fromObject(gpsJson);
			tmp.add(jsonArray);

		}
		return tmp;
	}
	public static void main(String[] args) throws Exception {
		getStanList();

	}

}