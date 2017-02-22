/**
 * 
 */
package site.franksite.pojo;

import java.sql.Timestamp;

/**
 * 评论
 * @author Frank
 *
 */
public class CommentEntity {
	
	private String commentid; // 评论ID
	
	private String articleid; // 文章ID
	
	private String parentid; // 回复的评论ID
	
	private String nickname; // 评论的作者昵称
	
	private String content; // 评论内容
	
	private Boolean visiable;
	
	private java.sql.Timestamp commentdate; // 评论时间

	

	/**
	 * @param commentid 评论ID
	 * @param articleid 文章ID
	 * @param parentid 回复的评论ID
	 * @param nickname 评论者昵称
	 * @param content 评论或回复内容
	 * @param visiable 评论是否可见
	 * @param commentdate 评论或回复日期
	 */
	public CommentEntity(String commentid, String articleid, String parentid, String nickname, String content,
			Boolean visiable, Timestamp commentdate) {
		super();
		this.commentid = commentid;
		this.articleid = articleid;
		this.parentid = parentid;
		this.nickname = nickname;
		this.content = content;
		this.visiable = visiable;
		this.commentdate = commentdate;
	}

	/**
	 * 
	 */
	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the commentid
	 */
	public String getCommentid() {
		return commentid;
	}

	/**
	 * @param commentid the commentid to set
	 */
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	/**
	 * @return the articleid
	 */
	public String getArticleid() {
		return articleid;
	}

	/**
	 * @param articleid the articleid to set
	 */
	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	/**
	 * @return the parentid
	 */
	public String getParentid() {
		return parentid;
	}

	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the commentdate
	 */
	public java.sql.Timestamp getCommentdate() {
		return commentdate;
	}

	/**
	 * @param commentdate the commentdate to set
	 */
	public void setCommentdate(java.sql.Timestamp commentdate) {
		this.commentdate = commentdate;
	}

	/**
	 * @return the visiable
	 */
	public Boolean getVisiable() {
		return visiable;
	}

	/**
	 * @param visiable the visiable to set
	 */
	public void setVisiable(Boolean visiable) {
		this.visiable = visiable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommentEntity [" + (commentid != null ? "commentid=" + commentid + ", " : "")
				+ (articleid != null ? "articleid=" + articleid + ", " : "")
				+ (parentid != null ? "parentid=" + parentid + ", " : "")
				+ (nickname != null ? "nickname=" + nickname + ", " : "")
				+ (content != null ? "content=" + content + ", " : "")
				+ (visiable != null ? "visiable=" + visiable + ", " : "")
				+ (commentdate != null ? "commentdate=" + commentdate : "") + "]";
	}
	
}
