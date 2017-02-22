/**
 * 
 */
package site.franksite.pojo;

import java.sql.Timestamp;

/**
 * 文章
 * @author Frank
 *
 */
public class ArticleEntity {

	private String articleid; // 文章ID
	
	private Integer typeid; // 类别ID
	
	private String title; // 标题
	
	private String shortcut; // 摘要
	
	private java.sql.Timestamp publishdate; // 发表日期
	
	private Boolean visiable = true; // 可见性
	
	private Long readtimes = 0l; // 阅读次数
	
	private String username; // 用户名
	

	/**
	 * 默认构造函数
	 */
	public ArticleEntity() {
		super();
	}

	

	/**
	 * @param articleid 文章ID
	 * @param typeid 类别ID
	 * @param title 标题
	 * @param shortcut 摘要信息
	 * @param publishdate 发表日期
	 * @param visiable 可见性
	 * @param readtimes 阅读次数
	 * @param username 用户名
	 */
	public ArticleEntity(String articleid, Integer typeid, String title, String shortcut, Timestamp publishdate, Boolean visiable, Long readtimes,
			String username) {
		super();
		this.title = title;
		this.shortcut = shortcut;
		this.articleid = articleid;
		this.typeid = typeid;
		this.publishdate = publishdate;
		this.visiable = visiable;
		this.readtimes = readtimes;
		this.username = username;
	}

	/**
	 * @return the shortcut
	 */
	public String getShortcut() {
		return shortcut;
	}



	/**
	 * @param shortcut the shortcut to set
	 */
	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
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
	 * @return the typeid
	 */
	public Integer getTypeid() {
		return typeid;
	}

	/**
	 * @param typeid the typeid to set
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	/**
	 * @return the publishdate
	 */
	public java.sql.Timestamp getPublishdate() {
		return publishdate;
	}

	/**
	 * @param publishdate the publishdate to set
	 */
	public void setPublishdate(java.sql.Timestamp publishdate) {
		this.publishdate = publishdate;
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

	/**
	 * @return the readtimes
	 */
	public Long getReadtimes() {
		return readtimes;
	}

	/**
	 * @param readtimes the readtimes to set
	 */
	public void setReadtimes(Long readtimes) {
		this.readtimes = readtimes;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleEntity [" + (articleid != null ? "articleid=" + articleid + ", " : "")
				+ (typeid != null ? "typeid=" + typeid + ", " : "") + (title != null ? "title=" + title + ", " : "")
				+ (publishdate != null ? "publishdate=" + publishdate + ", " : "")
				+ (visiable != null ? "visiable=" + visiable + ", " : "")
				+ (readtimes != null ? "readtimes=" + readtimes + ", " : "")
				+ (username != null ? "username=" + username : "") + "]";
	}
}
