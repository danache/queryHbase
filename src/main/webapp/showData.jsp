<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="danache.demo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据展示</title>
</head>
<body>
	<%
request.setCharacterEncoding("GBK");


String cityname = request.getParameter("city");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String dt1 = request.getParameter("beginDate");
String dt2 = request.getParameter("endDate");
Date date1 = sdf.parse(dt1);

Date date2 = sdf.parse(dt2);
Long times1 = date1.getTime();
Long times2 = date2.getTime();
List<String> stanList = exact.getStanList(cityname);
Iterator it = stanList.iterator();
%>

<%
		while(it.hasNext()){
			
			String stan = (String)it.next();
			String beginRowkey = (String)cityname + stan + times1;
			String endRowkey = (String)cityname + stan + times2;
			out.println(stan+"站点： "+dt1 + " ~ "+dt2);
			%>
			<br>
			<%
			
			List<dataStruct> data = hbaseOperation.QueryByRowkey(cityname ,stan ,String.valueOf(times1),String.valueOf(times2));
			List<stanPMdata> stanDataList = hbaseOperation.stanDataList(data);
			
			Iterator itstan = stanDataList.iterator();
			
			while (itstan.hasNext()){
				stanPMdata pMdata = (stanPMdata)itstan.next();
				HashMap<String, String> datamap = pMdata.getHashMap();
				/*
				Iterator iter = datamap.entrySet().iterator();
				while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();

				}
				*/
			if (!pMdata.getStanname().equals("南三环")){
			       
		         String fileName=LineChartDemo1.generatePieChart((stan+"站点： "+dt1 + " ~ "+dt2)+" PM2.5数值变化图",datamap,session,new PrintWriter(out));
		         String graphURL=request.getContextPath()+"/servlet/DisplayChart?filename="+fileName;
					
		%>
	 <img src="<%= graphURL %>" width=800 height=600 border=1 usemap="#<%=fileName %>">  
	 <br>
			<%
			}
			}
		}
	%>
	<br>
	

</body>
</html>