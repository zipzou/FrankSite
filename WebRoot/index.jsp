<%@page import="site.franksite.service.business.ArticleBusinessImpl"%>
<%@page import="site.franksite.service.business.ArticleBusiness"%>
<%@page import="site.franksite.pojo.AuthorEntity"%>
<%@page import="site.franksite.pojo.PojoBuilder"%>
<%@page import="site.franksite.pojo.ArticleEntity"%>
<%@page import="site.franksite.service.SessionAttribute"%>
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
    <jsp:include page="headers/metas.jsp"></jsp:include>
    <jsp:include page="headers/framework.jsp"></jsp:include>
    <title>Frank | 个人博客 </title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
  </head>

<body>
	<jsp:include page="headers/navheader.jsp"></jsp:include>
	<div class="am-g am-g-fixed blog-g-fixed">
		<div class="am-u-md-8">
			<c:forEach begin="0" end="${max_capacity }" step="1" items="${index_all_articles }" var="article">
			
				<article class="blog-main">
				<h3 class="am-article-title blog-title">
					<a href="page/article/${article.article.articleid }" class="am-link-article-detail_" target-id="${article.article.articleid }" title="${article.article.title }">${article.article.title }</a>
				</h3>
				<h4 class="am-article-meta blog-meta">
					由 <a href="javascript:;">frank</a> 发表于： ${article.publishdate }
				</h4>
	
				<div class="am-g">
					<div class="am-u-sm-12 am-article-shortcut">
						${article.article.shortcut }
					</div>
					<div class="am-u-sm-12 article-footer am-article-footer">
						<div class="am-fr">
		                	<span><i class="am-icon-calendar icon-label"></i>${article.publishdate }</span>
		                    <span><i class="am-icon-eye icon-label"></i>阅读(<a>${article.readTimes }</a>)</span>
		                    <span><i class="am-icon-comment icon-label"></i>评论(<a>${article.commentcount }</a>)</span>
						</div>
					</div>
				</div>
				</article>
				<hr class="am-article-divider blog-hr">
			</c:forEach>
			
		</div>

		<jsp:include page="//content/rightnav.jsp"></jsp:include>
	</div>
	<jsp:include page="//footer/footer.jsp"></jsp:include>
</body>
<link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/index.css">
  <script type="application/javascript">
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/index.script.js')
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/header.script.js')
  </script>
</html>
