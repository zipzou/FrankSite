/**
 * 
 */
package site.franksite.service.business;

import java.util.List;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.ResponseResultEntity;

/**
 * 评论业务接口
 * @author Frank
 *
 */
public interface CommentBusiness {

	/**
	 * 发表评论或回复
	 * @param comment 要发表的评论或回复，若parentid不为空，则表示回复，否则为评论
	 * @return 发表结果
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public ResponseResultEntity publishComment(CommentEntity comment) throws NotAllowAttributeNull;
	
	/**
	 * 隐藏一条评论
	 * @param account 操作的用户
	 * @param comment 要隐藏的评论
	 * @return 隐藏结果
	 * @throws NotAllowAttributeNull 当用户名或评论ID为空时，抛出该异常
	 */
	public ResponseResultEntity hideComment(AuthorEntity account, CommentEntity comment) throws NotAllowAttributeNull;
	
	/**
	 * 获取文章的所有评论
	 * @param article 文章
	 * @return 所有评论，若文章无评论，则为空表
	 * @throws NotAllowAttributeNull 当文章ID为空时，抛出该异常
	 */
	public List<CommentEntity> getComments(ArticleEntity article) throws NotAllowAttributeNull;
	
	/**
	 * 获取评论的详细信息
	 * @param comment 要获取的评论
	 * @return 评论详细信息
	 * @throws NotAllowAttributeNull 当评论ID为空时，抛出该异常
	 */
	public CommentEntity getComment(CommentEntity comment) throws NotAllowAttributeNull;
	
}
