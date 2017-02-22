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
    <title>${current_article.article.title } | Frank</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/editor/css/editormd.min.css" />
    <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/highlight/styles/agate.css" />
    <script src="http://static.jxufehelper.com/FrankSiteAssets/editor/src/editormd.js"></script>
    <script src="http://static.jxufehelper.com/FrankSiteAssets/assets/highlight/highlight.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/article.page.css">
  </head>
  
  <body>
  	<jsp:include page="//headers/navheader.jsp"></jsp:include>
	  <div class="am-g">
          <div class="am-u-sm-12 am-u-lg-8 am-u-lg-offset-2 am-u-md-10 am-u-md-offset-1">
          	
              <div class="am-article  am-margin-horizontal" target-id="${current_article.article.articleid }">
                  <div class="am-article-hd"><h1>${current_article.article.title }</h1><p class="am-article-meta">frank</p></div>
                  <hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
                  
                  
                  
                  <div class="am-article-bd am-text-sm am-text-break">
                      <div id="category"></div>
                      <div class="am-article-content">${current_article.content }</div>
                      </div>
                    <div class="am-g am-article-footer">
                        <div class="am-fr">
                            <span><a href="javascript:;" id="link-comment"><i class="am-icon-comment icon-label"></i>发表评论</a></span>
                    	</div>
						<ul class="am-comments-list">
							<c:forEach begin="0" items="${comment_list }" var="commentbody">
								<c:choose>
									<c:when test="${null==commentbody.parentComment }">
										<!-- 评论 -->
										<article class="am-comment"> <a href="javascript:;">
											<img src="http://static.jxufehelper.com/FrankSiteAssets/assets/ico/logo@2x.png" alt=""
												class="am-comment-avatar" width="48" height="48" />
											</a>
						
											<div class="am-comment-main">
												<header class="am-comment-hd" target-id="${commentbody.comment.commentid }"> <!--<h3 class="am-comment-title">评论标题</h3>-->
													<div class="am-comment-meta">
														<a href="#link-to-user" class="am-comment-author">${commentbody.comment.nickname }</a> 评论于
														<time datetime="${commentbody.dateStr }"
															title="${commentbody.dateStr }">${commentbody.dateStr }</time>
													</div>
													<c:if test="${null!=username }"><div class="am-comment-actions"><a href="javascript:;" class="am-link-reply" title="回复评论"><i class="am-icon-pencil"></i></a><a href="javascript:;" class="am-link-del" title="删除评论"><i class="am-icon-close"></i></a></div></c:if>
													
												</header>
						
												<div class="am-comment-bd">
													<div class="am-g">
														<div class="am-u-sm-12">${commentbody.comment.content }</div>
													</div>
												</div>
											</div>
										</article>
									</c:when>
									<c:otherwise>
										<!-- 回复 -->
										<article class="am-comment am-comment-flip"> <a
											href="javascript:;"> <img src="http://static.jxufehelper.com/FrankSiteAssets/assets/ico/logo@2x.png"
												alt="" class="am-comment-avatar" width="48" height="48" />
											</a>
						
											<div class="am-comment-main">
												<header class="am-comment-hd" target-id="${commentbody.comment.commentid }"> 
													<div class="am-comment-meta">
														<a href="#link-to-user" class="am-comment-author">${commentbody.comment.nickname }</a> 回复于
														<time datetime="${commentbody.dateStr }"
															title="${commentbody.dateStr }">${commentbody.dateStr }</time>
													</div>
													<c:if test="${null!=username }"><div class="am-comment-actions"><a href="javascript:;" class="am-link-del" title="删除回复"><i class="am-icon-close"></i></a></div></c:if>
												</header>
												<div class="am-comment-bd">
													<div class="am-g">
														<div class="am-u-sm-12">${commentbody.comment.content }</div>
													</div>
													<blockquote><a href="javascript:;" target-id="${commentbody.parentComment.commentid }">@${commentbody.parentComment.nickname }</a>:&nbsp;&nbsp;${commentbody.parentComment.content }</blockquote>
												</div>
											</div>
										</article>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					<div class="am-g">
						<div class="am-u-sm-12">
							<label class="am-form-label" for="nickname"></label>
							<input class="am-form-field" id="nickname" name="nickname" type="text" placeholder="输入你的昵称">
							<div id="editormd" style="height: 160px">
		              			<textarea style="display:none;"></textarea>
		              		</div>
						</div>
					</div>
                  
                  <div class="am-u-sm-10 am-margin-bottom">
                      <a class="am-btn am-btn-primary am-btn-sm" id="btn-submit">提交评论</a>
                  </div>
			</div>
              </div>
          </div>
          </div>
      <jsp:include page="//footer/footer.jsp"></jsp:include>
  </body>
  <link rel="stylesheet" href="http://static.jxufehelper.com/FrankSiteAssets/assets/css/index.css">
  <script type="application/javascript">
  		
  		$('pre code').each(function(i, block) {
				hljs.configure({
					tabReplace: '    ', // 4 spaces
			    });
			    hljs.highlightBlock(block);
			});
  
  	$('code').each(function(i, block) {
  		hljs.configure({
  			tabReplace : '    ', // 4 spaces
  		});
  		if ('pre'.toUpperCase().trim() !== $(block).parent().get(0).tagName.toUpperCase().trim()) {
  			$(block).addClass('inline-code')
  			hljs.highlightBlock(block);
  		}
  
  	});
  
  	jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/readarticle.script.js')
        jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/header.script.js')
        
        var editor
        
        $(function() {
	           	editor = editormd("editormd", {
	            path : "http://static.jxufehelper.com/FrankSiteAssets/editor/lib/", // Autoload modules mode, codemirror, marked... dependents libs path
				imageUpload : true,
    			codeFold : true,
    			height: 560,
    			imageUploadURL : "http://static.jxufehelper.com/ImageUpload/img/upload",
    			//syncScrolling : false,
    			saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
    			searchReplace : true,
    			watch : false,                // 关闭实时预览
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
    			imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp", ".JPG", ".JPGE", ".PNG", ".GIF", ".BMP" ],
    			crossDomainUpload : true,
    			uploadCallbackURL : "<%=basePath%>callback"
			});
	    });
  </script>
    <script type="text/javascript">
 $(document).ready(function(){
      $("h2,h3,h4,h5,h6").each(function(i,item){
        var tag = $(item).get(0).localName;
        $(item).attr("id","wow"+i);
        $("#category").append('<a class="new'+tag+'" href="#wow'+i+'">'+$(this).text()+'</a></br>');
        $(".newh2").css("margin-left",0);
        $(".newh3").css("margin-left",20);
        $(".newh4").css("margin-left",40);
        $(".newh5").css("margin-left",60);
        $(".newh6").css("margin-left",80);
      });
 });
</script>
</html>
