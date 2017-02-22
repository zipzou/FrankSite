/**
 * 
 */
package site.franksite.pojo;

import java.sql.Timestamp;

/**
 * 文章分类实体
 * @author Frank
 *
 */
public class ArticleTypeEntity {

	private Integer typeid; // 类型ID
	
	private Long blogid; // 博客ID
	
	private String title; // 类型标题
	
	private String note; // 标记
	
	private java.sql.Timestamp createdate; // 创建日期
	
	private Boolean visiable; // 文章是否可见

	public ArticleTypeEntity() {
		super();
	}

	/**
	 * @param typeid 类别ID
	 * @param blogid 博客ID
	 * @param title 类别标题
	 * @param note 类别标注
	 * @param createdate 创建日期
	 * @param visiable 是否可见
	 */
	public ArticleTypeEntity(Integer typeid, Long blogid, String title, String note, Timestamp createdate,
			Boolean visiable) {
		super();
		this.typeid = typeid;
		this.blogid = blogid;
		this.title = title;
		this.note = note;
		this.createdate = createdate;
		this.visiable = visiable;
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
	 * @return the blogid
	 */
	public Long getBlogid() {
		return blogid;
	}

	/**
	 * @param blogid the blogid to set
	 */
	public void setBlogid(Long blogid) {
		this.blogid = blogid;
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

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the createdate
	 */
	public java.sql.Timestamp getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(java.sql.Timestamp createdate) {
		this.createdate = createdate;
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
		return "ArticleTypeEntity [" + (typeid != null ? "typeid=" + typeid + ", " : "")
				+ (blogid != null ? "blogid=" + blogid + ", " : "") + (title != null ? "title=" + title + ", " : "")
				+ (note != null ? "note=" + note + ", " : "")
				+ (createdate != null ? "createdate=" + createdate + ", " : "")
				+ (visiable != null ? "visiable=" + visiable : "") + "]";
	}

}
