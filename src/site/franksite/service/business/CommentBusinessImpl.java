/**
 * 
 */
package site.franksite.service.business;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import site.franksite.dao.CommentDaoImpl;
import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.dao.interfaces.CommentDao;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 评论业务类
 * @author Frank
 *
 */
public class CommentBusinessImpl implements CommentBusiness {

	private static final Logger LOGGER = Logger.getLogger(CommentBusinessImpl.class);
	
	private CommentDao commentDaoImpl = new CommentDaoImpl();
	
	/* (non-Javadoc)
	 * @see site.franksite.service.business.CommentBusiness#publishComment(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ResponseResultEntity publishComment(CommentEntity comment) throws NotAllowAttributeNull {
		
		if (null == comment || null == comment.getArticleid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		// 设置ID
		comment.setCommentid(PojoBuilder.generateTimeStringId());
		// 设置时间
		comment.setCommentdate(new Timestamp(new Date().getTime()));
		
		LOGGER.info("准备插入评论：" + comment.getCommentid() + "……");
		
		// 插入
		return commentDaoImpl.insertComment(comment);
		
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.CommentBusiness#hideComment(site.franksite.pojo.AuthorEntity, site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ResponseResultEntity hideComment(AuthorEntity account, CommentEntity comment) throws NotAllowAttributeNull {
		
		if (null == account || null == account.getUsername()) {
			throw new NotAllowAttributeNull("用户名不允许为空！");
		}
		
		if (null == comment || null == comment.getCommentid()) {
			throw new NotAllowAttributeNull("评论ID不允许为空！");
		}
		
		// 查找到评论
		CommentEntity storedComment = getComment(comment);
		if (null == storedComment) {
			// 评论不存在
			LOGGER.warn("评论不存在，无法隐藏！");
			return PojoBuilder.responseResult("评论不存在，无法隐藏该评论！");
		}
		comment = storedComment;
		
		// 验证用户权限
		ArticleBusiness articleBusiness = new ArticleBusinessImpl();
		List<ArticleEntity> allArticles = articleBusiness.getArticles(account);
		boolean canHandle = false;
		for (ArticleEntity articleEntity : allArticles) {
			if (articleEntity.getArticleid().trim().equals(comment.getArticleid().trim())) {
				canHandle = true; // 权限满足
				LOGGER.info("权限满足！");
				break;
			}
		}
		if (!canHandle) {
			LOGGER.info("权限不满足，无法完成操作！");
			return PojoBuilder.responseResult("权限不足，无法完成操作！");
		}
		LOGGER.info("准备隐藏评论：" + comment.getCommentid() + "……");
		return commentDaoImpl.markCommentUnvisiable(comment);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.CommentBusiness#getComments(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public List<CommentEntity> getComments(ArticleEntity article) throws NotAllowAttributeNull {
		
		if (null == article || null == article.getArticleid()) {
			throw new NotAllowAttributeNull("文章ID不允许为空！");
		}
		
		LOGGER.info("准备获取所有评论……");
		
		return commentDaoImpl.selectComments(article);
	}

	/* (non-Javadoc)
	 * @see site.franksite.service.business.CommentBusiness#getComment(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public CommentEntity getComment(CommentEntity comment) throws NotAllowAttributeNull {
		
		if (null == comment || null == comment.getCommentid()) {
			throw new NotAllowAttributeNull("评论ID不允许为空！");
		}
		LOGGER.info("准备获取评论：" + comment.getCommentid() + "……");
		return commentDaoImpl.selectComment(comment);
	}

}
