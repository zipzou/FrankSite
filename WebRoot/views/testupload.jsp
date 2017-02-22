<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
InputStream inputStream = request.getInputStream();
byte []bytes = new byte[1024];
BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
BufferedImage image = ImageIO.read(inputStream);
File file = new File(request.getServletContext().getRealPath("img"));
if (!file.exists()) {
	file.mkdirs();
}
try {

ImageIO.write(image, new Date().getTime() + ".jpg", file);
}
catch (Exception e) {
	System.err.println(e);
}
 %>
