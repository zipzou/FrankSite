/**
 * 
 */
package site.franksite.dao.interfaces;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章数据库交互接口
 * @author Frank
 *
 */
public interface ArticleDao {

	/**
	 * 插入文章
	 * @param article 要插入的文章
	 * @return 插入结果
	 */
	public ResponseResultEntity insertArticle(ArticleEntity article);
	
	/**
	 * 查询用户所有的文章
	 * @param author 文章作者
	 * @return 所有的文章列表，依据发表日期倒序排列，若无文章，则为空列表
	 * @throws NotAllowAttributeNull 若用户名为空，则抛出该异常
	 */
	public List<ArticleEntity> selectArticles(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 查询博客下的所有文章
	 * @param blog 文章所收录的博客
	 * @return 所有的文章列表，依据发表日期倒序排列，若无文章，则为空列表
	 * @throws NotAllowAttributeNull 当博客ID为空时抛出该异常
	 */
	public List<ArticleEntity> selectArticles(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 查询某个分类下的所有文章
	 * @param articleType 文章分类
	 * @return 所有的文章列表，依据发表日期倒序排列，若无文章，则为空列表
	 * @throws NotAllowAttributeNull 当分类ID为空时，抛出该异常
	 */
	public List<ArticleEntity> selectArticles(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	
	/**
	 * 根据文章查询指定的文章详情
	 * @param article 文章ID
	 * @return 指定的文章详情，若ID不存在，在为null
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ArticleEntity selectArticle(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 根据评论查找指定的文章
	 * @param comment 评论
	 * @return 评论所属的文章
	 * @throws NotAllowAttributeNull 当评论ID为空时，抛出该异常
	 */
	public ArticleEntity selectArticle(CommentEntity comment) throws NotAllowAttributeNull;
	
	/**
	 * 更新文章信息
	 * @param article 要更新的文章，及要更新的信息
	 * @return 更新结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity updateArticle(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 标记文章为不可见
	 * @param article 要标记的文章
	 * @return 标记结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity markArticleUnvisiable(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 删除文章实体
	 * @param article 要删除的文章实体
	 * @return 删除结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity deleteArticle(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 查询阅读次数最多的10条文章
	 * @param blog 博客实体
	 * @return 文章列表
	 */
	public List<ArticleEntity> selectMaxReadArticles(BlogEntity blog);
	
	/**
	 * 查询阅读次数最多的10条文章
	 * @param author 作者
	 * @return 文章列表
	 */
	public List<ArticleEntity> selectMaxReadArticles(AuthorEntity author);
	
	/**
	 * 查询评论次数最多的10条文章
	 * @param blog 博客
	 * @return 文章列表
	 */
	public List<ArticleEntity> selectMaxCommentArticles(BlogEntity blog);
	
	/**
	 * 查询阅读次数最多的10条文章
	 * @param author 作者
	 * @return 文章列表
	 */
	public List<ArticleEntity> selectMaxCommentArticles(AuthorEntity author);
	
	/**
	 * 根据关键字搜索文章
	 * @param keyword 要搜索的关键字
	 * @return 文章列表
	 */
	public List<ArticleEntity> selectArticlesWithKeyword(String keyword);
}
