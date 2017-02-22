/**
 * 
 */
package site.franksite.testunit;

import static org.junit.Assert.*;

import org.junit.Test;

import site.franksite.pojo.PojoBuilder;

/**
 * @author Frank
 *
 */
public class TestPojoBuilder {

	/**
	 * Test method for {@link site.franksite.pojo.PojoBuilder#buildSalt()}.
	 */
	@Test
	public void testBuildSalt() {
		String salt = PojoBuilder.buildSalt();
		salt = salt.toLowerCase();
		System.out.println(salt);
		for (int i = 0; i < salt.length(); i++) {
			if (!((salt.charAt(i) >= '0' && salt.charAt(i) <= '9') || (salt.charAt(i) >= 'a' && salt.charAt(i) <= 'z'))) {
				fail(salt + " : " + "出现非字符！");
				break;
			}
		}
	}

}
