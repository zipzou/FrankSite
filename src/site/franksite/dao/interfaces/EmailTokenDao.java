/**
 * 
 */
package site.franksite.dao.interfaces;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.EmailTokenEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 邮件验证码业务类接口
 * @author Frank
 *
 */
public interface EmailTokenDao {

	/**
	 * 查询有效的令牌
	 * @param author 用户作者
	 * @return 有效的邮件验证令牌
	 */
	public EmailTokenEntity selectUsefulEmailToken(AuthorEntity author);
	
	/**
	 * 查询所有的邮件令牌
	 * @param author 要查询令牌的所属用户
	 * @return 属于某个用户的邮件令牌
	 */
	public List<EmailTokenEntity> selectEmailTokens(AuthorEntity author);
	
	/**
	 * 根据令牌或内容查询令牌
	 * @param tokenEntity 令牌ID或内容
	 * @return 指定的令牌详情
	 */
	public EmailTokenEntity selectEmailToken(EmailTokenEntity tokenEntity);
	/**
	 * 标识令牌为不可用
	 * @param tokenEntity 要标识的令牌
	 * @return 标识的结果
	 */
	public ResponseResultEntity markTokenDel(EmailTokenEntity tokenEntity);
	/**
	 * 标识用户的有效令牌为不可用
	 * @param author 要标识的令牌的所有用户
	 * @return 标识的结果
	 */
	public ResponseResultEntity markTokenDel(AuthorEntity author);
	/**
	 * 插入新的令牌
	 * @param tokenEntity 要存储的令牌实体
	 * @exception NotAllowAttributeNull 当用户名属性为空时抛出该异常
	 * @return 插入的结果
	 */
	public ResponseResultEntity insertToken(EmailTokenEntity tokenEntity);
	/**
	 * 删除存储的令牌实体
	 * @param tokenEntity 要删除的令牌
	 * @return 删除结果
	 */
	public ResponseResultEntity deleteTokenEntity(EmailTokenEntity tokenEntity);
}
