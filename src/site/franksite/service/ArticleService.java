/**
 * 
 */
package site.franksite.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.DateUtil;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.body.ArticleBody;
import site.franksite.service.business.ArticleBusiness;
import site.franksite.service.business.ArticleBusinessImpl;
import site.franksite.service.business.CommentBusiness;
import site.franksite.service.business.CommentBusinessImpl;

/**
 * 文章服务
 * @author Frank
 *
 */
@Path("/article")
public class ArticleService {
	
	private final static Logger LOGGER = Logger.getLogger(ArticleService.class);
	
	private ArticleBusiness business = new ArticleBusinessImpl();
	
	@POST
	@Path("/publish")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object publishArticle(ArticleBody body, @Context HttpServletRequest request) {
		
		if (null == body) {
			LOGGER.warn("非法的参数信息！");
			return PojoBuilder.responseResult("非法的参数信息！");
		}
		
		// 验证是否超时登录
		if (null == request.getSession().getAttribute(SessionAttribute.LoginedUsername)) {
			LOGGER.warn("登录已超时，请重新登录！");
			return PojoBuilder.responseResult("登录已超时，请重新登录！");
		}
		
		body.getArticle().setUsername(request.getSession().getAttribute(SessionAttribute.LoginedUsername).toString().trim());
		
		// 生成摘要
		String content = body.getContent();
		ArticleEntity article = body.getArticle();
		article.setArticleid(PojoBuilder.generateTimeStringId());
		File outFile = new File(request.getServletContext().getRealPath("articles"));
		if (!outFile.exists()) {
			outFile.mkdirs();
		}
		
		OutputStream stream;
		try {
			stream = new FileOutputStream(new File(outFile, article.getArticleid() + ".html"));
		} catch (FileNotFoundException e2) {
			LOGGER.error(e2.getMessage());
			e2.printStackTrace();
			return PojoBuilder.responseResult("服务器异常错误！");
		}
		
		try {
			IOUtils.write(content, stream, Charset.forName("utf-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			return PojoBuilder.responseResult("服务器异常错误！");
		}
		
		LOGGER.info("准备发表……");
		try {
			return business.publishArticle(body.getArticle());
		} catch (NotAllowAttributeNull e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return PojoBuilder.responseResult("服务器异常！");
		}
		
	}
	
	// 发表评论
	@POST
	@Path("/comment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	public Object commentArticle(@Context HttpServletRequest request, @Context HttpServletResponse response, 
			@FormParam("articleid") String articleid, @FormParam("content") String commentContent,
			@FormParam("nickname") String nickname) {
		
		
		CommentEntity comment = PojoBuilder.comment();
		comment.setArticleid(articleid);
		comment.setContent(commentContent);
		comment.setNickname(nickname);
		
		CommentBusiness business = new CommentBusinessImpl();
		ResponseResultEntity result;
		try {
			// 评论发表
			LOGGER.info("准备发表评论……");
			result = business.publishComment(comment);
			if (!result.getStatus()) {
				String jsStr = "<script>alert('评论发表失败，即将跳转回文章页面……')window.location.href=" + 
			request.getHeader("Referer")+ "</script>";
				return jsStr;
			} else {
				response.sendRedirect(request.getHeader("Referer"));
				return null;
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			response.setStatus(500);
			LOGGER.error(e.getMessage());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			response.setStatus(500);
			return null;
		}
		
	}
	
	@POST
	@Path("/reply")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	public Object reply(@Context HttpServletRequest request, 
			@Context HttpServletResponse response, 
			@FormParam("articleid") String articleid, @FormParam("content") String commentContent,
			@FormParam("nickname") String nickname, 
			@FormParam("parentcomment") String parentCommentid) {
		
		CommentEntity comment = PojoBuilder.comment();
		comment.setArticleid(articleid);
		comment.setContent(commentContent);
		comment.setParentid(parentCommentid);
		
		if (null == request.getSession().getAttribute(SessionAttribute.LoginedUsername)) {
			response.setStatus(405);
			LOGGER.warn("登录状态已失效，重新登录！");
			return null;
		}
		comment.setNickname(request.getSession().getAttribute(SessionAttribute.LoginedUsername).toString());
		
		CommentBusiness business = new CommentBusinessImpl();
		try {
			ResponseResultEntity result = business.publishComment(comment);
			if (!result.getStatus()) {
				String jsStr = "<script>alert('评论发表失败，即将跳转回文章页面……')window.location.href=" + 
						request.getHeader("Referer")+ "</script>";
				return jsStr;
			}
			response.sendRedirect(request.getHeader("Referer"));
			return null;
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			response.setStatus(405);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			response.setStatus(405);
			return null;
		}
		
	}
	
	@POST
	@Path("/deleteComment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	public Object deleteComment(@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@FormParam("commentid") String commentid) {
		
		Object username = request.getSession().getAttribute(SessionAttribute.LoginedUsername);
		
		if (null == username) {
			response.setStatus(405);
			LOGGER.warn("登录状态已失效，重新登录！");
			return null;
		}
		
		CommentEntity comment = PojoBuilder.comment();
		comment.setCommentid(commentid);
		
		CommentBusiness business = new CommentBusinessImpl();
		AuthorEntity account = PojoBuilder.author();
		account.setUsername(username.toString().trim());
		try {
			ResponseResultEntity result = business.hideComment(account, comment);
			if (!result.getStatus()) {
				String jsStr = "<script>alert('评论发表失败，即将跳转回文章页面……')window.location.href=" + 
						request.getHeader("Referer")+ "</script>";
				return jsStr;
			}
			response.sendRedirect(request.getHeader("Referer"));
			return null;
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			response.setStatus(405);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			response.setStatus(405);
			return null;
		}
		
	}
	
	@POST
	@Path("/searchKeyword")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Object searchArticleWithKeyword(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response, 
			@FormParam("keyword") String keyword) {
		
		try {
			List<ArticleEntity> articles = business.getArticlesWithKeyword(keyword);
			
			AuthorEntity author = PojoBuilder.author();
			author.setUsername("frank");
			updateArticleList(author, request);
			
			List<ArticleEntity> contentArticles = business.getArticles(author);
			List<ArticleBody> bodys = new LinkedList<ArticleBody>();
			for (ArticleEntity articleEntity : contentArticles) {

				ArticleBody body = new ArticleBody();
				
				boolean find = false;
				// 查找已存文章
				for (ArticleEntity storedArticle : articles) {
					if (storedArticle.getArticleid().equals(articleEntity.getArticleid())) {
						find = true;
						break;
					}
				}
				
				// 获取内容
				byte[] buffer = null;
				File file = new File(request.getServletContext().getRealPath("articles"), articleEntity.getArticleid() + ".html");
				if (file.exists()) {
					InputStream stream = new FileInputStream(file);
					buffer = new byte[stream.available()];
					IOUtils.read(stream, buffer);
				}
				
				if (find) {
					
					body.setArticle(articleEntity);
					if (null != buffer) {
						body.setContent(new String(buffer, Charset.forName("utf-8")));
					}
					
					CommentBusiness commentBusiness = new CommentBusinessImpl();
					long size = commentBusiness.getComments(articleEntity).size();
					body.setCommentcount(size);
					DateUtil util = new DateUtil();
					body.setPublishdate(util.format("yyyy-MM-dd", articleEntity.getPublishdate()));
					body.setReadTimes(articleEntity.getReadtimes());
					body.setShortcut(articleEntity.getShortcut());
					bodys.add(body);
					continue; // 防止重复
				}
				
				if (null != buffer) {
					String content = new String(buffer, Charset.forName("utf-8"));
					if (null != content && content.contains(keyword)) {
						body.setArticle(articleEntity);
						body.setContent(content);
						CommentBusiness commentBusiness = new CommentBusinessImpl();
						long size = commentBusiness.getComments(articleEntity).size();
						body.setCommentcount(size);
						DateUtil util = new DateUtil();
						body.setPublishdate(util.format("yyyy-MM-dd", articleEntity.getPublishdate()));
						body.setReadTimes(articleEntity.getReadtimes());
						body.setShortcut(articleEntity.getShortcut());
						bodys.add(body);
					}
				}
			}
			request.getSession().setAttribute(SessionAttribute.IndexAllArticles, bodys);
			request.getSession().setAttribute(SessionAttribute.IndexMaxArticleCapacity, bodys.size() > 0 ? bodys.size() : 1);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return null;
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return null;
		} catch (ServletException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return null;
		}
		
	}
	
	private void updateArticleList(AuthorEntity author, HttpServletRequest request) {

		ArticleBusiness business = new ArticleBusinessImpl();
		try {
			LOGGER.info("准备获取评论最多的文章列表……");
			List<ArticleEntity> commentArticles = business.getCommentArticles(author);
			LOGGER.info("准备获取阅读次数最多的文章列表……");
			List<ArticleEntity> readtimeArticles = business.getReadtimesArticles(author);
			request.getSession().setAttribute(SessionAttribute.ReadtimeArticleList, readtimeArticles);
			request.getSession().setAttribute(SessionAttribute.CommentArticleList, commentArticles);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
}
