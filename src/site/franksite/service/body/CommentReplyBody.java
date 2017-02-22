/**
 * 
 */
package site.franksite.service.body;

import site.franksite.pojo.CommentEntity;

/**
 * 评论回复体
 * @author Frank
 *
 */
public class CommentReplyBody {
	
	private CommentEntity comment; // 评论
	
	private CommentEntity parentComment; // 父评论
	
	private String dateStr; // 日期字符串

	/**
	 * @return the comment
	 */
	public CommentEntity getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(CommentEntity comment) {
		this.comment = comment;
	}

	/**
	 * @return the parentComment
	 */
	public CommentEntity getParentComment() {
		return parentComment;
	}

	/**
	 * @param parentComment the parentComment to set
	 */
	public void setParentComment(CommentEntity parentComment) {
		this.parentComment = parentComment;
	}

	/**
	 * @return the dateStr
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**
	 * @param dateStr the dateStr to set
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**
	 * @param comment 评论
	 * @param parentComment 若为回复，则为上一条评论
	 * @param dateStr 日期字符串
	 */
	public CommentReplyBody(CommentEntity comment, CommentEntity parentComment, String dateStr) {
		super();
		this.comment = comment;
		this.parentComment = parentComment;
		this.dateStr = dateStr;
	}

	/**
	 * 默认构造函数
	 */
	public CommentReplyBody() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommentReplyBody [" + (comment != null ? "comment=" + comment + ", " : "")
				+ (parentComment != null ? "parentComment=" + parentComment + ", " : "")
				+ (dateStr != null ? "dateStr=" + dateStr : "") + "]";
	}
}
