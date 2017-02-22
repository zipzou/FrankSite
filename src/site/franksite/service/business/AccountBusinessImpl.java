/**
 * 
 */
package site.franksite.service.business;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import site.franksite.dao.AuthorDaoImpl;
import site.franksite.dao.EmailTokenDaoImpl;
import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.AuthorDao;
import site.franksite.dao.interfaces.EmailTokenDao;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.EmailTokenEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 账户实现类
 * @author Frank
 *
 */
public class AccountBusinessImpl implements AccountBusiness {

	private static final Logger LOGGER = Logger.getLogger(AccountBusinessImpl.class);
	
	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#register(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity register(AuthorEntity account) {
		// 盐值
		String salt = PojoBuilder.buildSalt();
		account.setSalt(salt);
		String md5Str = DigestUtils.md5Hex(salt + account.getPassword());
		account.setPassword(md5Str);
		
		LOGGER.info("盐值：" + salt + "\tMD5密码：" + md5Str);
		
		// 是否存在该用户
		AuthorDao authorDaoImpl = new AuthorDaoImpl();
		if (null == authorDaoImpl.selectAuthor(account)) {
			LOGGER.info("用户不存在，可以新增！");
			ResponseResultEntity result = authorDaoImpl.insertAuthor(account); // 插入到数据库
			if (!result.getStatus()) {
				// 未插入成功
				LOGGER.warn("新增用户失败：" + account.getUsername());
				return PojoBuilder.responseResult("新增用户失败：" + account.getUsername());
			} else {
				// 插入成功
				LOGGER.info("用户注册成功，为其分配一个博客：" + account.getUsername());
				
				BlogBusiness blogBusiness = new BlogBusinessImpl();
				
				ResponseResultEntity blogAssignResult;
				try {
					blogAssignResult = blogBusiness.assignBlogToAuthor(account);
				} catch (NotAllowAttributeNull e) {
					e.printStackTrace();
					LOGGER.error("用户名不可为空值，检查空引用！");
					return PojoBuilder.responseResult("异常的空值！");
				}
				if (!blogAssignResult.getStatus()) {
					// 插入博客失败
					LOGGER.warn("博客安排失败，无法完成注册，即将删除用户：" + account.getUsername());
					
					// 删除该用户
					authorDaoImpl.deleteAuthor(account);
					
					return PojoBuilder.responseResult("博客产生失败，无法完成注册！");
				} else {
					// 博客插入成功，更新用户信息
//					LOGGER.info("博客插入成功，准备更新用户信息，username：" + account.getUsername());
					LOGGER.info("成功为用户分配博客，注册完成。用户名：" + account.getUsername());
					return blogAssignResult;
//					blog = blogImpl.selectLatestBlog(); // 查找最新的博客
//					account.setBlogid(blog.getBlogid());
//					// 更新信息
//					ResponseResultEntity updateAuthorResult = authorDaoImpl.updateAuthor(account);
//					if (!updateAuthorResult.getStatus()) {
//						LOGGER.warn("未更新成功，即将删除用户：" + account.getUsername());
//						authorDaoImpl.deleteAuthor(account);
//						return PojoBuilder.responseResult("无法为用户分配博客，注册失败！");
//					} else {
//						
//						return PojoBuilder.responseResult();
//					}
				}
			}
		} else {
			LOGGER.warn("用户已存在，新增失败！");
			return PojoBuilder.responseResult("该用户已经存在！");
		}
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#login(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity login(AuthorEntity account) {
		
		AuthorDao authorDaoImpl = new AuthorDaoImpl();
		
		// 查询用户
		AuthorEntity storedAuthor = authorDaoImpl.selectAuthor(account);
		
		// 是否存在
		if (null == storedAuthor) {
			LOGGER.info("用户不存在，无法登录！");
			return PojoBuilder.responseResult("该用户不存在！");
		}
		
		// 检验密码
		String salt = storedAuthor.getSalt();
		String encrptStr = DigestUtils.md5Hex(salt + account.getPassword());
		LOGGER.info("已存密文：" + storedAuthor.getPassword() + "\t提交密码：" + encrptStr);
		if (encrptStr.equals(storedAuthor.getPassword().trim())) {
			LOGGER.info("匹配成功");
			return PojoBuilder.responseResult();
		}
		LOGGER.info("用户名与密码不匹配！");
		return PojoBuilder.responseResult("用户名与密码不匹配！");
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#logout(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity logout(AuthorEntity account) {
		AuthorDao daoImpl = new AuthorDaoImpl();
		
		AuthorEntity storedAuthor  = daoImpl.selectAuthor(account);
		
		if (null == storedAuthor) {// 用户存在
			LOGGER.warn("已知用户不存在:" + account.getUsername());
			return PojoBuilder.responseResult("用户不存在：" + account.getUsername());
		}
		ResponseResultEntity res = PojoBuilder.responseResult();
		res.setReason("用户存在！");
		return res;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#changeEmail(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity changeEmail(AuthorEntity author) {
		
		AuthorDao authorDaoImpl = new AuthorDaoImpl(); // 数据库交互
		AuthorEntity storedAuthor = authorDaoImpl.selectAuthor(author);
		if (null == storedAuthor) {
			LOGGER.warn("用户名不存在：" + author.getUsername());
			return PojoBuilder.responseResult("用户名不存在：" + author.getUsername());
		}
		
		storedAuthor.setEmail(author.getEmail());
		storedAuthor.setEmailvalidation(false);
		author = storedAuthor;
		
		// 将用户可用令牌全部置为不可用
		EmailTokenDao tokenDaoImpl = new EmailTokenDaoImpl();
		ResponseResultEntity result = tokenDaoImpl.markTokenDel(author);
		if (!result.getStatus()) {
			// 无法置为不可用
			LOGGER.warn("无法置其他有效令牌为无效：" + result.getReason());
			return PojoBuilder.responseResult("无法置其他有效令牌为无效：" + result.getReason()); 
		}
		
		// 更新用户邮件地址
		EmailTokenEntity token = PojoBuilder.emailtoken();
		token.setToken(PojoBuilder.buildTokenContent());
		token.setUsername(author.getUsername());
		token.setUseful(true);
		token.setDate(new java.sql.Timestamp(new Date().getTime()));
		
		result = tokenDaoImpl.insertToken(token); // 插入结果
		if (result.getStatus()) {
			LOGGER.info("插入令牌成功，可以更新邮件地址！");
			author.setEmailvalidation(false); // 待验证
			result = authorDaoImpl.updateAuthor(author); // 更新邮件地址
			if (result.getStatus()) {
				LOGGER.info("用户邮件修改成功，新邮件待验证：" + author.getEmail());
				return PojoBuilder.responseResult();
			} else {
				LOGGER.warn("用户邮件地址修改失败：" + result.getReason());
				return PojoBuilder.responseResult("用户邮件地址修改失败：" + result.getReason());
			}
		} else {
			LOGGER.warn("插入令牌失败，后续操作无法进行：" + result.getReason());
			return PojoBuilder.responseResult("插入令牌失败，后续操作无法进行：" + result.getReason());
		}
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#changeMobile(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity changeMobile(AuthorEntity author) {
		AuthorDao authorImpl = new AuthorDaoImpl();
		
		// 查找已有用户
		AuthorEntity storedAuthor = authorImpl.selectAuthor(author);
		if (null == storedAuthor) {
			LOGGER.warn("不存在该用户：" + author.getUsername());
			return PojoBuilder.responseResult("不存在该用户：" + author.getUsername());
		}
		
		storedAuthor.setMobile(author.getMobile());
		author = storedAuthor;
		author.setMobilevalidation(true);
		
		ResponseResultEntity result = authorImpl.updateAuthor(author); // 更新用户
		if (result.getStatus()) {
			// 更新成功
			LOGGER.info("更新用户成功：" + author.getUsername() + ", " + author.getMobile());
			return PojoBuilder.responseResult();
		} else {
			// 更新失败
			LOGGER.warn("更新失败：" + author.getUsername() + ", " + author.getMobile());
			return PojoBuilder.responseResult("更新失败：" + author.getUsername() + ", " + author.getMobile());
		}
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#authorBasicInfo(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public AuthorEntity authorBasicInfo(AuthorEntity author) throws NotAllowAttributeNull {
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		AuthorDao authorDaoImpl = new AuthorDaoImpl();
		AuthorEntity storedAuthor = authorDaoImpl.selectAuthor(author);
		
		if (null == storedAuthor) {
			LOGGER.error("用户名不存在！");
			return null;
		}
		
		LOGGER.info("用户名为：" + author.getUsername());
		return storedAuthor;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#changePassword(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity changePassword(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		if (null == author.getPassword()) {
			throw new NotAllowAttributeNull("密码不允许为空！");
		}
		
		AuthorDao authorDaoImpl = new AuthorDaoImpl();
		AuthorEntity storedAuthor = authorBasicInfo(author);
		
		if (null == storedAuthor) {
			LOGGER.warn("用户名不存在！");
			return PojoBuilder.responseResult("用户名不存在，无法修改密码！");
		}
		
		LOGGER.info("准备修改用户密码：" + author.getUsername());
		String encrptPassword = DigestUtils.md5Hex(storedAuthor.getSalt().trim() + author.getPassword().trim());
		storedAuthor.setPassword(encrptPassword);
		
		return authorDaoImpl.updateAuthor(storedAuthor);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.AccountBusiness#findPassword(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity findPassword(AuthorEntity author) throws NotAllowAttributeNull {
		// TODO Auto-generated method stub
		return null;
	}

}
