/**
 * 
 */
package site.franksite.service.business;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import site.franksite.dao.EmailTokenDaoImpl;
import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.AbstractQuery;
import site.franksite.dao.interfaces.EmailTokenDao;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.EmailTokenEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 邮件令牌业务类
 * @author Frank
 *
 */
public class EmailTokenBusinessImpl extends AbstractQuery implements EmailTokenBusiness {

	private static final Logger LOGGER = Logger.getLogger(EmailTokenBusinessImpl.class);
	
	private EmailTokenDao emailTokenDaoImpl = new EmailTokenDaoImpl();
	
	/* (non-Javadoc)
	 * @see site.franksite.service.business.EmailTokenBusiness#validateEmailToken(site.franksite.pojo.AuthorEntity, site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public ResponseResultEntity validateEmailToken(AuthorEntity author, EmailTokenEntity token)
			throws NotAllowAttributeNull {
		
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		if (null == token || null == token.getToken()) {
			throw new NotAllowAttributeNull("令牌不允许为空！");
		}
		
		EmailTokenEntity storedToken = emailTokenDaoImpl.selectEmailToken(token);
		
		// 是否过期
		if (48 * 3600 * 1000 < (new Date().getTime() - token.getDate().getTime())) {
			// 过期
			LOGGER.info("令牌已经过期，失效，验证失败！");
			return PojoBuilder.responseResult("该令牌已经过期失效！");
		}
		
		if (null == storedToken) {
			// 验证失败
			LOGGER.info("未查询到令牌，验证失败！");
			return PojoBuilder.responseResult("验证失败！");
		}
		
		if (!author.getUsername().trim().equals(storedToken.getUsername().trim())) {
			LOGGER.info("用户名不匹配，验证失败！");
			return PojoBuilder.responseResult("验证失败！");
		}
		
		return PojoBuilder.responseResult();
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.EmailTokenBusiness#usefulEmailTokens(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<EmailTokenEntity> usefulEmailTokens(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		LOGGER.info("准备查找有效令牌……");
		
		return emailTokenDaoImpl.selectEmailTokens(author);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.EmailTokenBusiness#invalidateEmailToken(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public ResponseResultEntity invalidateEmailToken(EmailTokenEntity token) throws NotAllowAttributeNull {
		
		if (null == token || null == token.getTokenid() || null == token.getToken()) {
			throw new NotAllowAttributeNull("邮件令牌不允许为空！");
		}
		
		LOGGER.info("准备失效令牌……");
		return emailTokenDaoImpl.markTokenDel(token);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.EmailTokenBusiness#createEmailToken(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public ResponseResultEntity createEmailToken(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		EmailTokenEntity token = PojoBuilder.emailtoken();
		token.setToken(PojoBuilder.buildTokenContent());
		token.setUsername(author.getUsername());
		token.setUseful(true);
		token.setDate(new java.sql.Timestamp(new Date().getTime()));
		
		LOGGER.info("准备插入邮件令牌……");
		return emailTokenDaoImpl.insertToken(token);
	}

}
