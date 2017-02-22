/**
 * 
 */
package site.franksite.service.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.franksite.pojo.ImageCodeEntity;

/**
 * 账户服务
 * @author Frank
 *
 */
public interface AccountService {

	/**
	 * 账户登陆
	 * @param username 用户名
	 * @param password 密码
	 * @param validationCode 验证码
	 * @param request 请求
	 * @return 若成功登录则重定向到首页，否则重定向到登录页
	 */
	public Object login(String username, String password, String validationCode, HttpServletRequest request, String ref);
	
	/**
	 * 用户注销
	 * @param username 要注销的用户名
	 * @param request 请求
	 * @param response 响应
	 * @param ref 引用的路径
	 * @return 进行重定向，返回response
	 */
	public Object logout(String username, HttpServletRequest request, HttpServletResponse response, String ref);
	
	/**
	 * 修改用户密码
	 * @param username 要修改的用户
	 * @param newPassword 新用户密码
	 * @return 提供JSON数据的交互，响应体
	 */
	public Object changePassword(String username, String newPassword);
	
	
	public Object findPassword(String username);
	/**
	 * 验证图片验证码
	 * @param request 请求
	 * @param response 响应
	 * @param validationCode 验证码实体
	 * @return 验证结果
	 */
	public Object validateImageCode(HttpServletRequest request, 
			HttpServletResponse response,
			ImageCodeEntity validationCode);
}
