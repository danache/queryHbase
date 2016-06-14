
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>
<%@ page import="danache.demo.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果显示</title>
</head>
<body>
	<%
		request.setCharacterEncoding("GBK");
		

		String cityname = request.getParameter("select1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(request.getParameter("beginDate"));
		 
		Date date2 = sdf.parse(request.getParameter("endDate"));
		Long times1 = date1.getTime();
		Long times2 = date2.getTime();
		

	%>
	<br>
	<%

	%>


	<%
		
		out.println("输入城市为： " + cityname);
		%>
	<br>
	<%
		String cityIndex = new dict().Mapmap.get(cityname);  
		  
		
		List<String> cityList = exact.getCityList(cityIndex);
		Iterator it = cityList.iterator();
		%>


	<%
		while(it.hasNext()){
			
			String city = (String)it.next();
			%>
	<a href="showData.jsp?d1=<%=times1%>&d2=<%=times2%>&c=<%=city%>"> <%
			out.println(city);
			%>
	</a>
	<br>
	<%
		}
	%>
	</a>
	<br>




</body>
</html>