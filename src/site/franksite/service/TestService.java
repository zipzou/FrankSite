/**
 * 
 */
package site.franksite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.code.kaptcha.Constants;

import site.franksite.pojo.ImageCodeEntity;
import site.franksite.pojo.PojoBuilder;

/**
 * @author Frank
 *
 */
//@Path("/account")
public class TestService {
	
	static final Logger LOGGER = Logger.getLogger(TestService.class);
	
//	@Path("/validateImgCode")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces({"text/html;charset=utf-8", "application/json;charset=utf-8"})
//	public Object validateImageCode(@Context HttpServletRequest request, 
//			@Context HttpServletResponse response,
//			ImageCodeEntity validationCode) {
//		HttpSession session = request.getSession();
//		
//		if (null == session.getAttribute(Constants.KAPTCHA_SESSION_KEY)) {
//			LOGGER.warn("页面超时，刷新验证码！");
//			return PojoBuilder.responseResult("页面超时，请刷新验证码！");
//		}
//		
//		if (session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().toLowerCase().equals(validationCode.getCode().toLowerCase())) {
//			LOGGER.info("验证成功");
//			return PojoBuilder.responseResult();
//		} else { 
//			LOGGER.warn("验证码不匹配！");
//			return PojoBuilder.responseResult("验证码不匹配！");
//		}
//	}
	
//	@POST
//	@Path("/login")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Object login(@FormParam("username") String username, @FormParam("password") String password,
//			@FormParam("validateCode") String validateCode,
//			@Context HttpServletRequest request) {
//		LOGGER.info(username);
//		LOGGER.info(password);
//		LOGGER.info(validateCode);
//		
//		if (!request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().trim()
//				.toLowerCase().equals(validateCode.trim())) {
//			LOGGER.info("验证码错误");
//			return PojoBuilder.responseResult("验证码错误！");
//		}
//		
//		if (!username.trim().equals("frank")) {
//			LOGGER.info("用户名不存在");
//			return PojoBuilder.responseResult("用户名不存在！");
//		}
//		if (!password.trim().equals("zouzhipeng123A")) {
//			LOGGER.info("用户名和密码不匹配");
//			return PojoBuilder.responseResult("用户名和密码不匹配！");
//		}
//		
//		LOGGER.info("登陆成功，跳转URI:" + "..");
//		request.getSession().setAttribute("username", username);
//		return Response.status(302)
//				.header("Content-Type", "text/html;charset=utf-8")
//				.header("Location","..")
//				.build();
//		
//	}
}
