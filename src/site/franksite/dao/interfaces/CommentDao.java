/**
 * 
 */
package site.franksite.dao.interfaces;

import java.util.List;

import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 评论数据库访问接口
 * @author Frank
 *
 */
public interface CommentDao {

	/**
	 * 插入评论
	 * @param comment 要插入的评论
	 * @return 插入结果
	 */
	public ResponseResultEntity insertComment(CommentEntity comment);
	
	/**
	 * 将评论标记为不可见
	 * @param comment 要标记的评论
	 * @return 标记结果
	 */
	public ResponseResultEntity markCommentUnvisiable(CommentEntity comment);
	
	/**
	 * 根据文章，查询所有的评论
	 * @param article 要查询的文章
	 * @return 文章所有的评论，并根据时间倒序排列，若文章无评论，则为空表
	 */
	public List<CommentEntity> selectComments(ArticleEntity article);
	
	/**
	 * 查询指定的评论
	 * @param comment 要查询的评论
	 * @return 指定的评论实体，若该评论不存在，则为null
	 */
	public CommentEntity selectComment(CommentEntity comment);
	
	/**
	 * 从数据集中删除评论实体（不可撤销）
	 * @param comment 要删除的评论实体
	 * @return 删除结果
	 */
	public ResponseResultEntity deleteComment(CommentEntity comment);
	
	/**
	 * 更新评论信息
	 * @param comment 要更新的评论信息
	 * @return 更新结果
	 */
	public ResponseResultEntity updateComment(CommentEntity comment);
	
}
