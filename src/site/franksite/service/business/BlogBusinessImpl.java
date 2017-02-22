/**
 * 
 */
package site.franksite.service.business;

import org.apache.log4j.Logger;

import site.franksite.dao.AuthorDaoImpl;
import site.franksite.dao.BlogDaoImpl;
import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.AuthorDao;
import site.franksite.dao.interfaces.BlogDao;
import site.franksite.pojo.ArticleTypeEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.BlogEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 博客业务类
 * @author Frank
 *
 */
public class BlogBusinessImpl implements BlogBusiness {

	private static final Logger LOGGER = Logger.getLogger(BlogBusinessImpl.class);
	
	private BlogDao blogDaoImpl = new BlogDaoImpl();
	
	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#assignBlogToAuthor(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public ResponseResultEntity assignBlogToAuthor(AuthorEntity authorAndBlog) throws NotAllowAttributeNull {
		if (null == authorAndBlog || 
				null == authorAndBlog.getUsername()) {
			throw new NotAllowAttributeNull("用户名不可为空！");
		}
		
		// 博客是否已经存在
		boolean needCreated = false;
		if (null == authorAndBlog.getBlogid()) {
			needCreated = true;
		}
		
		BlogEntity blog = null; // 博客实体
		
		if (null != authorAndBlog.getBlogid()) { // 博客ID存在
			blog = blogDaoImpl.selectBlog(authorAndBlog); // 是否存在该博客
			if (null == blog) { // 不存在，需要创建
				needCreated = true;
			}
		}
		
		if (needCreated) {
			LOGGER.info("博客不存在，需要创建！");
			blog = PojoBuilder.blog();
			blog.setSlogan("");
			blog.setIndexmax(5l);
			ResponseResultEntity createResult = createBlog(blog); // 创建的博客ID为自增ID，不可使用指定的ID
			if (!createResult.getStatus()) {
				// 创建失败
				LOGGER.error("博客创建失败，无法为用户分配博客；用户名：" + authorAndBlog.getUsername());
				return PojoBuilder.responseResult("博客创建失败，无法为用户分配博客；用户名：" + authorAndBlog.getUsername());
			} else {
				// 博客创建成功，查找最新的博客记录
				blog = blogDaoImpl.selectLatestBlog();
				LOGGER.info("创建博客成功，博客ID：" + blog.getBlogid());
			}
		}
		
		AuthorDao authorImpl = new AuthorDaoImpl();
		
		// 查询用户信息
		AuthorEntity author  = authorImpl.selectAuthor(authorAndBlog);
		if (null == author) {
			// 不存在的用户
			LOGGER.warn("该用户不存在：" + authorAndBlog.getUsername());
			return PojoBuilder.responseResult("该用户不存在：" + authorAndBlog.getUsername());
		}
		
		// 用户存在，设置博客ID
		author.setBlogid(blog.getBlogid());
		ResponseResultEntity updateResult = authorImpl.updateAuthor(author); // 更新
		if (!updateResult.getStatus()) {
			// 更新失败
			LOGGER.warn("博客分配失败：" + author.getUsername() + ", " + author.getBlogid());
			return PojoBuilder.responseResult("博客分配失败，用户名：" + author.getUsername() + "博客ID：" + author.getBlogid());
		}
		LOGGER.info("博客分配成功，用户名：" + author.getUsername() + "博客ID：" + author.getBlogid());
		return PojoBuilder.responseResult();
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#createBlog(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public ResponseResultEntity createBlog(BlogEntity blog) {
		
		ResponseResultEntity createResult = blogDaoImpl.insertBlog(blog); // 创建博客
		if (!createResult.getStatus()) {
			// 创建失败
			LOGGER.error("博客创建失败：" + createResult.getReason());
			return PojoBuilder.responseResult("博客创建失败：" + createResult.getReason());
		}
		LOGGER.info("博客创建成功！");
		return createResult;
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#changeBlogSlogan(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public ResponseResultEntity changeBlogSlogan(BlogEntity blog) throws NotAllowAttributeNull {
		
		if (null == blog || null == blog.getBlogid()) {
			throw new NotAllowAttributeNull("博客ID不允许为空值！");
		}
		
		// 查找原始博客
		BlogEntity storedBlog = getBlog(blog); // 查找博客
		
		// 更新博客标语
		storedBlog.setSlogan(blog.getSlogan());
		return blogDaoImpl.updateBlog(storedBlog);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#changeBlogIndexMax(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public ResponseResultEntity changeBlogIndexMax(BlogEntity blog) throws NotAllowAttributeNull {
		
		// 查找原始博客
		BlogEntity storedBlog = blogDaoImpl.selectBlog(blog); // 查找博客
		
		// 更新博客首页容量
		storedBlog.setIndexmax(blog.getIndexmax());
		return blogDaoImpl.updateBlog(storedBlog);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#getBlog(site.franksite.pojo.AuthorEntity)
	 */
	@Override
	public BlogEntity getBlog(AuthorEntity author) throws NotAllowAttributeNull {
		
		return blogDaoImpl.selectBlog(author);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#getBlog(site.franksite.pojo.BlogEntity)
	 */
	@Override
	public BlogEntity getBlog(BlogEntity blog) throws NotAllowAttributeNull {
		
		return blogDaoImpl.selectBlog(blog);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.BlogBusiness#getBlog(site.franksite.pojo.ArticleTypeEntity)
	 */
	@Override
	public BlogEntity getBlog(ArticleTypeEntity articleType) throws NotAllowAttributeNull {
		return blogDaoImpl.selectBlog(articleType);
	}

}
