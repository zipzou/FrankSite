/**
 * 
 */
package site.franksite.dao.interfaces;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 博客数据库交互接口
 * @author Frank
 *
 */
public interface BlogDao {

	/**
	 * 根据用户查询博客
	 * @param author 博客的所属作者
	 * @return 查询到的博客实体，若该用户无博客，则为null
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public BlogEntity selectBlog(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 根据博客ID查询到博客
	 * @param blog 要查询的博客
	 * @return 查询到的博客实体，若该指定博客不存在，则为null
	 * @throws NotAllowAttributeNull 当博客实体的ID属性为空时，抛出该异常
	 */
	public BlogEntity selectBlog(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 根据文章分类查询到博客
	 * @param articleType 要查询的文章分类
	 * @return 查询到的博客实体，若该指定博客不存在，则为null
	 * @throws NotAllowAttributeNull 当分类ID属性为空时，抛出该异常
	 */
	public BlogEntity selectBlog(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	
	/**
	 * 查找最新的博客实体
	 * @return 最新的博客实体信息，若不存在，则为null
	 */
	public BlogEntity selectLatestBlog();
	/**
	 * 插入一个新的博客实体
	 * @param blog 要插入的博客信息
	 * @return 插入结果
	 */
	public ResponseResultEntity insertBlog(BlogEntity blog);
	/**
	 * 更新博客标语信息
	 * @param blog 要更新的博客
	 * @return 是否更新成功
	 * @throws NotAllowAttributeNull 当要更新博客时，博客ID为空，抛出该异常
	 */
	public ResponseResultEntity updateBlog(BlogEntity blog) throws NotAllowAttributeNull;
	/**
	 * 删除指定的博客
	 * @param blog 要删除的博客ID
	 * @return 是否删除成功
	 * @throws NotAllowAttributeNull 当要删除博客时，博客ID为空，抛出该异常
	 */
	public ResponseResultEntity deleteBlog(BlogEntity blog) throws NotAllowAttributeNull;
}
