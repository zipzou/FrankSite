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
    <link rel="stylesheet" type="text/css" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/profile.css">
    <title>偏好设置</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<jsp:include page="//headers/navheader.jsp"></jsp:include>
	  <div class="am-g">
		<div class="am-u-lg-9 am-u-md-7 right-bar">
			<div class="am-form-description">
				<p>在这里，您可以针对您自己的博客进行一系列个性化设置，包括博客文章分类、账号安全等的管理和设置。</p>
			</div>
			<div class="am-tabs" data-am-tabs>
				<ul class="am-tabs-nav am-nav am-nav-tabs">
					<li class="am-active"><a href="#basic-settings">基本设置</a></li>
					<li><a href="#account-safe-settings">账户与安全</a></li>
				</ul>

				<div class="am-tabs-bd">
					<div class="am-modal am-modal-alert" tabindex="-1" id="alert-result">
						<div class="am-modal-dialog">
							<div class="am-modal-hd">结果</div>
							<div class="am-modal-bd"></div>
							<div class="am-modal-footer">
								<span class="am-modal-btn">确定</span>
							</div>
						</div>
					</div>
					<div class="am-modal am-modal-loading am-modal-no-btn"
						tabindex="-1" id="modal-submitting">
						<div class="am-modal-dialog">
							<div class="am-modal-hd">正在载入...</div>
							<div class="am-modal-bd">
								<span class="am-icon-spinner am-icon-spin"></span>
							</div>
						</div>
					</div>
					
					<input type="hidden" value="${blog.blogid }" id="blog">
					<div class="am-tab-panel am-fade am-in am-active" id="basic-settings">
                        
						<form class="am-form am-form-horizontal" onsubmit="return false;">
                            <div class="am-form-group basic-tip am-u-sm-offset-1 am-u-sm-10">在这里，可以进行一些基本的博客设置，再更改每一项后，系统将自动为你提交最新结果，请等待所有更新被应用再关闭页面！</div>
							<div class="am-form-group">
								<label for="doc-ipt-3" class="am-u-lg-2 am-u-md-4 am-u-sm-5 am-form-label">博客标语</label>
								<div class="am-u-lg-10 am-u-md-8 am-u-sm-7">
									<input type="text" class="am-form-field" id="blog-slogan" placeholder="不多于120字" value="${blog.slogan }">
									
								</div>
							</div>

							<div class="am-form-group">
								<label for="doc-ipt-pwd-2" class="am-u-lg-2 am-u-md-4 am-u-sm-5 am-form-label">首页文章数</label>
								<div class="am-u-lg-10 am-u-md-8 am-u-sm-7">
									<input type="number" id="max-capacity" placeholder="最大数量" value="${blog.indexmax }">
								</div>
							</div>
							
							<div class="am-form-group am-form-inline">
                                <label class="am-u-lg-2 am-u-md-4 am-u-sm-5 am-form-label">文章类别</label>
                                <div class="am-u-lg-10 am-u-md-8 am-u-sm-7">
                                    <select
                                        data-am-selected="{btnSize: 'sm', btnStyle: 'secondary', maxHeight: 140, dropUp: 1}" id="type-selector">
                                        <c:forEach items="${all_types }" var="type">
                                        	<option value=${type.typeid }>${type.title }</option>
                                        </c:forEach>
                                    </select>
                                </div>
							</div>
                            
                            <div class="am-form-group" id="am-collapse-parent">
                                <div class="am-u-sm-10 am-u-sm-offset-2">
                                    <a href="javascript:;" id="delete-type">删除所选分类</a>
                                    <a href="javascript:;" data-am-collapse="{parent: '#am-collapse-parent', target: '#add-type-panel'}">添加分类</a>
                                </div>
                            </div>
                            
                            <div class="am-form-group am-collapse" id="add-type-panel">
                                <div class="am-u-sm-10 am-u-sm-offset-1">
                                    <div class="am-panel am-panel-default">
                                        <div class="am-panel-bd">
                                            <div class="am-form" >
                                                <label for="type-name" class="am-form-label">新类名</label>
                                                <br>
                                                <input class="am-form-field" type="text" id="type-name" placeholder="新分类名称">
                                                <p>
                                                <label for="type-name" class="am-form-label">分类信息</label>
                                                <br>
                                                <textarea class="am-form-field" id="type-note" placeholder="分类信息" id="type-note" style="resize: none; height: 120px;" ></textarea>
                                                <p>
                                                <div class="am-form-group" >
                                                    <div class="am-u-sm-6 am-u-sm-offset-4">
                                                        <button class="am-btn am-btn-primary" id="btn-add-type">确认新增</button>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
						</form>
					</div>
					<div class="am-tab-panel am-fade" id="account-safe-settings">
                        <div class="am-form-group basic-tip am-u-sm-offset-1 am-u-sm-10">在这里，可以进行一些有关账户安全类的设置，部分安全设置的更改将在验证后完成</div>
						<form class="am-form am-form-horizontal" onsubmit="return false;">
                            
                            <div class="am-form-group">
                                <label class="am-form-label am-u-lg-2 am-u-md-4 am-u-sm-5" for="username">用户名</label>
                                <div class="am-u-lg-10 am-u-md-8 am-u-sm-7">
                                    <input type="text" class="am-form-field am-monospace" disabled value="frank">
                                </div>
                            </div>
                            
                            <div class="am-form-group">
                                <label class="am-form-label am-u-lg-2 am-u-md-4 am-u-sm-5">手机号码</label>
                                <div class="am-u-lg-10 am-u-md-8 am-u-sm-7">
                                    <input class="am-form-field" type="tel" placeholder="手机号码,辅助验证使用，更改可能需要验证" disabled>
                                </div>
                            </div>
                            
                            <div class="am-form-group">
                                <div class="am-u-sm-10 am-u-sm-offset-2">
                                    <a href="#change-phone-panel" data-am-collapse>更改手机号码</a>
                                </div>
                            </div>
                            <!--  -->
                            <div class="am-form-group am-collapse" id="change-phone-panel">
                                <div class="am-u-sm-10 am-u-sm-offset-1">
                                    <div class="am-panel am-panel-default">
                                        <div class="am-panel-bd">
                                            <div class="am-form am-form-horizontal" >
                                            	<label class="am-form-label">新手机号码</label>
                                                <p>
                                                <input type="tel" placeholder="新手机号码" class="am-form-field">
                                                
                                                <p>
                                                <label class="am-form-label">验证码</label>
                                                <input type="text" placeholder="手机收到的短信验证码" class="am-form-field">
                                                <p>
                                                
                                                <button class="am-btn am-btn-default">确认并发送验证短信</button>
                                                <p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="am-form-group">
                                <label class="am-form-label am-u-lg-2 am-u-md-4 am-u-sm-5">邮件地址</label>
                                <div class="am-u-lg-10 am-u-md-8 am-u-sm-7">
                                    <input class="am-form-field" type="email" placeholder="安全电子邮件，更改后可能需要您进行验证" disabled>
                                </div>
                            </div>
                            
                            <div class="am-form-group">
                                <div class="am-u-sm-10 am-u-sm-offset-2">
                                    <a href="#change-email-panel" data-am-collapse>更改电子邮件</a>
                                </div>
                            </div>
                            
                            <div class="am-form-group am-collapse" id="change-email-panel">
                                <div class="am-u-sm-10 am-u-sm-offset-1">
                                    <div class="am-panel am-panel-default">
                                        <div class="am-panel-bd">
                                            <div class="am-form am-form-horizontal" onsubmit="return false;">
                                            	<label class="am-form-label">新邮件地址</label>
                                                <p>
                                                <input type="email" placeholder="新的电子邮件地址" class="am-form-field">
                                                
                                                <p>
                                                <button class="am-btn am-btn-default">确认并发送验证邮件</button>
                                                <p>
                                            
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="am-form-group">
                                <div class="am-u-sm-10 am-u-sm-offset-2">
                                    <a href="#change-pwd-panel" data-am-collapse>修改密码</a>
                                </div>
                            </div>
                            
                            <div class="am-form-group am-collapse" id="change-pwd-panel">
                                <div class="am-u-sm-10 am-u-sm-offset-1">
                                    <div class="am-panel am-panel-default">
                                        <div class="am-panel-bd">
                                            <div class="am-form am-form-horizontal" onsubmit="return false;">
                                            	<label class="am-form-label">新密码</label>
                                            	<p>
                                            	<input type="password" placeholder="新的用户密码" class="am-form-field">
                                                
                                                <p>
                                                <label class="am-form-label">重复密码</label>
                                                <p>
                                                <input type="password" placeholder="重复新的密码以确认" class="am-form-field">
                                                
                                                <p>
                                                <button class="am-btn am-btn-default">确认并修改</button>
                                            
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </form>
					</div>
				</div>
			</div>
		</div>
	</div>
      <jsp:include page="//footer/footer.jsp"></jsp:include>
  </body>
  <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/index.css">
    <script type="application/javascript">
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/profile.settings.js')
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/header.script.js')
    </script>
</html>
