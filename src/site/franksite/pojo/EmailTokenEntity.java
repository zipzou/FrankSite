/**
 * 
 */
package site.franksite.pojo;

import java.sql.Timestamp;

/**
 * 邮件令牌
 * @author Frank
 *
 */
public class EmailTokenEntity {

	private Integer tokenid; // 令牌ID
	
	private String username; // 用户名
	
	private String token; // 令牌
	
	private Boolean useful; // 是否有用
	
	private java.sql.Timestamp date; // 日期

	/**
	 * @return the tokenid
	 */
	public Integer getTokenid() {
		return tokenid;
	}

	/**
	 * @param tokenid the tokenid to set
	 */
	public void setTokenid(Integer tokenid) {
		this.tokenid = tokenid;
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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the useful
	 */
	public Boolean getUseful() {
		return useful;
	}

	/**
	 * @param useful the useful to set
	 */
	public void setUseful(Boolean useful) {
		this.useful = useful;
	}

	/**
	 * @return the date
	 */
	public java.sql.Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}

	public EmailTokenEntity() {
		super();
	}

	public EmailTokenEntity(Integer tokenid, String username, String token, Boolean useful, Timestamp date) {
		super();
		this.tokenid = tokenid;
		this.username = username;
		this.token = token;
		this.useful = useful;
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailTokenEntity [" + (tokenid != null ? "tokenid=" + tokenid + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (token != null ? "token=" + token + ", " : "") + (useful != null ? "useful=" + useful + ", " : "")
				+ (date != null ? "date=" + date : "") + "]";
	}
	
	
}
