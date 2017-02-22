/**
 * 
 */
package site.franksite.service.business;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章业务接口
 * @author Frank
 *
 */
public interface ArticleBusiness {

	/**
	 * 发表文章
	 * @param article 要发表的文章，文章ID即为本地文件名（不含后缀）
	 * @return 发表结果
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public ResponseResultEntity publishArticle(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 隐藏用户的某篇文章
	 * @param article 要隐藏的文章
	 * @return 隐藏结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity hideArticle(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 根据作者，获取作者的所有文章
	 * @param author 要获取的文章作者
	 * @return 所有的文章列表，依据发表日期降序排列，若该用户未发表文章，则为空表
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public List<ArticleEntity> getArticles(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 根据博客，获取博客的所有文章
	 * @param blog 要获取的文章博客
	 * @return 所有的文章列表，依据发表日期降序排列，若该用户未发表文章，则为空表
	 * @throws NotAllowAttributeNull 当博客ID为空时，抛出该异常
	 */
	public List<ArticleEntity> getArticles(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 根据文章类型，获取属于该类的所有文章
	 * @param articleType 文章分类
	 * @return 所有的文章列表，依据发表日期降序排列，若该用户未发表文章，则为空表
	 * @throws NotAllowAttributeNull 当分类ID为空时，抛出该异常
	 */
	public List<ArticleEntity> getArticles(ArticleTypeEntity articleType) throws NotAllowAttributeNull;
	
	/**
	 * 根据文章ID获取指定文章
	 * @param article 要获取的文章
	 * @return 所有的文章列表，依据发表日期降序排列，若该用户未发表文章，则为空表
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ArticleEntity getArticle(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 根据评论获取到指定的文章
	 * @param comment 文章评论
	 * @return 评论所针对的文章
	 * @throws NotAllowAttributeNull 当评论ID为空时，抛出该异常
	 */
	public ArticleEntity getArticle(CommentEntity comment) throws NotAllowAttributeNull;
	
	/**
	 * 增加文章的阅读次数
	 * @param article 要增加的文章
	 * @return 增加结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity increaseArticleReadTime(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 减少文章的阅读次数
	 * @param article 要减少的文章
	 * @return 减少结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity decreaseArticleReadTime(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 获取文章评论数
	 * @param article 要查询的文章
	 * @return 评论数
	 */
	public long getArticleCommentCount(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 获取读取次数最多的10条文章记录
	 * @param blog 博客
	 * @return 文章列表
	 * @throws NotAllowAttributeNull 当博客ID为空时，抛出该异常
	 */
	public List<ArticleEntity> getReadtimesArticles(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 获取读取次数最多的10条文章记录
	 * @param author 作者
	 * @return 文章列表
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public List<ArticleEntity> getReadtimesArticles(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 获取评论次数最多的10条文章记录
	 * @param blog 博客
	 * @return 文章列表
	 * @throws NotAllowAttributeNull 当博客ID为空时，抛出该异常
	 */
	public List<ArticleEntity> getCommentArticles(BlogEntity blog) throws NotAllowAttributeNull;
	
	/**
	 * 获取评论次数最多的10条文章记录
	 * @param author 作者
	 * @return 文章列表
	 * @throws NotAllowAttributeNull 当用户名为空时，抛出该异常
	 */
	public List<ArticleEntity> getCommentArticles(AuthorEntity author) throws NotAllowAttributeNull;
	
	/**
	 * 根据关键词查询到文章
	 * @param keyword 关键字
	 * @return 文章列表
	 * @throws NotAllowAttributeNull 当关键字为空时，抛出该异常
	 */
	public List<ArticleEntity> getArticlesWithKeyword(String keyword) throws NotAllowAttributeNull;
}
