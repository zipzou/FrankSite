/**
 * 
 */
package site.franksite.service.business;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import site.franksite.dao.ArticleTypeDaoImpl;
import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.ArticleTypeDao;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 文章分类业务类
 * @author Frank
 *
 */
public class ArticleTypeBusinessImpl implements ArticleTypeBusiness {

	private static final Logger LOGGER = Logger.getLogger(ArticleTypeBusinessImpl.class);
	private ArticleTypeDao articleTypeDaoImpl = new ArticleTypeDaoImpl();
	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleTypeBusiness#createArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity createArticleType(AuthorEntity author, ArticleTypeEntity articleType) {
		
		BlogBusiness blogBusiness = new BlogBusinessImpl();
		// 查询到用户的博客
		try {
			BlogEntity blog = blogBusiness.getBlog(author);
			
			// 判断blog是否存在
			if (null == blog) {
				LOGGER.warn("该用户未分配一个博客！");
				return PojoBuilder.responseResult("该用户无权限创建文章分类！");
			}
			
			// 判断blog是否一致
			if (!blog.getBlogid().equals(articleType.getBlogid())) {
				// 不一致，无权限
				LOGGER.warn("该用户无权操作该博客：" + articleType.getBlogid() + "，将更换至博客：" + blog.getBlogid());
			}
			LOGGER.info("准备创建……");
			articleType.setBlogid(blog.getBlogid());

			// 设置日期
			articleType.setCreatedate(new Timestamp(new Date().getTime()));
			
			return articleTypeDaoImpl.insertArticleType(articleType);
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			LOGGER.error("发生参数的空值错误：" + e.getMessage());
			return PojoBuilder.responseResult(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleTypeBusiness#hideArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity hideArticleType(AuthorEntity author, ArticleTypeEntity articleType) throws NotAllowAttributeNull {

		BlogBusiness blogBusiness = new BlogBusinessImpl();
		// 查询到用户的博客
		BlogEntity blog = blogBusiness.getBlog(author);

		// 判断blog是否存在
		if (null == blog) {
			LOGGER.warn("该用户未分配一个博客！");
			return PojoBuilder.responseResult("该用户未分配一个博客，无权限隐藏文章分类！");
		}

		// 判断blog是否一致
		if (!blog.getBlogid().equals(articleType.getBlogid())) {
			// 不一致，无权限
			LOGGER.warn("该用户无权操作该博客：" + articleType.getBlogid() + "，将更换至博客：" + blog.getBlogid());
		}
		LOGGER.info("准备隐藏博客……");
		articleType.setBlogid(blog.getBlogid());
		return articleTypeDaoImpl.markArticleTypeUnvisiable(articleType);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleTypeBusiness#changeArticleTypeTitle(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ResponseResultEntity changeArticleTypeTitle(AuthorEntity author, ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		
		// 获取到分类
		ArticleTypeEntity storedType = getArticleType(articleType);
		
		// 更新
		storedType.setTitle(articleType.getTitle());
		ResponseResultEntity changeResult = articleTypeDaoImpl.updateArticleType(storedType);
		
		if (!changeResult.getStatus()) {
			LOGGER.warn("修改错误：" + changeResult.getReason());
		} else {
			LOGGER.info("修改成功：" + articleType.getTitle());
		}
		return changeResult;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleTypeBusiness#getAllArticleTypes(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public List<ArticleTypeEntity> getArticleTypes(AuthorEntity author) throws NotAllowAttributeNull {
		
		List<ArticleTypeEntity> types = articleTypeDaoImpl.selectArticleTypes(author); // 查询结果
		
		if (types.isEmpty()) {
			// 空结果
			LOGGER.info("结果为空！");
		} else {
			LOGGER.info("结果个数为：" + types.size());
		}
		
		return types;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleTypeBusiness#getArticleType(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public ArticleTypeEntity getArticleType(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		
		// 查询
		ArticleTypeEntity type = articleTypeDaoImpl.selectArticleType(articleType);
		
		if (null == type) {
			LOGGER.info("未获取到分类！");
		} else {
			LOGGER.info("获取到分类：" + type.toString());
		}
		return type;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.ArticleTypeBusiness#getArticleTypes(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public List<ArticleTypeEntity> getArticleTypes(BlogEntity blog) throws NotAllowAttributeNull {
		return articleTypeDaoImpl.selectArticleTypes(blog);
	}

}
