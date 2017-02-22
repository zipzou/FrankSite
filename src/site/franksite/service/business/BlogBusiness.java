/**
 * 
 */
package site.franksite.service.business;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 博客业务接口
 * @author Frank
 *
 */
public interface BlogBusiness {

	/**
	 * 为作者用户安排博客
	 * @param authorAndBlog 作者，若初次分配，则博客ID可为空，若更换博客，则博客ID可以为已有ID
	 * @return 安排结果
	 * @throws NotAllowAttributeNull 博客ID和用户ID均不可为空，若为空，则抛出该异常
	 */
	public ResponseResultEntity assignBlogToAuthor(AuthorEntity authorAndBlog) throws NotAllowAttributeNull;
	
	/**
	 * 创建一个博客
	 * @param blog 要创建的博客信息，不包含ID
	 * @return 创建结果
	 */
	public ResponseResultEntity createBlog(BlogEntity blog);
	
	/**
	 * 修改博客标语
	 * @param blog 要修改的博客
	 * @return 修改结果
	 * @throws NotAllowAttributeNull 博客ID为空时，抛出该异常
	 */
	public ResponseResultEntity changeBlogSlogan(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 修改博客首页最大容量
	 * @param blog 要修改的博客
	 * @return 修改结果
	 * @throws NotAllowAttributeNull 博客ID为空时，抛出该异常
	 */
	public ResponseResultEntity changeBlogIndexMax(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 获取用户的博客
	 * @param author 作者用户
	 * @return 作者管理的博客
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public BlogEntity getBlog(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 获取指定的博客
	 * @param blog 博客ID
	 * @return 指定的博客
	 * @throws NotAllowAttributeNull 当博客ID为空时，抛出该异常
	 */
	public BlogEntity getBlog(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 根据文章分类获取博客
	 * @param articleType 文章分类
	 * @return 文章分类所属的博客
	 * @throws NotAllowAttributeNull 文章分类ID为空时，抛出该异常
	 */
	public BlogEntity getBlog(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
}
