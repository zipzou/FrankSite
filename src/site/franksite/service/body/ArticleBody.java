/**
 * 
 */
package site.franksite.service.body;

import site.franksite.pojo.ArticleEntity;

/**
 * 文章回传体
 * @author Frank
 *
 */
public class ArticleBody {
	
	private String shortcut; //摘要
	
	private String publishdate; //发表日期
	
	private long readTimes; // 阅读次数
	
	private long commentcount; // 评论数
	
	private ArticleEntity article; // 文章实体
	
	private String content; // 内容

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
	 * @return the publishdate
	 */
	public String getPublishdate() {
		return publishdate;
	}

	/**
	 * @param publishdate the publishdate to set
	 */
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	/**
	 * @return the readTimes
	 */
	public long getReadTimes() {
		return readTimes;
	}

	/**
	 * @param readTimes the readTimes to set
	 */
	public void setReadTimes(long readTimes) {
		this.readTimes = readTimes;
	}

	/**
	 * @return the commentcount
	 */
	public long getCommentcount() {
		return commentcount;
	}

	/**
	 * @param commentcount the commentcount to set
	 */
	public void setCommentcount(long commentcount) {
		this.commentcount = commentcount;
	}

	/**
	 * @return the article
	 */
	public ArticleEntity getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleEntity article) {
		this.article = article;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleBody [" + (shortcut != null ? "shortcut=" + shortcut + ", " : "")
				+ (publishdate != null ? "publishdate=" + publishdate + ", " : "") + "readTimes=" + readTimes
				+ ", commentcount=" + commentcount + ", " + (article != null ? "article=" + article + ", " : "")
				+ (content != null ? "content=" + content : "") + "]";
	}

	/**
	 * @param shortcut 文章摘要
	 * @param publishdate 发表日期
	 * @param readTimes 阅读次数
	 * @param commentcount 评论数
	 * @param article 文章实体
	 * @param content 文章内容
	 */
	public ArticleBody(String shortcut, String publishdate, long readTimes, long commentcount, ArticleEntity article, 
			String content) {
		super();
		this.shortcut = shortcut;
		this.publishdate = publishdate;
		this.readTimes = readTimes;
		this.commentcount = commentcount;
		this.article = article;
		this.content = content;
	}

	/**
	 * 默认构造函数
	 */
	public ArticleBody() {
		super();
	}
	
}
