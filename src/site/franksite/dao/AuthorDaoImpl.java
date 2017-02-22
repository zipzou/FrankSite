/**
 * 
 */
package site.franksite.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dbquery.util.Model;

import site.franksite.dao.interfaces.AbstractQuery;
import site.franksite.dao.interfaces.AuthorDao;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.EmailTokenEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 作者用户数据库接口实现
 * @author Frank
 *
 */
public class AuthorDaoImpl extends AbstractQuery implements AuthorDao {
	
	private final static Logger LOGGER = Logger.getLogger(AuthorDaoImpl.class);
	
	private static final String SQL_QUERY_AUTHOR = "SELECT * FROM AUTHOR WHERE USERNAME = ? ";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#selectAuthor(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public AuthorEntity selectAuthor(AuthorEntity author) {
		// 查询
		List<Map<String, Object>> authorMappers = select(SQL_QUERY_AUTHOR, author.getUsername());
		if (null == authorMappers || authorMappers.isEmpty()) {
			LOGGER.warn("该用户在数据库中不存在，未查询到结果:" + author.getUsername());
			return null;
		}
		// 映射
		Map<String, Object> mapper = authorMappers.get(0);
		LOGGER.info("在数据库中找到指定用户：" + author.getUsername());
		return (AuthorEntity)Model.parseObject(mapper, AuthorEntity.class);
	}

	private static final String SQL_UPDATE_AUTHOR_ACCOUNT = "UPDATE AUTHOR SET BLOGID = ?, PASSWORD = ?, EMAIL = ?, MOBILE = ?, "
			+ "EMAILVALIDATION = ?, MOBILEVALIDATION = ? WHERE USERNAME = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#updateAuthor(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity updateAuthor(AuthorEntity author) {
		if (update(SQL_UPDATE_AUTHOR_ACCOUNT, author.getBlogid(), author.getPassword(), author.getEmail(), author.getMobile(), 
				author.getEmailvalidation(), author.getMobilevalidation(), author.getUsername())) {
			LOGGER.info("用户信息更新成功：" + author.getUsername());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("无法更新用户：" + author.getUsername());
		return PojoBuilder.responseResult("无法更新用户信息！");
	}

	private static final String SQL_INSERT_AUTHOR = "INSERT INTO AUTHOR(USERNAME, BLOGID, SALT, PASSWORD, "
			+ "EMAIL, MOBILE, EMAILVALIDATION, MOBILEVALIDATION) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#insertAuthor(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity insertAuthor(AuthorEntity author) {
		if (insert(SQL_INSERT_AUTHOR, author.getUsername(), author.getBlogid(), 
				author.getSalt(), author.getPassword(), author.getEmail(), 
				author.getMobile(), author.getEmailvalidation(), 
				author.getMobilevalidation())) {
			LOGGER.info("用户插入成功：" + author.getUsername());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("未能插入用户：" + author.getUsername());
		return PojoBuilder.responseResult("未能成功插入数据！");
	}

	private static final String SQL_DELETE_AUTHOR = "DELETE FROM AUTHOR WHERE USERNAME = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#deleteAuthor(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity deleteAuthor(AuthorEntity author) {
		if (delete(SQL_DELETE_AUTHOR, author.getUsername()) > 0) {
			LOGGER.info("已删除用户：" + author.getUsername());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("未删除指定用户：" + author.getUsername());
		return PojoBuilder.responseResult("未删除指定用户：" + author.getUsername());
	}
	private static final String SQL_QUERY_AUTHOR_WITH_BLOG = "SELECT * FROM AUTHOR WHERE BLOGID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#selectAuthor(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public AuthorEntity selectAuthor(BlogEntity blog) {
		
		List<Map<String, Object>> authorMapper = select(SQL_QUERY_AUTHOR_WITH_BLOG, blog.getBlogid());
		if (null == authorMapper || authorMapper.isEmpty()) {
			// 查询空集合
			LOGGER.warn("该博客不属于任何一个用户！");
			return null;
		}
		
		AuthorEntity author = (AuthorEntity) Model.parseObject(authorMapper.get(0), AuthorEntity.class);
		LOGGER.info("查询成功：" + author.getUsername());
		return author;
		
	}
	private static final String SQL_QUERY_AUTHOR_WITH_ARTICLETYPE = "SELECT * FROM AUTHOR WHERE BLOGID = ("
			+ "SELECT BLOGID FROM ARTICLETYPE WHERE TYPEID = ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#selectAuthor(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public AuthorEntity selectAuthor(ArticleTypeEntity articleType) {
		
		List<Map<String, Object>> authorMapper = select(SQL_QUERY_AUTHOR_WITH_ARTICLETYPE, articleType.getTypeid());
		
		if (null == authorMapper || authorMapper.isEmpty()) {
			// 查询空集合
			LOGGER.warn("该文章分类不与任何一个用户产生关系！");
			return null;
		}
		
		AuthorEntity author = (AuthorEntity) Model.parseObject(authorMapper.get(0), AuthorEntity.class);
		LOGGER.info("查询成功：" + author.getUsername());
		return author;
	}
	private static final String SQL_QUERY_AUTHOR_WITH_ARTICLE = "SELECT * FROM AUTHOR WHERE USERNAME = ("
			+ "SELECT USERNAME FROM ARTICLE WHERE ARTICLEID = ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#selectAuthor(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public AuthorEntity selectAuthor(ArticleEntity article) {
		
		List<Map<String, Object>> authorMapper = select(SQL_QUERY_AUTHOR_WITH_ARTICLE, article.getArticleid());
		
		if (null == authorMapper || authorMapper.isEmpty()) {
			// 查询空集合
			LOGGER.warn("该文章不与任何一个用户产生关系！");
			return null;
		}
		
		AuthorEntity author = (AuthorEntity) Model.parseObject(authorMapper.get(0), AuthorEntity.class);
		LOGGER.info("查询成功：" + author.getUsername());
		return author;
	}
	private static final String SQL_QUERY_AUTHOR_WITH_EMAILTOKEN = "SELECT * FROM AUTHOR WHERE USERNAME = ("
			+ "SELECT USERNAME FROM EMAILTOKEN WHERE TOKENID = ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.AuthorDao#selectAuthor(site.franksite.pojo.EmailTokenEntity)
	 */
	@Override
	public AuthorEntity selectAuthor(EmailTokenEntity token) {
		
		List<Map<String, Object>> authorMapper = select(SQL_QUERY_AUTHOR_WITH_EMAILTOKEN, token.getTokenid());
		
		if (null == authorMapper || authorMapper.isEmpty()) {
			// 查询空集合
			LOGGER.warn("该邮件验证令牌不与任何一个用户产生关系！");
			return null;
		}
		
		AuthorEntity author = (AuthorEntity) Model.parseObject(authorMapper.get(0), AuthorEntity.class);
		LOGGER.info("查询成功：" + author.getUsername());
		return author;
	}

}
