/**
 * 
 */
package site.franksite.testunit;

import static org.junit.Assert.*;

import org.junit.Test;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.BlogBusiness;
import site.franksite.service.business.BlogBusinessImpl;

/**
 * @author Frank
 *
 */
public class BlogBusinessTest {

	private BlogBusiness blogBusinessImpl = new BlogBusinessImpl();
	
	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#assignBlogToAuthor(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testAssignBlogToAuthor() {
	}

	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#createBlog(site.franksite.pojo.BlogEntity)}.
	 */
	@Test
	public void testCreateBlog() {
	}

	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#changeBlogSlogan(site.franksite.pojo.BlogEntity)}.
	 */
	@Test
	public void testChangeBlogSlogan() {
		BlogEntity blog = PojoBuilder.blog();
		blog.setBlogid(2l);
		blog.setSlogan("Code as a developer");
		try {
			ResponseResultEntity result = blogBusinessImpl.changeBlogSlogan(blog);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#changeBlogIndexMax(site.franksite.pojo.BlogEntity)}.
	 */
	@Test
	public void testChangeBlogIndexMax() {
		BlogEntity blog = PojoBuilder.blog();
		blog.setBlogid(2l);
		blog.setIndexmax(10l);
		try {
			ResponseResultEntity result = blogBusinessImpl.changeBlogIndexMax(blog);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#getBlog(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testGetBlogAuthorEntity() {
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		try {
			BlogEntity blog = blogBusinessImpl.getBlog(author);
			if (null == blog) {
				fail("未查询到博客，请查看日志");
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#getBlog(site.franksite.pojo.BlogEntity)}.
	 */
	@Test
	public void testGetBlogBlogEntity() {
		BlogEntity blog = PojoBuilder.blog();
		blog.setBlogid(2l);
		try {
			blog = blogBusinessImpl.getBlog(blog);
			if (null == blog) {
				fail("查找失败，请查看日志！");
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.BlogBusinessImpl#getBlog(site.franksite.pojo.ArticleTypeEntity)}.
	 */
	@Test
	public void testGetBlogArticleTypeEntity() {
		ArticleTypeEntity type = PojoBuilder.articletype();
		type.setTypeid(6);
		try {
			BlogEntity blog = blogBusinessImpl.getBlog(type);
			if (null == blog) {
				fail("查找失败，请查看日志！");
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
