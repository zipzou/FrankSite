package site.franksite.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.DateUtil;
import site.franksite.pojo.PojoBuilder;
import site.franksite.service.body.ArticleBody;
import site.franksite.service.body.CommentReplyBody;
import site.franksite.service.business.ArticleBusiness;
import site.franksite.service.business.ArticleBusinessImpl;
import site.franksite.service.business.ArticleTypeBusiness;
import site.franksite.service.business.ArticleTypeBusinessImpl;
import site.franksite.service.business.BlogBusiness;
import site.franksite.service.business.BlogBusinessImpl;
import site.franksite.service.business.CommentBusiness;
import site.franksite.service.business.CommentBusinessImpl;

@Path("/page")
public class PageService {

	private static final Logger LOGGER = Logger.getLogger(PageService.class);
	@GET
	@Path("/author/{username}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.TEXT_HTML})
	public void indexpage(@PathParam("username") String username, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		
		// 查询
		AuthorEntity author = PojoBuilder.author();
		author.setUsername(username);
		
		HttpSession session = request.getSession();
		
		updateArticleList(author, request);
		
		// 获取所有文章
		LOGGER.info("获取文章……");
		ArticleBusiness articleBusiness = new ArticleBusinessImpl();
		try {
			List<ArticleEntity> articles = articleBusiness.getArticles(author);
			List<ArticleBody> articleBodys = new LinkedList<ArticleBody>();
			for (ArticleEntity articleEntity : articles) {
				ArticleBody articleBody = new ArticleBody();
				articleBody.setArticle(articleEntity);
				DateUtil util = new DateUtil();
				articleBody.setPublishdate(util.format("yyyy-MM-dd", new Date(articleEntity.getPublishdate().getTime())));
				articleBody.setReadTimes(articleEntity.getReadtimes());
				articleBody.setCommentcount(articleBusiness.getArticleCommentCount(articleEntity));
				articleBody.setShortcut("这是摘要信息！");
				articleBodys.add(articleBody);
			}
			
			session.setAttribute(SessionAttribute.IndexAllArticles, articleBodys);
			
			// 获取博客设置
			BlogBusiness blogBusiness = new BlogBusinessImpl();
			BlogEntity blog = blogBusiness.getBlog(author);
			LOGGER.info("获取博客设置……");
			session.setAttribute(SessionAttribute.IndexMaxArticleCapacity, articles.size() < blog.getIndexmax() ? 
					articles.size() : blog.getIndexmax());
			if (articles.isEmpty()) {
				session.setAttribute(SessionAttribute.IndexMaxArticleCapacity, 1l);
			}
			session.setAttribute(SessionAttribute.Blog, blog);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/types")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void typespage(@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		ArticleTypeBusiness typeBusiness = new ArticleTypeBusinessImpl();
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		
		updateArticleList(author, request);
		
		try {
			LOGGER.info("准备查询所有文章分类……");
			List<ArticleTypeEntity> types = typeBusiness.getArticleTypes(author);
			request.getSession().setAttribute(SessionAttribute.TypesAttribute, types);
			request.getSession().setAttribute(SessionAttribute.CurrentUrl, request.getServletContext() + "../types");
			response.sendRedirect("../types");
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/allarticles")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void allarticlesPage(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		
		ArticleBusiness articeBusiness = new ArticleBusinessImpl();
		updateArticleList(author, request);
		
		try {
			List<ArticleEntity> articles = articeBusiness.getArticles(author);
			List<ArticleBody> bodys = new LinkedList<ArticleBody>();
			for (ArticleEntity articleEntity : articles) {
				// 获取内容
				byte[] buffer = null;
				File file = new File(request.getServletContext().getRealPath("articles"), articleEntity.getArticleid() + ".html");
				if (file.exists()) {
					InputStream stream = new FileInputStream(file);
					buffer = new byte[stream.available()];
					IOUtils.read(stream, buffer);
				}
				ArticleBody body = new ArticleBody();
				body.setArticle(articleEntity);
				if (null != buffer) {
					body.setContent(new String(buffer, Charset.forName("utf-8")));
				}
				bodys.add(body);
			}
			
			request.getSession().setAttribute(SessionAttribute.AllArticles, bodys);
			// 重定向
			response.sendRedirect("../articles");
			request.getSession().setAttribute(SessionAttribute.CurrentUrl, request.getServletContext() + "../types");
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/article/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void articlePage(@PathParam("id") String id, 
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		ArticleEntity article = PojoBuilder.article();
		article.setArticleid(id);
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		
		updateArticleList(author, request);
		
		ArticleBusiness articleBusiness = new ArticleBusinessImpl();
		try {
			LOGGER.info("准备获取文章……");
			ArticleEntity destArticle = articleBusiness.getArticle(article);
			
			// 获取内容
			byte []buffer = null;
			File file = new File(request.getServletContext().getRealPath("articles"), article.getArticleid() + ".html");
			if (file.exists()) {
				InputStream stream = new FileInputStream(file);
				buffer = new byte[stream.available()];
				IOUtils.read(stream, buffer);
			}
			
			if (null == destArticle) {
				response.setStatus(404);
				return;
			}
			LOGGER.info("准备获取评论……");
			ArticleBody body = new ArticleBody();
			body.setArticle(destArticle);
			if (null != buffer)
				body.setContent(new String(buffer, Charset.forName("utf-8")));
			// 获取评论
			CommentBusiness commentBusiness = new CommentBusinessImpl();
			DateUtil util = new DateUtil();
			List<CommentEntity> comments = commentBusiness.getComments(destArticle);
			request.getSession().setAttribute(SessionAttribute.CurrentArticle, body);
			List<CommentReplyBody> commentBodys = new LinkedList<CommentReplyBody>();
			for (CommentEntity commentEntity : comments) {
				CommentReplyBody commentBody = new CommentReplyBody();
				commentBody.setDateStr(util.format("yyyy-MM-dd HH:mm", commentEntity.getCommentdate()));
				commentBody.setComment(commentEntity);
				if (null != commentEntity.getParentid()) {
					// 含有父评论
					CommentEntity parentComment = new CommentEntity();
					parentComment.setCommentid(commentEntity.getParentid());
					parentComment = commentBusiness.getComment(parentComment);
					commentBody.setParentComment(parentComment);
				}
				commentBodys.add(commentBody);
			}
			request.getSession().setAttribute(SessionAttribute.CommentList, commentBodys);
			request.getSession().setAttribute(SessionAttribute.CurrentUrl, request.getServletContext());
			if (null == request.getSession().getAttribute(SessionAttribute.LoginedUsername)) {
				articleBusiness.increaseArticleReadTime(destArticle);
			}
			request.getRequestDispatcher("/views/articlePage.jsp").forward(request, response);;
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	

	@GET
	@Path("/types/{typeid}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void articlesForType(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@PathParam("typeid") String typeid) {
		ArticleTypeEntity type = PojoBuilder.articletype();
		
		try {
			type.setTypeid(Integer.parseInt(typeid));
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
			return;
		}
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		
		updateArticleList(author, request);
		
		
		ArticleBusiness articleBusiness = new ArticleBusinessImpl();
		try {
			List<ArticleBody> articles = new LinkedList<ArticleBody>();
			List<ArticleEntity> articleEntities = articleBusiness.getArticles(type);
			for (ArticleEntity articleEntity : articleEntities) {
				ArticleBody body = new ArticleBody();
				body.setArticle(articleEntity);
				body.setShortcut("这是摘要的测试内容");
				
				// 获取内容
				byte[] buffer = null;
				File file = new File(request.getServletContext().getRealPath("articles"), articleEntity.getArticleid() + ".html");
				if (file.exists()) {
					InputStream stream = new FileInputStream(file);
					buffer = new byte[stream.available()];
					IOUtils.read(stream, buffer);
				}
				
				if (null != buffer) {
					body.setContent(new String(buffer, Charset.forName("utf-8")));
				}
				
				articles.add(body);
			}
			request.getSession().setAttribute(SessionAttribute.AllArticles, articles);
			request.getSession().setAttribute(SessionAttribute.CurrentUrl, request.getServletContext());
			request.getRequestDispatcher("/views/allarticles.jsp").forward(request, response);
			
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			response.setStatus(404);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Path("/publish")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void publishPreload(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		
		updateArticleList(author, request);
		
		ArticleTypeBusiness typeBusiness = new ArticleTypeBusinessImpl();
		try {
			List<ArticleTypeEntity> types = typeBusiness.getArticleTypes(author);
			request.setAttribute(SessionAttribute.PublishTypes, types);
			request.getSession().setAttribute(SessionAttribute.CurrentUrl, request.getServletContext());
			request.getRequestDispatcher("/views/publish.jsp").forward(request, response);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/settings")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Object getSettingsPage(@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		
		Object username = request.getSession().getAttribute(SessionAttribute.LoginedUsername);
		
		if (null == username) {
			response.setStatus(Status.REQUEST_TIMEOUT.getStatusCode());
			return null;
		}
		
		ArticleTypeBusiness typebusiness = new ArticleTypeBusinessImpl();
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername(username.toString());
		
		updateArticleList(author, request);
		
		BlogBusiness blogBusiness = new BlogBusinessImpl();
		BlogEntity blog;
		try {
			LOGGER.info("获取博客……");
			blog = blogBusiness.getBlog(author);
		} catch (NotAllowAttributeNull e1) {
			e1.printStackTrace();
			LOGGER.info(e1.getMessage());
			response.setStatus(Status.BAD_REQUEST.getStatusCode());
			return null;
		}
		
		if (null == blog) {
			response.setStatus(Status.BAD_REQUEST.getStatusCode());
			return null;
		}
		
		try {
			LOGGER.info("获取类型列表……");
			List<ArticleTypeEntity> types = typebusiness.getArticleTypes(blog);
			request.getSession().setAttribute(SessionAttribute.AllTypes, types);
			response.sendRedirect("../settings");
			return null;
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			response.setStatus(Status.BAD_REQUEST.getStatusCode());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			response.setStatus(Status.BAD_REQUEST.getStatusCode());
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
	
	private void updateArticleList(BlogEntity blog, HttpServletRequest request) {
		ArticleBusiness business = new ArticleBusinessImpl();
		try {
			LOGGER.info("准备获取评论最多的文章列表……");
			List<ArticleEntity> commentArticles = business.getCommentArticles(blog);
			LOGGER.info("准备获取阅读次数最多的文章列表……");
			List<ArticleEntity> readtimeArticles = business.getReadtimesArticles(blog);
			request.getSession().setAttribute(SessionAttribute.ReadtimeArticleList, readtimeArticles);
			request.getSession().setAttribute(SessionAttribute.CommentArticleList, commentArticles);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
}
