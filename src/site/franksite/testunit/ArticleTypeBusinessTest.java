/**
 * 
 */
package site.franksite.testunit;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.ArticleTypeBusiness;
import site.franksite.service.business.ArticleTypeBusinessImpl;

/**
 * @author Frank
 *
 */
public class ArticleTypeBusinessTest {

	private ArticleTypeBusiness articleTypeBusiness = new ArticleTypeBusinessImpl();
	/**
	 * Test method for {@link site.franksite.service.business.ArticleTypeBusinessImpl#createArticleType(site.franksite.pojo.AuthorEntity, site.franksite.pojo.ArticleTypeEntity)}.
	 */
	@Test
	public void testCreateArticleType() {
		ArticleTypeEntity articleType = PojoBuilder.articletype();
		articleType.setTitle("测试类别");
		articleType.setVisiable(true);
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		articleTypeBusiness.createArticleType(author, articleType);
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleTypeBusinessImpl#hideArticleType(site.franksite.pojo.AuthorEntity, site.franksite.pojo.ArticleTypeEntity)}.
	 */
	@Test
	public void testHideArticleType() {
		ArticleTypeEntity type = PojoBuilder.articletype();
		type.setTypeid(6);
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		try {
			ResponseResultEntity result = articleTypeBusiness.hideArticleType(author, type);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleTypeBusinessImpl#changeArticleTypeTitle(site.franksite.pojo.AuthorEntity, site.franksite.pojo.ArticleTypeEntity)}.
	 */
	@Test
	public void testChangeArticleTypeTitle() {
		ArticleTypeEntity articleType = PojoBuilder.articletype();
		articleType.setTypeid(10);
		articleType.setTitle("测试修改类别");
		articleType.setVisiable(true);
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		try {
			ResponseResultEntity result = articleTypeBusiness.changeArticleTypeTitle(author, articleType);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleTypeBusinessImpl#getArticleTypes(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testGetArticleTypesAuthorEntity() {
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		try {
			List<ArticleTypeEntity> types = articleTypeBusiness.getArticleTypes(author);
			for (ArticleTypeEntity articleTypeEntity : types) {
				System.out.println(articleTypeEntity.toString());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleTypeBusinessImpl#getArticleType(site.franksite.pojo.ArticleTypeEntity)}.
	 */
	@Test
	public void testGetArticleType() {
		ArticleTypeEntity type = PojoBuilder.articletype();
		type.setTypeid(7);
		try {
			type = articleTypeBusiness.getArticleType(type);
			if (null == type) {
				fail("查找出错！");
			}
			System.out.println(type);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.ArticleTypeBusinessImpl#getArticleTypes(site.franksite.pojo.BlogEntity)}.
	 */
	@Test
	public void testGetArticleTypesBlogEntity() {
		BlogEntity blog = PojoBuilder.blog();
		blog.setBlogid(2l);
		try {
			List<ArticleTypeEntity> types = articleTypeBusiness.getArticleTypes(blog);
			for (ArticleTypeEntity articleTypeEntity : types) {
				System.out.println(articleTypeEntity.toString());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
