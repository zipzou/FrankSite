<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String context = request.getServletPath().substring(1);

String ref = request.getHeader("Referer");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Frank | 博客登录</title>
    <jsp:include page="//headers/framework.jsp"></jsp:include>
    <jsp:include page="//headers/metas.jsp"></jsp:include>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/login.css">
  </head>
  
  <body>
	<div class="header">
		<div class="am-g">
			<h1>登录到博客</h1>
			<p>
				以管理者身份，登录到博客系统，并管理博客<br />管理博客、分类、文章、评论等
			</p>
		</div>
		<hr />
	</div>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<h3>登录</h3>
			<hr>
			<br />
			<form class="am-form" id="form-login" ref="<%=ref %>">
				<div class="am-form-group am-monospace">
					<label for="username">用户名:</label> <input type="text"
						name="username" id="username" autocomplete="off">
				</div>
				<div class="am-form-group am-monospace">
					<label for="password">密码:</label> <input type="password"
						name="password" id="password" autocomplete="off">
				</div>
				<div class="am-form-group am-monospace">
					<label class="am-form-label" for="validateCode">验证码:</label> <input type="text"
						name="validateCode" id="validateCode" class="am-form-field" autocomplete="off">
				</div>

				<img class="am-img-responsive" alt="validate-code" width="200px"
					height="50px" id="vaidationCodeImage" /> <br />
				<div class="am-cf">
					<input type="submit" id="login" value="登 录"
						class="am-btn am-btn-primary am-btn-sm am-fl"> <input
						type="submit" id="forget" value="忘记密码 ^_^? "
						class="am-btn am-btn-default am-btn-sm am-fr">
				</div>
			</form>
			<hr>
			<p style="text-align: center;">© 2016 FrankSite, Inc.</p>
		</div>
	</div>
	<script type="text/javascript">
      	jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/login.script.js?key=' + Math.random(), function(){
      	})
      </script>
  </body>
</html>
