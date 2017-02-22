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
    <link rel="stylesheet" type="text/css" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/articles.css">
    <title>Frank | 所有文章</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<jsp:include page="//headers/navheader.jsp"></jsp:include>
	  <div class="am-g">
          <div class="am-u-md-8">
			<c:forEach begin="0" items="${all_articles }" var="article" step="1" varStatus="index">
				<div class="am-panel am-panel-default">
                <div class="am-panel-hd">
                  <h4 class="am-panel-title" data-am-collapse="{parent: '#article-list', target: '#${article.article.articleid }'}">
                    ${article.article.title }
                    <i class="am-fr am-icon-angle-right am-icon-sm am-icon-fw"></i>
                  </h4>
                </div>
                <div id="${article.article.articleid }" class="am-panel-collapse am-collapse">
                  <div class="am-panel-bd">
                      <div class="am-g">
                      	<div class="am-u-sm-12">
                      		${article.content }
                      	</div>
                      </div>
                      <div class="read-complete"><a href="javascript:;" class="am-link-read-article">阅读全文</a></div>
                  </div>
                </div>
              </div>
			</c:forEach>
			</div>
			<jsp:include page="//content/rightnav.jsp"></jsp:include>
	  </div>
      <jsp:include page="//footer/footer.jsp"></jsp:include>
  </body>
  <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/index.css">
    <script type="application/javascript">
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/articles.script.js')
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/header.script.js')
    </script>
</html>
