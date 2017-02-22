<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String context = request.getServletPath().substring(1);
%>
<footer data-am-widget="footer" class="am-footer am-footer-default am-footer-gray"
	data-am-footer="{ }">
	<div class="am-footer-miscs white-foreground">

		<p>
			本站由 <a href="https://www.github.com/Frank17" class="link" title="Frank"
				target="_blank" class="">Frank</a> 提供技术支持
		</p>
		<p>CopyRight©2016 franksite.site.</p>
		<p>京ICP备13033158</p>
		<p></p>
		<div class="share-group">
			<a href="javascript:;" class="am-btn-icon am-icon-github am-monospace">Github</a>
			<a href="javascript:;" class="am-btn-icon am-icon-wechat am-monospace">WeChat</a>
			<a href="javascript:;" class="am-btn-icon am-icon-weibo am-monospace">Weibo</a>
			<a href="javascript:;" class="am-btn-icon am-secondary am-icon-qq">QQ</a>
			<c:choose>
				<c:when test="${username!=null }">
					<a href="javascript:;" class="am-btn-icon am-icon-unlock">Admin</a>
				</c:when>
				<c:otherwise>
					<a href="login?ref=${current_url }" class="am-btn-icon am-icon-lock">Admin</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</footer>
