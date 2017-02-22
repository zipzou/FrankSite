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
import site.franksite.dao.interfaces.ArticleTypeDao;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章分类数据库交互
 * @author Frank
 *
 */
public class ArticleTypeDaoImpl extends AbstractQuery implements ArticleTypeDao {

	private final static Logger LOGGER = Logger.getLogger(ArticleTypeDaoImpl.class);
	
	private static final String SQL_INSERT_ARTICLE_TYPE = "INSERT INTO ARTICLETYPE(TYPEID, BLOGID, TITLE, NOTE, CREATEDATE, "
			+ "VISIABLE) VALUES(NULL, ?, ?, ?, ?, ?)";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#insertArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity insertArticleType(ArticleTypeEntity articleType) {
		
		if (insert(SQL_INSERT_ARTICLE_TYPE, articleType.getBlogid(), articleType.getTitle(), articleType.getNote(), articleType.getCreatedate(), 
				articleType.getVisiable())) {
			// 插入成功
			LOGGER.info("插入成功，分类标题为：" + articleType.getTitle() + ", 博客ID为：" + articleType.getBlogid());
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("插入失败，分类标题为：" + articleType.getTitle());
		return PojoBuilder.responseResult("插入失败，分类标题为：" + articleType.getTitle());
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#markArticleTypeUnvisiable(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity markArticleTypeUnvisiable(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		
		if (null == articleType || null == articleType.getTypeid()) {
			throw new NotAllowAttributeNull("不允许分类ID为空！");
		}
		
		// 查询原始的分类
		ArticleTypeEntity storedType = selectArticleType(articleType);
		if (null == storedType) {
			LOGGER.warn("该分类不存在：" + articleType.getTypeid());
			return PojoBuilder.responseResult("该分类不存在：" + articleType.getTypeid());
		}
		LOGGER.info("要隐藏的分类为：" + storedType.getTitle() + "ID为：" + storedType.getTypeid() + "，准备隐藏该分类……");
		// 标记指定的分类
		storedType.setVisiable(false);
		return updateArticleType(storedType);
	}

	private static final String SQL_QUERY_ARTICLE_TYPES_WITH_AUTHOR = "SELECT * FROM ARTICLETYPE WHERE VISIABLE = 1 AND BLOGID = ("
			+ "SELECT BLOGID FROM AUTHOR WHERE USERNAME = ?) ORDER BY CREATEDATE DESC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#selectArticleTypes(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleTypeEntity> selectArticleTypes(AuthorEntity author) throws NotAllowAttributeNull {
		
		if (null == author || null == author.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		List<ArticleTypeEntity> types = new LinkedList<ArticleTypeEntity>();
		
		List<Map<String, Object>> typeMappers = select(SQL_QUERY_ARTICLE_TYPES_WITH_AUTHOR, author.getUsername());
		if (null == typeMappers || typeMappers.isEmpty()) {
			// 查询空集
			LOGGER.warn("查询到空集，用户名为：" + author.getUsername());
			return types;
		}
		
		LOGGER.info("找到分类：" + typeMappers.size() + "条");
		for (Map<String, Object> map : typeMappers) {
			types.add((ArticleTypeEntity)Model.parseObject(map, ArticleTypeEntity.class)); // 添加
		}
		
		return types;
	}

	private static final String SQL_QUERY_ARTICLE_TYPES_WITH_BLOG = "SELECT * FROM ARTICLETYPE WHERE VISIABLE = 1 AND BLOGID = ? ORDER BY CREATEDATE DESC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#selectArticleTypes(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleTypeEntity> selectArticleTypes(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null == blog || null == blog.getBlogid()) {
			// 空参数
			throw new NotAllowAttributeNull("博客ID不允许为空！");
		}
		
		// 查询结果容器
		List<ArticleTypeEntity> types = new LinkedList<ArticleTypeEntity>();
		
		// 查询
		List<Map<String, Object>> typeMappers = select(SQL_QUERY_ARTICLE_TYPES_WITH_BLOG, blog.getBlogid());
		
		if (null == typeMappers || typeMappers.isEmpty()) {
			// 空集
			LOGGER.warn("查询到空集，博客ID为：" + blog.getBlogid());
			return types;
		}
		
		LOGGER.info("查询到分类：" + typeMappers.size() + "条");
		// 反映射
		for (Map<String, Object> map : typeMappers) {
			types.add((ArticleTypeEntity)Model.parseObject(map, ArticleTypeEntity.class));
		}
		
		return types;
	}

	private static final String SQL_QUERY_ARTICLE_TYPE_WITH_ID = "SELECT * FROM ARTICLETYPE WHERE VISIABLE = 1 AND TYPEID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#selectArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ArticleTypeEntity selectArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		
		if (null == articleType || null == articleType.getTypeid()) {
			throw new NotAllowAttributeNull("不允许分类ID为空！");
		}
		
		// 查找
		List<Map<String, Object>> typeMapper = select(SQL_QUERY_ARTICLE_TYPE_WITH_ID, articleType.getTypeid());
		
		// 空集
		if (null == typeMapper || typeMapper.isEmpty()) {
			LOGGER.warn("未查询到指定的分类，分类ID为：" + articleType.getTypeid());
			return null;
		}

		ArticleTypeEntity type = (ArticleTypeEntity) Model.parseObject(typeMapper.get(0), ArticleTypeEntity.class);
		LOGGER.info("查询到分类：" + type.getTitle());
		
		return type;
	}

	private static final String SQL_UPDATE_ARTICLE_TYPE = "UPDATE ARTICLETYPE SET TITLE = ?, VISIABLE = ?, BLOGID = ? WHERE TYPEID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#updateArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity updateArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull {

		if (null == articleType || null == articleType.getTypeid()) {
			throw new NotAllowAttributeNull("不允许分类ID为空！");
		}
		
		// 更新
		if (update(SQL_UPDATE_ARTICLE_TYPE, articleType.getTitle(),articleType.getVisiable(), articleType.getBlogid() , articleType.getTypeid())) {
			LOGGER.info("更新成功，更新的分类为：" + articleType.getTypeid());
			return PojoBuilder.responseResult();
		}
		
		// 失败
		LOGGER.warn("更新分类失败，分类ID为：" + articleType.getTypeid());
		
		return PojoBuilder.responseResult("更新分类失败，分类ID为：" + articleType.getTypeid());
	}

	private static final String SQL_DELETE_ARTICLE_TYPE = "DELETE FROM ARTICLETYPE WHERE TYPEID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.ArticleTypeDao#deleteArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity deleteArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		// 注：该删除操作，是对实际实体记录的删除，不考虑参照完整性，若有外键约束，请根据业务进行移除
		
		if (null == articleType || null == articleType.getTypeid()) {
			throw new NotAllowAttributeNull("不允许分类ID为空！");
		}
		
		// 删除
		int count = delete(SQL_DELETE_ARTICLE_TYPE, articleType.getTypeid());
		if (0 >= count) {
			// 未删除记录
			LOGGER.warn("未删除记录！");
		}
		
		LOGGER.info("共删除记录：" + count + "条！");
		
		return PojoBuilder.responseResult();
	}

}
