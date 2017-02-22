/**
 * 
 */
package site.franksite.service.business;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.EmailTokenEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 邮件令牌业务接口
 * @author Frank
 *
 */
public interface EmailTokenBusiness {

	/**
	 * 验证邮件令牌
	 * @param author 要验证的用户
	 * @param token 要验证的令牌
	 * @return 验证结果
	 * @throws NotAllowAttributeNull 当用户名为空，或令牌内容为空时，抛出该异常
	 */
	public ResponseResultEntity validateEmailToken(AuthorEntity author, EmailTokenEntity token) throws NotAllowAttributeNull;
	
	/**
	 * 所有可用的邮件令牌
	 * @param author 所属的用户
	 * @return 邮件令牌列表，根据时间倒序排列
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public List<EmailTokenEntity> usefulEmailTokens(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 使邮件令牌失效
	 * @param token 令牌
	 * @return 更新结果
	 * @throws NotAllowAttributeNull 当邮件令牌ID为空时，触发该异常
	 */
	public ResponseResultEntity invalidateEmailToken(EmailTokenEntity token) throws NotAllowAttributeNull;
	
	/**
	 * 创建一条新的邮件令牌
	 * @param author 用户
	 * @return 创建结果
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public ResponseResultEntity createEmailToken(AuthorEntity author) throws NotAllowAttributeNull;
	
}
