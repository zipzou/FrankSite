/**
 * 
 */
package site.franksite.dao.interfaces;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章类型数据库层接口
 * @author Frank
 *
 */
public interface ArticleTypeDao {
	
	/**
	 * 插入一个文章类别
	 * @param articleType 文章类别
	 * @return 插入结果
	 */
	public ResponseResultEntity insertArticleType(ArticleTypeEntity articleType);
	/**
	 * 标记某个文章类别不可见
	 * @param articleType 要隐藏的文章类别
	 * @return 是否隐藏成功
	 * @throws NotAllowAttributeNull 当文章类别ID为空时，抛出该异常
	 */
	public ResponseResultEntity markArticleTypeUnvisiable(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	/**
	 * 查询用户的所有文章类别
	 * @param author 作者用户
	 * @return 所有的文章类别列表，按照创建时间倒序排列，若无分类，则产生一个空列表
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public List<ArticleTypeEntity> selectArticleTypes(AuthorEntity author) throws NotAllowAttributeNull;
	/**
	 * 查询某个博客的所有文章类别
	 * @param blog 要查询的博客
	 * @return 所有的文章分类列表，按照创建时间倒序排列，若无分类，则产生一个空列表
	 * @throws NotAllowAttributeNull 当博客ID为空时，该异常被抛出
	 */
	public List<ArticleTypeEntity> selectArticleTypes(BlogEntity blog) throws NotAllowAttributeNull;
	/**
	 * 查询指定的文章分类
	 * @param articleType 文章分类ID
	 * @return 指定的文章分类，若不存在，则为null
	 * @throws NotAllowAttributeNull 当文章分类的ID为空时，抛出该异常
	 */
	public ArticleTypeEntity selectArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	/**
	 * 更新文章类别信息
	 * @param articleType 要更新的文章分类
	 * @return 更新结果
	 * @throws NotAllowAttributeNull 当分类的ID为空时，则抛出该异常
	 */
	public ResponseResultEntity updateArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	/**
	 * 删除指定的实体
	 * @param articleType 要删除的实体
	 * @return 删除结果
	 * @throws NotAllowAttributeNull 当指定的分类ID为空时，抛出该异常
	 */
	public ResponseResultEntity deleteArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
}
