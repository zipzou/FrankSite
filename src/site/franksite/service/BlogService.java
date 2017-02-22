/**
 * 
 */
package site.franksite.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.ArticleBusiness;
import site.franksite.service.business.ArticleBusinessImpl;
import site.franksite.service.business.ArticleTypeBusiness;
import site.franksite.service.business.ArticleTypeBusinessImpl;
import site.franksite.service.business.BlogBusiness;
import site.franksite.service.business.BlogBusinessImpl;

/**
 * 博客服务
 * @author Frank
 *
 */
@Path("/blog")
public class BlogService {
	
	private final static Logger LOGGER = Logger.getLogger(BlogService.class);
	
	private BlogBusiness business = new BlogBusinessImpl();
	
	@POST
	@Path("/updateBlogSolgan")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object updateSlogan(BlogEntity blog,
			@Context HttpServletRequest request) {
		
		if (null == request.getSession().getAttribute(SessionAttribute.LoginedUsername)) {
			return PojoBuilder.responseResult("登录已失效，请重新登录！");
		}
		
		try {
			LOGGER.info("准备更新博客标语……");
			// 更新数据
			ResponseResultEntity result = business.changeBlogSlogan(blog);
			
			if (result.getStatus()) {
				BlogEntity latestBlog = reloadBlog(blog);
				if (null != latestBlog) {
					request.getSession().setAttribute(SessionAttribute.Blog, latestBlog);
				}
			}
			return result;
			
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return PojoBuilder.responseResult("服务器异常！");
		}
	}
	
	@POST
	@Path("/updateIndexMax")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object updateIndexMax(BlogEntity blog, @Context
			HttpServletRequest request) {
		
		if (null == request.getSession().getAttribute(SessionAttribute.LoginedUsername)) {
			return PojoBuilder.responseResult("登录已失效，请重新登录！");
		}
		
		try {
			LOGGER.info("准备更新最大容量……");
			// 更新数据
			ResponseResultEntity result = business.changeBlogIndexMax(blog);
			if (result.getStatus()) {
				BlogEntity latestBlog = reloadBlog(blog);
				if (null != latestBlog) {
					request.getSession().setAttribute(SessionAttribute.Blog, latestBlog);
				}
			}
			return result;
			
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return PojoBuilder.responseResult("服务器异常！");
		}
	}
	
	@POST
	@Path("/addType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object addType(ArticleTypeEntity type, 
			@Context HttpServletRequest request) {
		
		Object username = request.getSession().getAttribute(SessionAttribute.LoginedUsername);
		
		if (null == username) {
			return PojoBuilder.responseResult("登录已失效，请重新登录！");
		}
		
		ArticleTypeBusiness typeBusiness = new ArticleTypeBusinessImpl();
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername(username.toString());
		LOGGER.info("准备添加类型！");
		ResponseResultEntity result = typeBusiness.createArticleType(author, type);
		if (result.getStatus()) {
			Object obj = reloadTypes(author);
			request.getSession().setAttribute(SessionAttribute.AllTypes, obj);
		}
		return result;
		
	}
	
	@POST
	@Path("/deleteType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object deleteType(ArticleTypeEntity type, 
			@Context HttpServletRequest request) {
		
		Object username = request.getSession().getAttribute(SessionAttribute.LoginedUsername);
		
		if (null == username) {
			return PojoBuilder.responseResult("登录已失效，请重新登录！");
		}
		ArticleTypeBusiness typeBusiness = new ArticleTypeBusinessImpl();
		AuthorEntity author = PojoBuilder.author();
		author.setUsername(username.toString());
		try {
			ArticleBusiness articleBusiness = new ArticleBusinessImpl();
			if (articleBusiness.getArticles(type).size() > 0) {
				// 类别下有文章
				return PojoBuilder.responseResult("类别下文章不为空，无法删除！");
			}
			
			LOGGER.info("准备删除类别：" + type.getTitle() + "……");
			ResponseResultEntity result = typeBusiness.hideArticleType(author, type);
			
			if (result.getStatus()) {
				Object obj = reloadTypes(author);
				request.getSession().setAttribute(SessionAttribute.AllTypes, obj);
			}
			return result;
		} catch (NotAllowAttributeNull e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return PojoBuilder.responseResult("服务器异常！");
		}
	}
	
	BlogEntity reloadBlog(BlogEntity blog) {
		
		BlogBusiness blogBusiness = new BlogBusinessImpl();
		try {
			return blogBusiness.getBlog(blog);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			return null;
		}
	}
	
	List<ArticleTypeEntity> reloadTypes(AuthorEntity author) {
		
		ArticleTypeBusiness typeBusiness = new ArticleTypeBusinessImpl();
		try {
			return typeBusiness.getArticleTypes(author);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
