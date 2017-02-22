/**
 * 
 */
package site.franksite.testunit;

import static org.junit.Assert.*;

import org.junit.Test;

import site.franksite.dao.AuthorDaoImpl;
import site.franksite.dao.interfaces.AuthorDao;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * @author Frank
 *
 */
public class DaoTest {

	/**
	 * Test method for {@link site.franksite.dao.AuthorDaoImpl#selectAuthor(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testSelectAuthor() {
		AuthorDao daoImpl = new AuthorDaoImpl();
		AuthorEntity account = new AuthorEntity();
		account.setUsername("test");
		account = daoImpl.selectAuthor(account);
		System.out.println(account.toString());
	}

	/**
	 * Test method for {@link site.franksite.dao.AuthorDaoImpl#updateAuthor(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testUpdateAuthor() {
//		fail("Not yet implemented");
		AuthorDao authorImpl = new AuthorDaoImpl();
		AuthorEntity author = new AuthorEntity();
		author.setUsername("test");
		
		author = authorImpl.selectAuthor(author);
		
		author.setMobile("15279104096");
		author.setMobilevalidation(true);
		
		ResponseResultEntity result = authorImpl.updateAuthor(author);
		if (!result.getStatus()) {
			fail(result.getReason());
		}
	}

	/**
	 * Test method for {@link site.franksite.dao.AuthorDaoImpl#insertAuthor(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testInsertAuthor() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link site.franksite.dao.AuthorDaoImpl#deleteAuthor(site.franksite.pojo.AuthorEntity)}.
	 */
	@Test
	public void testDeleteAuthor() {
//		fail("Not yet implemented");
	}

}
