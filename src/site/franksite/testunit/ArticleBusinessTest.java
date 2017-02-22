/**
 * 
 */
package site.franksite.testunit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.ArticleBusiness;
import site.franksite.service.business.ArticleBusinessImpl;

/**
 * @author Frank
 *
 */
public class ArticleBusinessTest {

	private ArticleBusiness articleBusiness = new ArticleBusinessImpl();
	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#publishArticle(site.franksite.pojo.ArticleEntity)}.
	 */
	@Test
	public void testPublishArticle() {
		
		ArticleEntity article = PojoBuilder.article();
		article.setUsername("frank");
		article.setTitle("测试标题");
		article.setArticleid(PojoBuilder.generateTimeStringId());
		article.setTypeid(2);
		
		try {
			articleBusiness.publishArticle(article);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#hideArticle(site.franksite.pojo.ArticleEntity)}.
	 */
	@Test
	public void testHideArticle() {
		ArticleEntity article = PojoBuilder.article();
		article.setUsername("frank");
		article.setTypeid(2);
		article.setArticleid("20170122224718173");
		try {
			ResponseResultEntity result = articleBusiness.hideArticle(article);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#getArticles(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testGetArticlesAuthorEntity() {
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		try {
			List<ArticleEntity> articles = articleBusiness.getArticles(author);
			for (ArticleEntity articleEntity : articles) {
				System.out.println(articleEntity.toString());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#getArticles(site.franksite.pojo.BlogEntity)}.
	 */
	@Test
	public void testGetArticlesBlogEntity() {
		BlogEntity blog = PojoBuilder.blog();
		blog.setBlogid(2l);
		
		try {
			List<ArticleEntity> articles = articleBusiness.getArticles(blog);
			for (ArticleEntity articleEntity : articles) {
				System.out.println(articleEntity.toString());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#getArticles(site.franksite.pojo.ArticleTypeEntity)}.
	 */
	@Test
	public void testGetArticlesArticleTypeEntity() {
		ArticleTypeEntity articleType = PojoBuilder.articletype();
		articleType.setTypeid(2);
		try {
			List<ArticleEntity> articles = articleBusiness.getArticles(articleType);
			for (ArticleEntity articleEntity : articles) {
				System.out.println(articleEntity.toString());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#getArticle(site.franksite.pojo.ArticleEntity)}.
	 */
	@Test
	public void testGetArticleArticleEntity() {
		
		ArticleEntity article = PojoBuilder.article();
		article.setArticleid("20170122224755777");
		try {
			article = articleBusiness.getArticle(article);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		if (null == article) {
			fail("获取失败！");
		}
		
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#getArticle(site.franksite.pojo.CommentEntity)}.
	 */
	@Test
	public void testGetArticleCommentEntity() {
		
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#increaseArticleReadTime(site.franksite.pojo.ArticleEntity)}.
	 */
	@Test
	public void testIncreaseArticleReadTime() {
		ArticleEntity article = PojoBuilder.article();
		article.setArticleid("20170122224755777");
		try {
			ResponseResultEntity result = articleBusiness.increaseArticleReadTime(article);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleBusinessImpl#decreaseArticleReadTime(site.franksite.pojo.ArticleEntity)}.
	 */
	@Test
	public void testDecreaseArticleReadTime() {
		ArticleEntity article = PojoBuilder.article();
		article.setArticleid("20170122224755777");
		try {
			ResponseResultEntity result = articleBusiness.decreaseArticleReadTime(article);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
