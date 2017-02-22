/**
 * 
 */
package site.franksite.pojo;

/**
 * 博客实体
 * @author Frank
 *
 */
public class BlogEntity {
	
	private Long blogid; // 博客ID
	
	private String slogan; // 标语
	
	private Long indexmax = 5l; // 首页最大文章数

	public BlogEntity() {
		super();
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
	 * @return the slogan
	 */
	public String getSlogan() {
		return slogan;
	}

	/**
	 * @param slogan the slogan to set
	 */
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	/**
	 * @return the indexmax
	 */
	public Long getIndexmax() {
		return indexmax;
	}

	/**
	 * @param indexmax the indexmax to set
	 */
	public void setIndexmax(Long indexmax) {
		this.indexmax = indexmax;
	}

	public BlogEntity(Long blogid, String slogan, Long indexmax) {
		super();
		this.blogid = blogid;
		this.slogan = slogan;
		this.indexmax = indexmax;
	}
		
}
