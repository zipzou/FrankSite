<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<header class="am-topbar">
  <h1 class="am-topbar-brand">
    <a href="#">FrankSite</a>
  </h1>
	<c:if test="${null!=username }"><input id="username-hidden" type="hidden" value="${username }"> </c:if>
  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
          data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
      class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav">
      <li id="index"><a href="javascript:;">首页</a></li>
      <li id="types"><a href="javascript:;">分类</a></li>
      <li id="all-article"><a href="javascript:;" id="link-articles-all">所有文章</a></li>
      <c:if test="${null!=username }">
	      <li class="am-dropdown" data-am-dropdown>
	        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">管理<span class="am-icon-caret-down"></span>
	        </a>
	        <ul class="am-dropdown-content">
	          <li class="am-dropdown-header">用户账户</li>
	          <li><a href="javascript:;" id="am-link-logout">注销</a></li>
	          <li class="am-dropdown-header">博客管理</li>
	          
	          <li><a href="javascript:;" id="link-publish-atricles">发表文章</a></li>
	          <li><a href="javascript:;" id="link-settings">偏好设置</a></li>
	        </ul>
	      </li>
      </c:if>
      
    </ul>

    <form class="am-topbar-form am-topbar-left am-form-inline am-topbar-right" role="search" id="search-form" action="article/searchKeyword" method="post">
      <div class="am-form-group">
        <input type="text" class="am-form-field am-input-sm" placeholder="搜索文章" id="input-keyword" name="keyword">
      </div>
      <button type="button" class="am-btn am-btn-default am-btn-sm" id="btn-search">搜索</button>
    </form>

  </div>
</header>
