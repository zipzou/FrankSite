<%@page import="site.franksite.service.SessionAttribute"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%request.getRequestDispatcher("page/author/frank").forward(request, response);request.getSession()
.setAttribute(SessionAttribute.CurrentUrl, request.getServletPath());%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="//headers/metas.jsp"></jsp:include>
    <jsp:include page="//headers/framework.jsp"></jsp:include>
    <title>Frank | 个人博客 </title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
  </head>
  </html>