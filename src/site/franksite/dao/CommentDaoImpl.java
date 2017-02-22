/**
 * 
 */
package site.franksite.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dbquery.util.Model;

import site.franksite.dao.interfaces.AbstractQuery;
import site.franksite.dao.interfaces.CommentDao;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 评论数据库访问类
 * @author Frank
 *
 */
public class CommentDaoImpl extends AbstractQuery implements CommentDao {

	private Logger LOGGER = Logger.getLogger(CommentDaoImpl.class);
	
	private static final String SQL_INSERT_COMMENT = "INSERT INTO COMMENT (COMMENTID, ARTICLEID, PARENTID, NICKNAME, "
			+ "CONTENT, COMMENTDATE, VISIABLE) VALUES(?, ?, ?, ?, ?, ?, 1) ";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.CommentDao#insertComment(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ResponseResultEntity insertComment(CommentEntity comment) {
		
		if (this.insert(SQL_INSERT_COMMENT, comment.getCommentid(), comment.getArticleid(), 
				comment.getParentid(), comment.getNickname(), comment.getContent(), 
				comment.getCommentdate())) {
			LOGGER.info("插入成功：" + comment.getCommentid());
			return PojoBuilder.responseResult();
		}
		
		return PojoBuilder.responseResult("插入失败：" + comment.getCommentid());
		
	}

	private static final String SQL_UPDATE_COMMENT_UNVISIABLE = "UPDATE COMMENT SET VISIABLE = 0 WHERE COMMENTID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.CommentDao#markCommentUnvisiable(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ResponseResultEntity markCommentUnvisiable(CommentEntity comment) {
		
		if (update(SQL_UPDATE_COMMENT_UNVISIABLE, comment.getCommentid())) {
			LOGGER.info("更新成功，评论：" + comment.getCommentid() + "不可见。");
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("更新失败：" + comment.getCommentid());
		return PojoBuilder.responseResult("更新失败，评论：" + comment.getCommentid() + "可见。");
	}

	private static final String SQL_QUERY_COMMENTS_WITH_ARTICLE = "SELECT * FROM COMMENT WHERE VISIABLE = 1 AND ARTICLEID = ? ORDER BY COMMENTDATE ASC";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.CommentDao#selectComments(site.franksite.pojo.ArticleEntity)
	 */
	@Override
	public List<CommentEntity> selectComments(ArticleEntity article) {
		
		List<CommentEntity> comments = new LinkedList<CommentEntity>();
		
		List<Map<String, Object>> commentMappers = select(SQL_QUERY_COMMENTS_WITH_ARTICLE, article.getArticleid());
		
		if (null == commentMappers || commentMappers.isEmpty()) {
			// 空结果
			LOGGER.warn("查询结果为空集！");
			return comments;
		}
		
		for (Map<String, Object> map : commentMappers) {
			comments.add((CommentEntity) Model.parseObject(map, CommentEntity.class));
		}
		
		LOGGER.info("共查询到结果：" + comments.size() + "条。");
		return comments;
	}

	private static final String SQL_QUERY_COMMENT_WITH_ID = "SELECT * FROM COMMENT WHERE VISIABLE = 1 AND COMMENTID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.CommentDao#selectComment(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public CommentEntity selectComment(CommentEntity comment) {
		
		List<Map<String, Object>> commentMapper = select(SQL_QUERY_COMMENT_WITH_ID, comment.getCommentid());
		
		if (null == commentMapper || commentMapper.isEmpty()) {
			// 空集
			LOGGER.warn("查询结果为空集！");
			return null;
		}
		
		comment = (CommentEntity) Model.parseObject(commentMapper.get(0), CommentEntity.class); // 反映射
		
		LOGGER.info("查询成功：" + comment.getCommentid());
		return comment;
	}

	private static final String SQL_DELETE_COMMENT = "DELETE FROM COMMENT WHERE COMMENTID = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.CommentDao#deleteComment(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ResponseResultEntity deleteComment(CommentEntity comment) {
		
		// 注：该删除为针对持久层的数据的真实删除，该删除操作不考虑约束条件，若存在约束，请根据业务需要进行移除。
		int count = delete(SQL_DELETE_COMMENT, comment.getCommentid());
		
		if (0 >= count) {
			LOGGER.warn("已删除：" + count + "条评论。");
		} else {
			LOGGER.info("已删除：" + count + "条评论");
		}
		return PojoBuilder.responseResult();
	}

	private static final String SQL_UPDATE_COMMENT = "UPDATE COMMENT SET ARTICLEID = ?, PARENTID = ?, NICKNAME = ?, "
			+ "CONTENT = ?, VISIABLE = ?";
	/* (non-Javadoc)
	 * @see site.franksite.dao.interfaces.CommentDao#updateComment(site.franksite.pojo.CommentEntity)
	 */
	@Override
	public ResponseResultEntity updateComment(CommentEntity comment) {
		
		if (update(SQL_UPDATE_COMMENT, comment.getArticleid(), comment.getParentid(), 
				comment.getNickname(), comment.getContent(), comment.getVisiable())) {
			LOGGER.info("更新成功：" + comment.getCommentid());
			return PojoBuilder.responseResult();
		}
		
		LOGGER.warn("更新失败：" + comment.getCommentid());
		return PojoBuilder.responseResult("更新失败：" + comment.getCommentid());
		
	}

}
