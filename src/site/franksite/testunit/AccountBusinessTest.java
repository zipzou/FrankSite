package site.franksite.testunit;

import static org.junit.Assert.*;

import org.junit.Test;

import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.AccountBusiness;
import site.franksite.service.business.AccountBusinessImpl;

public class AccountBusinessTest {

	@Test
	public void testRegister() {
		AuthorEntity author = new AuthorEntity();
		author.setUsername("frank");
		author.setPassword("zouzhipeng123A");
		AccountBusiness authorBusiness = new AccountBusinessImpl();
		ResponseResultEntity registerResult = authorBusiness.register(author);
		if (!registerResult.getStatus()) {
			// 注册注册失败
			fail(registerResult.getReason());
		}
	}

	private AccountBusiness accountBusiness = new AccountBusinessImpl();
	@Test
	public void testLogin() {
//		fail("Not yet implemented");
		AuthorEntity author = new AuthorEntity();
		author.setUsername("frank");
		author.setPassword("zouzhipeng123A");
		ResponseResultEntity loginResult = accountBusiness.login(author);
		if (!loginResult.getStatus()) {
			// 登录失败
			fail("登录失败：" + loginResult.getReason());
		}
	}

	@Test
	public void testLogout() {
	}

	@Test
	public void testChangeEmail() {
//		AuthorEntity author = new AuthorEntity();
//		author.setUsername("test");
//		author.setEmail("zouzhipeng.1@163.com");
//		author.setEmailvalidation(false);
//		AccountBusiness authorBusiness = new AccountBusinessImpl();
//		ResponseResultEntity result = authorBusiness.changeEmail(author);
//		if (!result.getStatus()) {
//			fail(result.getReason());
//		}
	}

	@Test
	public void testChangeMobile() {
//		AuthorEntity author = new AuthorEntity();
//		author.setUsername("test");
//		author.setMobile("15070378591");
//		AccountBusiness authorBusiness = new AccountBusinessImpl();
//		ResponseResultEntity result = authorBusiness.changeMobile(author);
//		if (!result.getStatus()) {
//			fail(result.getReason());
//		}
	}

}
