<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="am-u-md-4 blog-sidebar">
	<div class="am-panel-group">
		<section class="am-panel am-panel-default">
			<div class="am-panel-hd">热门文章</div>
			<div class="am-panel-bd">
				<ul class="am-list blog-list">
					<c:forEach items="${readtime_articles }" var="article">
						<li><a href="page/article/${article.articleid }" data-target-id="${article.articleid }" class="article-read-link">${article.title }</a></li>
					</c:forEach>
				</ul>
			</div>
		</section>
		<section class="am-panel am-panel-default">
			<div class="am-panel-hd">热门评论</div>
			<div class="am-panel-bd">
                <ul class="am-list blog-list">
                    <c:forEach items="${comment_articles }" var="article">
                        <li><a href="page/article/${article.articleid }" data-target-id="${article.articleid }" class="article-read-link">${article.title }</a></li>
                    </c:forEach>
			     </ul>
            </div>
		</section>

		<section class="am-panel am-panel-default about-me">
			<div class="am-panel-hd">关于作者</div>
			<div class="am-panel-bd">
				<div class="am-panel-bd">
					<img alt="face" src="http://jxufehelper.com/FrankSiteAssets/assets/i/face.jpg" class="am-img-responsive" width="50%" style="margin-left: 25%;">
					<p><span><i class="am-icon-user">&nbsp;&nbsp;作者：</i></span>邹智鹏  / Frank</p>
					<p><span><i class="am-icon-send">&nbsp;&nbsp;邮件地址：</i></span>zouzhipeng.1@163.com</p>
					<p><span><i class="am-icon-github">&nbsp;&nbsp;GitHub主页：</i></span><a href="http://www.github.com/Frank17" target="_blank">Frank17</a></p>
					<p><span><i class="am-icon-qq">&nbsp;&nbsp;QQ：</i></span><a href="http://www.github.com/Frank17" target="_blank">294183657</a></p>
					<p><span><i class="am-icon-home">&nbsp;&nbsp;CSDN：</i></span><a href="http://blog.csdn.net/zouzhipeng1717" target="_blank">Frank's CSDN Blog</a></p>
					<p>现就读于江西财经大学，攻读软件工程本科专业，隶属于<a href="http://www.jxufe.edu.cn" target="_blank">江西财经大学</a> <a href="http://software.jxufe.edu.cn" target="_blank">软件与通信工程学院</a>。</p>
					<p>致力于Java Web程序开发，熟悉Java、C/C++、Objective C等语言编程。期间已成功开发多款iOS APP：<a href="https://appsto.re/cn/7ILKcb.i" target="_blank">Hex Convert</a></p>
<!-- 					<a class="am-btn am-btn-success am-btn-sm" href="#">查看更多 →</a> -->
				</div>
			</div>
		</section>
	</div>
</div>
