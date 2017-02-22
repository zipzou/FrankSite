/**
 * 
 */
package site.franksite.dao.interfaces;

import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.EmailTokenEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 用户查询
 * @author Frank
 *
 */
public interface AuthorDao {
	
	/**
	 * 查询作者用户
	 * @param author 用户
	 * @return 查询到的用户，若未查询到，则为null
	 */
	public AuthorEntity selectAuthor(AuthorEntity author);
	
	/**
	 * 依据博客查询作者
	 * @param blog 博客
	 * @return 博客所属的作者，若未查询到，则为null
	 */
	public AuthorEntity selectAuthor(BlogEntity blog);
	
	/**
	 * 依据文章分类，查询作者
	 * @param articleType 文章分类
	 * @return 分类所属的博客，博客所属的作者，若未查询到，则为null
	 */
	public AuthorEntity selectAuthor(ArticleTypeEntity articleType);
	/**
	 * 根据文章，查询作者
	 * @param article 文章
	 * @return 文章作者，若未查询到，则为null
	 */
	public AuthorEntity selectAuthor(ArticleEntity article);
	/**
	 * 依据邮件验证令牌查询作者
	 * @param token 邮件验证令牌
	 * @return 邮件验证令牌，若未查询到，则为null
	 */
	public AuthorEntity selectAuthor(EmailTokenEntity token);
	
	/**
	 * 更新用户信息
	 * @param author 最新的作者信息
	 * @return 是否更新成功
	 */
	public ResponseResultEntity updateAuthor(AuthorEntity author);
	
	/**
	 * 插入用户
	 * @param author 要插入的用户信息
	 * @return 是否插入成功
	 */
	public ResponseResultEntity insertAuthor(AuthorEntity author);
	
	/**
	 * 删除用户
	 * @param author 要删除的用户
	 * @return 是否删除成功
	 */
	public ResponseResultEntity deleteAuthor(AuthorEntity author);
}
