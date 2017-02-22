<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String query = request.getParameter("query");
	if (null != query)
		query = URLDecoder.decode(query, "utf-8");
 %>

<!doctype html>
<html>
<body><%=query %></body>
<script type="text/javascript">
console.log('<%=request.getParameter("ref") %>')
</script>
</html>