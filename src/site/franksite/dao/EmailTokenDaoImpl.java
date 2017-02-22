/**
 * 
 */
package site.franksite.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dbquery.util.Model;
import com.sun.istack.NotNull;

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
public class EmailTokenDaoImpl extends AbstractQuery implements EmailTokenDao {

	private static final Logger LOGGER = Logger.getLogger(EmailTokenDaoImpl.class);
	private static final String SQL_QUERY_USEFUL_EMAIL_TOKEN = "SELECT * FROM EMAILTOKEN WHERE USERNAME = ? AND USEFUL = 1";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#selectUsefulEmailToken(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public EmailTokenEntity selectUsefulEmailToken(AuthorEntity author) {
		List<Map<String, Object>> objMappers = select(SQL_QUERY_USEFUL_EMAIL_TOKEN, author.getUsername());
		if (null == objMappers || objMappers.isEmpty()) {
			LOGGER.warn("该用户无可用的令牌！");
			return null;
		}
		EmailTokenEntity token = (EmailTokenEntity)Model.parseObject(objMappers.get(0), EmailTokenEntity.class);
		LOGGER.info("令牌已找到:" + token.getTokenid());
		return token;
	}

	private static final String SQL_QUERY_ALL_TOKENS = "SELECT * FROM EMAILTOKEN WHERE USERNAME = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#selectEmailTokens(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<EmailTokenEntity> selectEmailTokens(AuthorEntity author) {
		List<Map<String, Object>> objMappers = select(SQL_QUERY_ALL_TOKENS, author.getUsername());
		List<EmailTokenEntity> tokens = new LinkedList<EmailTokenEntity>();
		// 判断是否存在
		if (null == objMappers || objMappers.isEmpty()) {
			LOGGER.warn("该用户无可用的令牌");
		}
		
		for (Map<String,Object> map : objMappers) {
			tokens.add((EmailTokenEntity)Model.parseObject(map, EmailTokenEntity.class));
		}
		LOGGER.info("查询到令牌共计：" + tokens.size() + "条");
		return tokens;
	}

	private static final String SQL_QUERY_EMAIL_TOKEN_WITH_ID = "SELECT * FROM EMAILTOKEN WHERE TOKENID = ? OR TOKEN = ? AND USEFUL = 1";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#selectEmailToken(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public EmailTokenEntity selectEmailToken(@NotNull EmailTokenEntity tokenEntity) {
		// 查询
		List<Map<String, Object>> tokenMappers = select(SQL_QUERY_EMAIL_TOKEN_WITH_ID, tokenEntity.getTokenid(), tokenEntity.getToken());
		if (null == tokenMappers || tokenMappers.isEmpty()) {
			LOGGER.warn("未查询到令牌：" + tokenEntity.getTokenid());
			return null;
		}
		EmailTokenEntity token = (EmailTokenEntity)Model.parseObject(tokenMappers.get(0), EmailTokenEntity.class);
		LOGGER.info("找到token: " + token.getTokenid() + " " + token.getToken());
		return token;
	}

	private static final String SQL_UPDATE_EMAIL_TOKEN_DEL = "UPDATE EMAILTOKEN SET USEFUL = 0 WHERE TOKENID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#markTokenDel(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public ResponseResultEntity markTokenDel(EmailTokenEntity tokenEntity) {
		
		if (update(SQL_UPDATE_EMAIL_TOKEN_DEL, tokenEntity.getTokenid())) {
			LOGGER.info("更新成功:" + tokenEntity.getTokenid());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("更新失败：" + tokenEntity.getTokenid());
		
		return PojoBuilder.responseResult("更新失败：" + tokenEntity.getTokenid());
	}

	private static final String SQL_UPDATE_ALL_TOKENS_DEL = "UPDATE EMAILTOKEN SET USEFUL = 0 WHERE USERNAME = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#markTokenDel(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity markTokenDel(AuthorEntity author) {
		
		if (update(SQL_UPDATE_ALL_TOKENS_DEL, author.getUsername())) {
			LOGGER.info("更新成功：" + author.getUsername());
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("更新失败：" + author.getUsername());
		
		return PojoBuilder.responseResult("更新失败：" + author.getUsername());
	}

	private static final String SQL_INSERT_EMAIL_TOKEN = "INSERT INTO EMAILTOKEN(TOKENID, USERNAME, TOKEN, USEFUL, DATE) "
			+ "VALUES (NULL, ?, ?, 1, ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#insertToken(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public ResponseResultEntity insertToken(EmailTokenEntity tokenEntity) {
		
		// 插入
		if (insert(SQL_INSERT_EMAIL_TOKEN, tokenEntity.getUsername(), tokenEntity.getToken(), new Date())) {
			LOGGER.info("插入成功：" + tokenEntity.getUsername());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("插入失败：" + tokenEntity.getUsername());
		
		return PojoBuilder.responseResult("插入失败：" + tokenEntity.getUsername());
	}

	private static final String SQL_DELETE_EMAIL_TOKEN_WITH_ID = "DELETE FROM EMAILTOKEN WHERE TOKENID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.EmailTokenBusiness#deleteTokenEntity(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public ResponseResultEntity deleteTokenEntity(EmailTokenEntity tokenEntity) {
		if (delete(SQL_DELETE_EMAIL_TOKEN_WITH_ID, tokenEntity.getTokenid()) > 0) {
			LOGGER.info("已删除记录：" + tokenEntity.getTokenid());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("未删除记录：" + tokenEntity.getTokenid());
		return PojoBuilder.responseResult("删除记录失败：" + tokenEntity.getTokenid());
	}

}
