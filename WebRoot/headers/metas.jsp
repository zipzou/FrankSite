<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no, email=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="description" content="Frank个人博客，个人技术博客"/>
<meta name="keywords" content="Frank Blog Site"/>
<meta name="author" content="frank, zouzhipeng.1@163.com"/>
<meta name="robots" content="all"/>

<!-- 不缓存  -->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<!-- iOS 设备 begin -->
<meta name="apple-mobile-web-app-title" content="FrankSite">
<!-- 添加到主屏后的标题（iOS 6 新增） -->
<meta name="apple-mobile-web-app-capable" content="yes"/>
<!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->
<meta name="apple-itunes-app" content="app-id=1116887227, affiliate-data=myAffiliateData, app-argument=myURL">
<!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
 
<!-- 启用 360 浏览器的极速模式(webkit) -->     
<meta name="renderer" content="webkit">

<!-- uc 强制竖屏 -->
<meta name="screen-orientation" content="portrait">
<!-- QQ 强制竖屏 -->
<meta name="x5-orientation" content="portrait">
<!-- UC 强制全屏 -->
<meta name="full-screen" content="yes">
<!-- QQ 强制全屏 -->
<meta name="x5-fullscreen" content="true">
<!-- UC 应用模式 -->
<meta name="browsermode" content="application">
<!-- QQ 应用模式 -->
<meta name="x5-page-mode" content="app">
<!-- windows phone 点击无高光 -->
<meta name="msapplication-tap-highlight" content="no">

<!-- iOS 图标 begin -->
<link rel="apple-touch-icon-precomposed"
	href="http://static.jxufehelper.com/FrankSiteAssets/assets/ico/logo.png" />
<!-- iPhone 和 iTouch，默认 57x57 像素，必须有 -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="http://static.jxufehelper.com/FrankSiteAssets/assets/ico/logo@2x.png" />
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://static.jxufehelper.com/FrankSiteAssets/assets/ico/logo@2x.png"/>
<!-- Retina iPhone 和 Retina iTouch，114x114 像素，可以没有，但推荐有 ->     <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/apple-touch-icon-144x144-precomposed.png"/>     <!-- Retina iPad，144x144 像素，可以没有，但推荐有 -->
<!-- iOS 图标 end -->
<!-- iOS 启动画面 begin -->
<link rel="apple-touch-startup-image" sizes="768x1004"
	href="http://static.jxufehelper.com/FrankSiteAssets/assets/i/launch-screen.png" />
<!-- iPad 竖屏 768 x 1004（标准分辨率） -->
<link rel="apple-touch-startup-image" sizes="1536x2008"
	href="http://static.jxufehelper.com/FrankSiteAssets/assets/i/launch-screen@2x.png" />
<!-- iPad 竖屏 1536x2008（Retina） -->
<link rel="apple-touch-startup-image" sizes="1024x748"
	href="http://static.jxufehelper.com/FrankSiteAssets//Default-Portrait-1024x748.png" />
<!-- iPad 横屏 1024x748（标准分辨率） -->
<link rel="apple-touch-startup-image" sizes="2048x1496"
	href="http://static.jxufehelper.com/FrankSiteAssets//splash-screen-2048x1496.png" />
<!-- iPad 横屏 2048x1496（Retina） -->
<link rel="apple-touch-startup-image" href="/splash-screen320x480.png" />
<!-- iPhone/iPod Touch 竖屏 320x480 (标准分辨率) -->
<link rel="apple-touch-startup-image" sizes="640x960"
	href="http://static.jxufehelper.com/FrankSiteAssets//splashscreen-640x960.png" />
<!-- iPhone/iPod Touch 竖屏 640x960 (Retina) -->
<link rel="apple-touch-startup-image" sizes="640x1136"
	href="http://static.jxufehelper.com/FrankSiteAssets//splashscreen-640x1136.png" />
<!-- iPhone 5/iPod Touch 5 竖屏 640x1136 (Retina) -->
<!-- iOS 启动画面 end -->
<!-- iOS 设备 end -->
<meta name="msapplication-TileColor" content="#000" />
<!-- Windows 8 磁贴颜色 -->
<meta name="msapplication-TileImage" content="icon.png" />
