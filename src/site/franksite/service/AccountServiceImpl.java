/**
 * 
 */
package site.franksite.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.google.code.kaptcha.Constants;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.ImageCodeEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.AccountBusiness;
import site.franksite.service.business.AccountBusinessImpl;
import site.franksite.service.business.ArticleBusiness;
import site.franksite.service.business.ArticleBusinessImpl;
import site.franksite.service.business.BlogBusinessImpl;


/**
 * 账户服务类
 * @author Frank
 *
 */
@Path("/account")
public class AccountServiceImpl implements site.franksite.service.interfaces.AccountService {

	private Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see site.franksite.service.interfaces.AccountService#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	public Object login(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("validateCode") String validateCode, @Context HttpServletRequest request, 
			@QueryParam("ref") String ref) {
		
		System.out.println(ref);
		
		if (null == request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)) {
			LOGGER.info("未加载验证码！");
			return PojoBuilder.responseResult("页面超时，请刷新重试！");
		}
		
		if (!request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().trim()
				.toLowerCase().equals(validateCode.trim())) {
			LOGGER.info("验证码错误");
			return PojoBuilder.responseResult("验证码错误！");
		}
		
		AccountBusiness accountBusiness = new AccountBusinessImpl();
		AuthorEntity author = PojoBuilder.author();
		author.setUsername(username);
		author.setPassword(password);
		
		ResponseResultEntity result = accountBusiness.login(author);
		if (result.getStatus()) {
			request.getSession().setAttribute(SessionAttribute.LoginedUsername, author.getUsername().trim());
			// 获取所有的文章
			ArticleBusiness articleBusiness = new ArticleBusinessImpl();
			try {
				List<ArticleEntity> articles = articleBusiness.getArticles(author);
				request.getSession().setAttribute(SessionAttribute.IndexAllArticles, articles);
				// 获取博客
				BlogEntity blog = new BlogBusinessImpl().getBlog(author);
				request.getSession().setAttribute(SessionAttribute.IndexMaxArticleCapacity, blog.getIndexmax());
			} catch (NotAllowAttributeNull e) {
				e.printStackTrace();
				return PojoBuilder.responseResult(e.getMessage());
			}
			
			LOGGER.info("Referer：" + request.getHeader("Referer"));
			
			return Response.status(302)
					.header("Content-Type", "text/html;charset=utf-8")
					.header("Location",ref)
					.build();
		} else {
			return result;
		}
	}

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
	/* (non-Javadoc)
	 * @see site.franksite.service.interfaces.AccountService#logout(java.lang.String)
	 */
	@Override
	public Object logout(@FormParam("username") String username,
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@QueryParam("ref") String ref) {
		
		LOGGER.info("准备注销……");
		
		if (null == username) {
			response.setStatus(Status.BAD_REQUEST.getStatusCode());
			LOGGER.warn("用户名为空！");
			return PojoBuilder.responseResult("请求错误，参数非法！");
		}
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername(username);
		
		// session
		Object loginedUsername = request.getSession().getAttribute(SessionAttribute.LoginedUsername).toString();
		if (null == loginedUsername) {
			// 未登录或登录失败
			LOGGER.warn("未登录或登录失败");
			return PojoBuilder.responseResult("未登录或登录已失效！");
		}
		
		// 不等
		if (!username.trim().equals(((String)loginedUsername).trim())) {
			response.setStatus(Status.BAD_REQUEST.getStatusCode());
			LOGGER.warn("注销错误");
			return PojoBuilder.responseResult("注销错误！");
		}
		
		AccountBusiness accountBusiness = new AccountBusinessImpl();
		ResponseResultEntity result = accountBusiness.logout(author);
		if (result.getStatus()) {
			// 注销成功，重定向
			try {
				LOGGER.info("注销成功！");
				request.getSession().removeAttribute(SessionAttribute.LoginedUsername);
				response.sendRedirect(request.getHeader("Referer"));
			} catch (IOException e) {
				e.printStackTrace();
				response.setStatus(Status.NOT_FOUND.getStatusCode());
				LOGGER.info("注销失败！");
				return PojoBuilder.responseResult("注销失败！");
			}
		}
		
		return null;
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.interfaces.AccountService#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Object changePassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.interfaces.AccountService#findPassword(java.lang.String)
	 */
	@Override
	public Object findPassword(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.interfaces.AccountService#validateImageCode(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, site.franksite.pojo.ImageCodeEntity)
	 */
	@Override
	@Path("/validateImgCode")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({"text/html;charset=utf-8", "application/json;charset=utf-8"})
	public Object validateImageCode(@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			ImageCodeEntity validationCode) {
		HttpSession session = request.getSession();

		if (null == session.getAttribute(Constants.KAPTCHA_SESSION_KEY)) {
			LOGGER.warn("页面超时，刷新验证码！");
			return PojoBuilder.responseResult("页面超时，请刷新验证码！");
		}

		if (session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().toLowerCase()
				.equals(validationCode.getCode().toLowerCase())) {
			LOGGER.info("验证成功");
			return PojoBuilder.responseResult();
		} else {
			LOGGER.warn("验证码不匹配！");
			return PojoBuilder.responseResult("验证码不匹配！");
		}
	}

}
