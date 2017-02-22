/**
 * 
 */
package site.franksite.service.business;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import site.franksite.dao.ArticleDaoImpl;
import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.ArticleDao;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章业务类
 * @author Frank
 *
 */
public class ArticleBusinessImpl implements ArticleBusiness {

	private Logger LOGGER = Logger.getLogger(ArticleBusinessImpl.class);
	private ArticleDao articleDaoImpl = new ArticleDaoImpl();
	private BlogEntity blog; // 博客
	private AuthorEntity author; // 用户
	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#publishArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity publishArticle(ArticleEntity article) throws NotAllowAttributeNull {
		
		// 分离出作者
		AuthorEntity author = new AuthorEntity();
		author.setUsername(article.getUsername());
		
		ResponseResultEntity authorValidation = validateAuthor(author);
		if (!authorValidation.getStatus()) {
			return authorValidation;
		} else {
			ResponseResultEntity blogValidation = validateBlog(article);
			if (!blogValidation.getStatus()) {
				return blogValidation;
			}
		}
		
		article.setPublishdate(new Timestamp(new Date().getTime()));
		LOGGER.info("准备发表文章：" + article.getArticleid() + "……");
		return articleDaoImpl.insertArticle(article);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#hideArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity hideArticle(ArticleEntity article) throws NotAllowAttributeNull {

		// 分离出作者
		AuthorEntity author = new AuthorEntity();
		author.setUsername(article.getUsername());

		ResponseResultEntity authorValidation = validateAuthor(author);
		if (!authorValidation.getStatus()) {
			return authorValidation;
		} else {
			ResponseResultEntity blogValidation = validateBlog(article);
			if (!blogValidation.getStatus()) {
				return blogValidation;
			}
		}
		
		return articleDaoImpl.markArticleUnvisiable(article);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticles(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleEntity> getArticles(AuthorEntity author) throws NotAllowAttributeNull {
		
		return articleDaoImpl.selectArticles(author);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticles(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleEntity> getArticles(BlogEntity blog) throws NotAllowAttributeNull {
		
		return articleDaoImpl.selectArticles(blog);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticles(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public List<ArticleEntity> getArticles(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		return articleDaoImpl.selectArticles(articleType);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ArticleEntity getArticle(ArticleEntity article) throws NotAllowAttributeNull {
		
		return articleDaoImpl.selectArticle(article);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticle(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ArticleEntity getArticle(CommentEntity comment) throws NotAllowAttributeNull {
		return articleDaoImpl.selectArticle(comment);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#increaseArticleReadTime(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity increaseArticleReadTime(ArticleEntity article) throws NotAllowAttributeNull {
		
		// 获取文章
		ArticleEntity storedArticle = getArticle(article);
		
		if (null == storedArticle) {
			LOGGER.warn("未找到指定的文章！");
			return PojoBuilder.responseResult("未找到指定的文章：" + article.getArticleid());
		}
		
		storedArticle.setReadtimes(storedArticle.getReadtimes() + 1);
		return articleDaoImpl.updateArticle(storedArticle);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#decreaseArticleReadTime(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity decreaseArticleReadTime(ArticleEntity article) throws NotAllowAttributeNull {
		// 获取文章
		ArticleEntity storedArticle = getArticle(article);

		if (null == storedArticle) {
			LOGGER.warn("未找到指定的文章！");
			return PojoBuilder.responseResult("未找到指定的文章：" + article.getArticleid());
		}

		storedArticle.setReadtimes(storedArticle.getReadtimes() - 1);
		if (0 > storedArticle.getReadtimes()) {
			storedArticle.setReadtimes(0l);
		}
		return articleDaoImpl.updateArticle(storedArticle);
	}
	
	private ResponseResultEntity validateAuthor(AuthorEntity author) throws NotAllowAttributeNull {
		if (null == this.author || !this.author.getUsername().equals(author.getUsername())) { // 验证用户
			AccountBusiness authorBusiness = new AccountBusinessImpl();
			AuthorEntity authorInfo = authorBusiness.authorBasicInfo(author);
			if (null == authorInfo) {
				// 未查询到结果
				LOGGER.warn("用户名不存在，无法完成发表！");
				return PojoBuilder.responseResult("用户名不存在，无法完成发表！");
			}
			LOGGER.info("用户名验证成功，准备验证博客权限……");
			this.author = authorInfo;
			return PojoBuilder.responseResult();
		}
		return PojoBuilder.responseResult();
	}
	
	private ResponseResultEntity validateBlog(ArticleEntity article) throws NotAllowAttributeNull {
		if (null == blog || !blog.getBlogid().equals(this.author.getBlogid())) { // 验证博客
			// 根据用户名查询博客
			ArticleTypeEntity articleType = new ArticleTypeEntity();
			articleType.setTypeid(article.getTypeid());
			BlogEntity blog = new BlogBusinessImpl().getBlog(articleType);
			
			if (!blog.getBlogid().equals(this.author.getBlogid())) {
				// 博客ID不匹配，权限错误
				LOGGER.warn("权限错误，用户：" + this.author.getUsername() + "无权访问博客：" + blog.getBlogid());
				return PojoBuilder.responseResult("权限错误，用户：" + this.author.getUsername() + "无权访问博客：" + blog.getBlogid());
			}
			
			LOGGER.info("博客权限验证通过，准备发表文章……");
			this.blog = blog;
		}
		return PojoBuilder.responseResult();
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticleCommentCount(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public long getArticleCommentCount(ArticleEntity article) throws NotAllowAttributeNull {
		CommentBusiness commentBusiness = new CommentBusinessImpl();
		return commentBusiness.getComments(article).size();
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getReadtimesArticles(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleEntity> getReadtimesArticles(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不可为空！");
		}
		return articleDaoImpl.selectMaxReadArticles(blog);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getReadtimesArticles(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleEntity> getReadtimesArticles(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		return articleDaoImpl.selectMaxReadArticles(author);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getCommentArticles(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleEntity> getCommentArticles(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null  == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不允许为空！");
		}
		
		return articleDaoImpl.selectMaxCommentArticles(blog);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getCommentArticles(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleEntity> getCommentArticles(AuthorEntity author) throws NotAllowAttributeNull {
		if (null  == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		return articleDaoImpl.selectMaxCommentArticles(author);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleBusiness#getArticlesWithKeyword(java.lang.String)
	 */
	@Override
	public List<ArticleEntity> getArticlesWithKeyword(String keyword) throws NotAllowAttributeNull {
		
		if (null == keyword) {
			throw new NotAllowAttributeNull("关键字不能为空！");
		}
		
		return articleDaoImpl.selectArticlesWithKeyword(keyword);
		
	}
	
	
}
