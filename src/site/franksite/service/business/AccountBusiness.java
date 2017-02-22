package site.franksite.service.business;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 用户账户业务类
 * @author Frank
 *
 */
public interface AccountBusiness {
	
	/**
	 * 注册一个用户
	 * @param account 要注册的用户
	 * @return 响应实体
	 */
	public ResponseResultEntity register(AuthorEntity account);
	
	/**
	 * 登录到系统
	 * @param account 要验证的账户
	 * @return 响应实体
	 */
	public ResponseResultEntity login(AuthorEntity account);
	
	/**
	 * 注销出系统
	 * @param account 要注销的用户
	 * @return 响应实体
	 */
	public ResponseResultEntity logout(AuthorEntity account);
	
	/**
	 * 修改用户邮件
	 * @param author 要修改的作者用户
	 * @return 响应实体
	 */
	public ResponseResultEntity changeEmail(AuthorEntity author);
	
	/**
	 * 修改用户手机号码
	 * @param author 要修改的作者用户
	 * @return 响应实体
	 */
	public ResponseResultEntity changeMobile(AuthorEntity author);
	
	/**
	 * 获取用户基本信息
	 * @param author 用户名
	 * @return 用户的基本信息实体
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public AuthorEntity authorBasicInfo(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 用户修改密码
	 * @param author 要修改密码的用户
	 * @return 修改结果
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public ResponseResultEntity changePassword(AuthorEntity author) throws NotAllowAttributeNull; 
	
	/**
	 * 用户找回密码
	 * @param author 要找回密码的用户
	 * @return 找回结果
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public ResponseResultEntity findPassword(AuthorEntity author) throws NotAllowAttributeNull;
	
}
