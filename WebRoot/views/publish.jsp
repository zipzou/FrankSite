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

<%
//request.getSession().setAttribute("admin", "frank");
//request.getSession().invalidate();
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <jsp:include page="//headers/metas.jsp"></jsp:include>
    <jsp:include page="//headers/framework.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/editor.css">
    <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/editor/css/editormd.min.css" />
    <script src="http://static.jxufehelper.com/FrankSiteAssets/editor/src/editormd.js"></script>
  	<link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/index.css">
    <title>FrankSite | 发表文章</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
      <jsp:include page="//headers/navheader.jsp"></jsp:include>
	  <div class="am-g">
          <div class="am-u-md-12 am-publish-body">
              <div class="am-editor-title am-g">
                  <div class="am-title-container am-u-lg-10 am-u-md-8 am-u-sm-7">
                      <a href="javascript:;" id="blog-title">输入您文章的标题</a>
                  </div>
              </div>
              <div id="editormd">
              	<textarea style="display:none;"></textarea>
              </div>
			<form class="am-form am-form-horizontal">
				<div class="am-form-group">
					<label for="doc-ipt-3">分类</label><select
						data-am-selected="{btnSize: 'sm', btnStyle: 'secondary', maxHeight: 200}" id="type-selection" style="overflow: scroll;max-height: 120px">
						<c:forEach begin="0" items="${publish_types }" var="type">
							<option value="${type.typeid }">${type.title }</option>
						</c:forEach>
					</select>
				</div>
				
			</form>
			<div class="am-g">
				<div class="am-u-sm-6 am-u-lg-3 am-u-md-4">
<!-- 					<label class="am-checkbox"> <input type="checkbox" value="" -->
<!-- 						data-am-ucheck> 置顶该文章 -->
<!-- 					</label> -->
<!--                     <label class="am-checkbox"> <input type="checkbox" value="" -->
<!-- 						data-am-ucheck> 允许访客评论 -->
<!-- 					</label> -->
				</div>
			</div>
			<div class="am-g am-u-sm-centered am-u-md-centered am-u-md-centered">
                <a class="am-btn am-btn-primary" id="btn-publish">发表博客</a>
			</div>
		</div>
	  </div>
      <jsp:include page="//footer/footer.jsp"></jsp:include>
  </body>
    <script type="application/javascript">
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/editor.script.js')
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/header.script.js')
        
        var editor
        
        $(function() {
	           	editor = editormd("editormd", {
	            path : "http://static.jxufehelper.com/FrankSiteAssets/editor/lib/", // Autoload modules mode, codemirror, marked... dependents libs path
				imageUpload : true,
    			codeFold : true,
    			imageUploadURL : "http://static.jxufehelper.com/ImageUpload/img/upload",
    			onload: function() {
    				this.config({
                        tocm : true,
                        tocContainer : "",
                        tocDropdown   : false
                    });
    			},
    			//syncScrolling : false,
    			saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
    			searchReplace : true,
    			//watch : false,                // 关闭实时预览
    			//htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启    
    			//toolbar  : false,             //关闭工具栏
    			//previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
    			emoji : true,
    			taskList : true,
    			tocm : true, // Using [TOCM]
    			tex : true, // 开启科学公式TeX语言支持，默认关闭
    			flowChart : true, // 开启流程图支持，默认关闭
    			sequenceDiagram : true, // 开启时序/序列图支持，默认关闭,
    			//dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
    			//dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
    			//dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
    			//dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
    			//dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
    			imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
    			crossDomainUpload : true,
    			uploadCallbackURL : "<%=basePath%>callback",
    		});
    		
    		
	
	        /*
	        // or
	        var editor = editormd({
	            id   : "editormd",
	            path : "../lib/"
	        });
	        */
	    });
    </script>
</html>
