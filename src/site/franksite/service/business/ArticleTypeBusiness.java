/**
 * 
 */
package site.franksite.service.business;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章分类业务接口
 * @author Frank
 *
 */
public interface ArticleTypeBusiness {

	/**
	 * 创建文章分类
	 * @param author 要创建的作者
	 * @param articleTpye 要创建的分类
	 * @return 创建结果
	 */
	public ResponseResultEntity createArticleType(AuthorEntity author, ArticleTypeEntity articleType);
	
	/**
	 * 隐藏文章分类
	 * @param author 要隐藏的作者
	 * @param articleType 要隐藏的分类
	 * @return 隐藏结果
	 * @throws NotAllowAttributeNull 若分类ID为空，抛出该异常
	 */
	public ResponseResultEntity hideArticleType(AuthorEntity author, ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	
	/**
	 * 修改文章分类标题
	 * @param author 要修改的用户
	 * @param articleType 要修改的文章类别
	 * @return 修改结果
	 * @throws NotAllowAttributeNull 当文章类别ID为空时，抛出该异常
	 */
	public ResponseResultEntity changeArticleTypeTitle(AuthorEntity author, ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	
	/**
	 * 获取用户所有的文章分类
	 * @param author 作者用户
	 * @return 所有的分类
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public List<ArticleTypeEntity> getArticleTypes(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 获取博客获取所有文章分类
	 * @param blog 博客
	 * @return 所有的分类
	 * @throws NotAllowAttributeNull 当博客ID为空时，抛出该异常
	 */
	public List<ArticleTypeEntity> getArticleTypes(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 获取指定的文章分类信息
	 * @param articleType 文章分类
	 * @return 要获取的文章分类
	 * @throws NotAllowAttributeNull 当文章分类ID为空时，抛出该异常
	 */
	public ArticleTypeEntity getArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	
	
}
