<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%
response.setDateHeader("Last-Modified", new Date().getTime());
response.setDateHeader("Expires", 0);
response.setHeader("Pragma", "no-cache");
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Cache-Control", "no-store");
response.addHeader("Cache-Control", "must-revalidate");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <jsp:include page="//headers/metas.jsp"></jsp:include>
    <jsp:include page="//headers/framework.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/type.css">
    <title>Frank | 文章分类</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<jsp:include page="//headers/navheader.jsp"></jsp:include>
	  <div class="am-g">
          
          <div class="am-u-md-8">
              <div class="am-g am-content">
              	<c:forEach begin="0" items="${all_types }" var="type">
<!--               		<li class="am-type-block"> -->
<%--                       <div class="am-type-content"><a href="javascript:;" target-id=${type.typeid } class="am-link-typedetail">${type.title }</a></div> --%>
<!--                   	</li> -->

					<div class="am-u-sm-12 am-u-md-4 am-u-lg-3">
						<div class="am-panel am-panel-primary ">
							<p class="am-panel-hd"><a href="javascript:;" target-id=${type.typeid } style="color: #fff;" class="am-link-typedetail">${type.title }</a></p>
							<div class="am-panel-bd">${type.note }</div>
						</div>
					</div>
					
              	</c:forEach>
              </div>
          </div>
          
          <jsp:include page="//content/rightnav.jsp"></jsp:include>
	  </div>
      <jsp:include page="//footer/footer.jsp"></jsp:include>
  </body>
  <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/index.css">
    <script type="application/javascript">
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/types.script.js')
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/header.script.js')
    </script>
</html>
