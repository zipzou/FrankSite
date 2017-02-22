/**
 * 
 */
package site.franksite.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dbquery.util.Model;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.AbstractQuery;
import site.franksite.dao.interfaces.ArticleDao;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章数据库交互类
 * @author Frank
 *
 */
public class ArticleDaoImpl extends AbstractQuery implements ArticleDao {

	private static final Logger LOGGER = Logger.getLogger(ArticleDaoImpl.class);
	
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLE(ARTICLEID, TYPEID, TITLE, SHORTCUT, USERNAME, PUBLISHDATE, VISIABLE, READTIMES)"
			+ " VALUES(?, ?, ?, ?, ?, ?, 1, 0)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#insertArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity insertArticle(ArticleEntity article) {
		
		if (!insert(SQL_INSERT_ARTICLE, article.getArticleid(), article.getTypeid(), article.getTitle(), article.getShortcut(), article.getUsername(), 
				article.getPublishdate())) {
			LOGGER.warn("插入失败：" + article.getArticleid());
			return PojoBuilder.responseResult("插入失败：" + article.getArticleid());
		}
		
		LOGGER.info("插入成功：" + article.getArticleid());
		
		return PojoBuilder.responseResult();
	}

	private static final String SQL_QUERY_ARTICLES_WITH_AUTHOR = "SELECT * FROM ARTICLE WHERE USERNAME = ? AND VISIABLE = 1 ORDER BY PUBLISHDATE DESC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectArticles(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleEntity> selectArticles(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>(); // 结果容器
		
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_ARTICLES_WITH_AUTHOR, author.getUsername());
		
		if (null == articleMappers || articleMappers.isEmpty()) {
			LOGGER.warn("查询空集！");
			return articles;
		}
		
		for (Map<String, Object> map : articleMappers) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		
		LOGGER.info("共查询到：" + articles.size() + "条记录！");
		
		return articles;
	}

	private static final String SQL_QUERY_ARTICLES_WITH_BLOG = "SELECT * FROM ARTICLE WHERE TYPEID IN "
			+ "(SELECT TYPEID FROM ARTICLETYPE WHERE BLOGID = ?) AND VISIABLE = 1 ORDER BY PUBLISHDATE DESC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectArticles(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleEntity> selectArticles(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null == blog || null == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不允许为空！");
		}
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>(); // 文章容器
		
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_ARTICLES_WITH_BLOG, blog.getBlogid());
		
		if (null == articleMappers || articleMappers.isEmpty()) {
			// 空集合
			LOGGER.warn("查询为空集合！");
			return articles;
		}
		
		for (Map<String, Object> map : articleMappers) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class)); // 反映射
		}
		LOGGER.info("共查询到记录：" + articles.size() + "条。");
		return articles;
	}

	private static final String SQL_QUERY_ARTICLE_WITH_ARTICLETYPE = "SELECT * FROM ARTICLE WHERE TYPEID = ? AND VISIABLE = 1 ORDER BY PUBLISHDATE DESC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectArticles(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public List<ArticleEntity> selectArticles(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		
		if (null == articleType || null == articleType.getTypeid()) {
			throw new NotAllowAttributeNull("分类ID不允许为空！");
		}
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>();
		
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_ARTICLE_WITH_ARTICLETYPE, articleType.getTypeid());
		
		if (null == articleMappers || articleMappers.isEmpty()) {
			// 查询空集合
			LOGGER.warn("查询结果为空集合！");
			return articles;
		}
		
		for (Map<String, Object> map : articleMappers) {
			// 反映射
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		LOGGER.info("共查询到：" + articles.size() + "条。");
		
		return articles;
	}

	private static final String SQL_QUERY_ARTICLE_WITH_ID = "SELECT * FROM ARTICLE WHERE ARTICLEID = ? AND VISIABLE = 1 ORDER BY PUBLISHDATE DESC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ArticleEntity selectArticle(ArticleEntity article) throws NotAllowAttributeNull {
		
		if (null == article || null == article.getArticleid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		List<Map<String, Object>> articleMapper = select(SQL_QUERY_ARTICLE_WITH_ID, article.getArticleid());
		
		// 空集合
		if (null == articleMapper || articleMapper.isEmpty()) {
			LOGGER.warn("查询结果集合为空！");
			return null;
		}
		
		ArticleEntity resultArticle = (ArticleEntity) Model.parseObject(articleMapper.get(0), ArticleEntity.class);
		LOGGER.info("查询到文章：" + resultArticle.getArticleid());
		return resultArticle;
	}

	private static final String SQL_QUERY_ARTICLE_WITH_COMMENT = "SELECT * FROM ARTICLE WHERE VISIABLE = 1 AND ARTICLEID = (SELECT "
			+ "ARTICLEID FROM COMMENT WHERE COMMENTID = ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectArticle(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ArticleEntity selectArticle(CommentEntity comment) throws NotAllowAttributeNull {
		if (null == comment || null == comment.getCommentid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		List<Map<String, Object>> articleMapper = select(SQL_QUERY_ARTICLE_WITH_COMMENT, comment.getCommentid());
		
		// 空集合
		if (null == articleMapper || articleMapper.isEmpty()) {
			LOGGER.warn("查询结果集合为空！");
			return null;
		}
		
		ArticleEntity resultArticle = (ArticleEntity) Model.parseObject(articleMapper.get(0), ArticleEntity.class);
		LOGGER.info("查询到文章：" + resultArticle.getArticleid());
		return resultArticle;
	}

	private static final String SQL_UPDATE_ARTICLE_BAISC = "UPDATE ARTICLE SET VISIABLE = ?, READTIMES = ? WHERE ARTICLEID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#updateArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity updateArticle(ArticleEntity article) throws NotAllowAttributeNull {
		
		if (null == article || null == article.getArticleid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		if (update(SQL_UPDATE_ARTICLE_BAISC, article.getVisiable(), article.getReadtimes(), article.getArticleid())) {
			LOGGER.info("更新成功：" + article.getArticleid());
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("更新失败：" + article.getArticleid());
		return PojoBuilder.responseResult("文章更新失败：" + article.getArticleid());
	}

	private static final String SQL_DELETE_ARTICLE = "DELETE FROM ARTICLE WHERE ARTICLEID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#deleteArticle(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity deleteArticle(ArticleEntity article) throws NotAllowAttributeNull {
		
		// 注：本删除接口只适用于实际的实体删除，不考虑参照完整性约束，若存在约束，请根据业务需要，移除后再进行删除
		
		if (null == article || null == article.getArticleid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		int count = delete(SQL_DELETE_ARTICLE, article.getArticleid());
		
		if (0 >= count) {
			// 未删除
			LOGGER.warn("已删除：" + count);
		} else {
			LOGGER.info("已删除：" + count);
		}
		
		return PojoBuilder.responseResult();
	}
	private static final String SQL_UPDATE_ARTICLE_UNVISIABLE = "UPDATE ARTICLE SET VISIABLE = 0 WHERE ARTICLEID = ? AND TYPEID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#markArticleUnvisiable(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public ResponseResultEntity markArticleUnvisiable(ArticleEntity article) throws NotAllowAttributeNull {
		
		if (null == article || null == article.getArticleid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		// 更新
		if (update(SQL_UPDATE_ARTICLE_UNVISIABLE, article.getArticleid(), article.getTypeid())) {
			LOGGER.info("更新成功，文章不可见：" + article.getArticleid());
			return PojoBuilder.responseResult();
		}
		LOGGER.warn("更新失败，文章可见：" + article.getArticleid());
		return PojoBuilder.responseResult("标记失败，文章可见：" + article.getArticleid());
	}
	
	private static final String SQL_QUERY_HOT_ARTICLE_WITH_BLOG = "SELECT * FROM ARTICLE WHERE VISIABLE = 1 AND USERNAME = ("
			+ "SELECT USERNAME FROM AUTHOR WHERE BLOGID = ?) ORDER BY READTIMES DESC LIMIT 10";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectMaxReadArticles(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleEntity> selectMaxReadArticles(BlogEntity blog) {
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>();
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_HOT_ARTICLE_WITH_BLOG, blog.getBlogid());
		for (Map<String, Object> map : articleMappers) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		return articles;
	}
	
	private static final String SQL_QUERY_HOT_ARTICLE_WITH_AUTHOR = "SELECT * FROM ARTICLE WHERE VISIABLE = 1 AND"
			+ " USERNAME = ? ORDER BY READTIMES DESC LIMIT 10";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectMaxReadArticles(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleEntity> selectMaxReadArticles(AuthorEntity author) {
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>();
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_HOT_ARTICLE_WITH_AUTHOR, author.getUsername());
		for (Map<String, Object> map : articleMappers) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		
		return articles;
	}
	
	private static final String SQL_QUERY_COMMENT_ARTICLES_WITH_BLOG = "select * from article where username "
			+ "in (select username from author where blogid = ?)"+
					"order by (select count(articleid) from comment where `comment`.articleid "
					+ "= article.articleid) desc limit 10;";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectMaxCommentArticles(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleEntity> selectMaxCommentArticles(BlogEntity blog) {
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>();
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_COMMENT_ARTICLES_WITH_BLOG, blog.getBlogid());
		
		for (Map<String, Object> map : articleMappers) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		
		return articles;
	}
	
	private static final String SQL_QUERY_COMMENT_ARTICLES_WITH_AUTHOR = "select * from article where username = ?" +
			"order by (select count(articleid) from comment where `comment`.articleid = article.articleid) desc limit 10;";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectMaxCommentArticles(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleEntity> selectMaxCommentArticles(AuthorEntity author) {

		List<ArticleEntity> articles = new LinkedList<ArticleEntity>();
		List<Map<String, Object>> articleMappers = select(SQL_QUERY_COMMENT_ARTICLES_WITH_AUTHOR, author.getUsername());
		
		for (Map<String, Object> map : articleMappers) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		
		return articles;
		
	}
	
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleDao#selectArticlesWithKeyword(java.lang.String)
	 */
	@Override
	public List<ArticleEntity> selectArticlesWithKeyword(String keyword) {
		
		String sql = "SELECT * FROM ARTICLE WHERE VISIABLE = 1 AND TITLE LIKE '%" + keyword + "%'" + " OR SHORTCUT LIKE '%" + 
		 keyword + "%' ";
		
		List<ArticleEntity> articles = new LinkedList<ArticleEntity>();
		List<Map<String, Object>> articleMapper = select(sql, new Object[0]);
		for (Map<String, Object> map : articleMapper) {
			articles.add((ArticleEntity) Model.parseObject(map, ArticleEntity.class));
		}
		
		return articles;
	}

}
