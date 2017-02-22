/**
 * 
 */
package site.franksite.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dbquery.util.Model;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.AbstractQuery;
import site.franksite.dao.interfaces.BlogDao;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 博客数据库交互
 * @author Frank
 *
 */
public class BlogDaoImpl extends AbstractQuery implements BlogDao {

	private static final Logger LOGGER = Logger.getLogger(BlogDaoImpl.class);
	
	private final static String SQL_QUERY_BLOG_WITH_AUTHOR = "SELECT * FROM BLOG WHERE BLOGID = (SELECT BLOGID FROM "
			+ "AUTHOR WHERE USERNAME = ?) ";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#selectBlog(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public BlogEntity selectBlog(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		List<Map<String, Object>> blogMappers = select(SQL_QUERY_BLOG_WITH_AUTHOR, author.getUsername());
		if (null == blogMappers || blogMappers.isEmpty()) {
			// 结果集为空
			LOGGER.warn("未查询到指定博客，所属用户为：" + author.getUsername());
			return null;
		}
		
		BlogEntity blog = (BlogEntity)Model.parseObject(blogMappers.get(0), BlogEntity.class); // 反映射
		LOGGER.info("查询到博客：" + blog.getBlogid() + "，所属用户为：" + author.getUsername());
		
		return blog;
	}

	private static final String SQL_QUERY_BLOG_WITH_ID = "SELECT * FROM BLOG WHERE BLOGID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#selectBlog(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public BlogEntity selectBlog(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null == blog || null == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不允许为空！");
		}
		
		// 查询
		List<Map<String, Object>> blogMappers = select(SQL_QUERY_BLOG_WITH_ID, blog.getBlogid());
		
		if (null == blogMappers || blogMappers.isEmpty()) {
			// 结果集为空
			LOGGER.warn("为查询到指定的博客，博客ID为：" + blog.getBlogid());
			return null;
		}
		
		// 反映射
		blog = (BlogEntity) Model.parseObject(blogMappers.get(0), BlogEntity.class);
		LOGGER.info("查询到博客：" + blog.getBlogid());
		return blog;
	}

	private static final String SQL_QUERY_LATEST_BLOG = "SELECT * FROM BLOG ORDER BY BLOGID DESC LIMIT 1";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#selectLatestBlog()
	 */
	@Override
	public BlogEntity selectLatestBlog() {
		
		List<Map<String, Object>> latestBlogMappers = select(SQL_QUERY_LATEST_BLOG, new Object[0]);
		
		if (null == latestBlogMappers || latestBlogMappers.isEmpty()) {
			// 结果集为空
			LOGGER.warn("最新的博客ID未找到！");
			return null;
		}
		
		// 反映射
		BlogEntity blog = (BlogEntity)Model.parseObject(latestBlogMappers.get(0), BlogEntity.class);
		LOGGER.info("查询到博客：" + blog.getBlogid());
		
		return blog;
	}

	private static final String SQL_INSERT_BLOG = "INSERT INTO BLOG(BLOGID, SLOGAN, INDEXMAX) VALUES(NULL, ?, ?) ";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#insertBlog(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public ResponseResultEntity insertBlog(BlogEntity blog) {

		if (insert(SQL_INSERT_BLOG, blog.getSlogan(), blog.getIndexmax())) {
			LOGGER.info("插入博客成功！");
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("博客插入失败！");
		return PojoBuilder.responseResult("博客插入失败！");
		
	}

	private static final String SQL_UPDATE_BLOG = "UPDATE BLOG SET SLOGAN = ?, INDEXMAX = ? WHERE BLOGID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#updateBlog(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public ResponseResultEntity updateBlog(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null == blog || null == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不可为空！");
		}
		
		// 更新
		if (update(SQL_UPDATE_BLOG, blog.getSlogan(), blog.getIndexmax(), blog.getBlogid())) {
			LOGGER.info("博客更新成功：" + blog.getBlogid());
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("博客更新失败：" + blog.getBlogid());
		return PojoBuilder.responseResult("博客更新失败：" + blog.getBlogid());
	}

	private static final String SQL_DELETE_BLOG = "DELETE FROM BLOG WHERE BLOGID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#deleteBlog(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public ResponseResultEntity deleteBlog(BlogEntity blog) throws NotAllowAttributeNull {
		// 注：该删除不考虑外键，请在删除之前确认外键约束已经移除完毕，否则将不可正常删除
		if (null == blog || null == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不允许为空！");
		}
		
		// 删除
		if (delete(SQL_DELETE_BLOG, blog.getBlogid()) <= 0) {
			LOGGER.warn("删除博客数量为0！");
			ResponseResultEntity result = PojoBuilder.responseResult();
			result.setReason("博客删除数量为0！");
			return result;
		}
		LOGGER.info("已删除博客，ID为：" + blog.getBlogid());
		return PojoBuilder.responseResult("已删除博客，ID为：" + blog.getBlogid());
	}
	
	private static final String SQL_SELECT_BLOG_WITH_ARTICLETYPE = "SELECT * FROM BLOG WHERE BLOGID = (SELECT "
			+ "BLOGID FROM ARTICLETYPE WHERE TYPEID = ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.BlogDao#selectBlog(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public BlogEntity selectBlog(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		
		if (null == articleType || null == articleType.getTypeid()) {
			throw new NotAllowAttributeNull("分类ID不允许为空！");
		}
		
		// 选择
		List<Map<String, Object>> blogMapper = select(SQL_SELECT_BLOG_WITH_ARTICLETYPE, articleType.getTypeid());
		
		if (null == blogMapper || blogMapper.isEmpty()) {
			// 查询结果为空
			LOGGER.warn("查询结果为空集！");
			return null;
		}
		
		BlogEntity blog = (BlogEntity) Model.parseObject(blogMapper.get(0), BlogEntity.class); // 反映射
		LOGGER.info("查询成功，博客ID：" + blog.getBlogid());
		
		return blog;
	}

}
