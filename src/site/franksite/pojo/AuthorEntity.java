/**
 * 
 */
package site.franksite.pojo;

/**
 * 博客作者
 * @author Frank
 *
 */
public class AuthorEntity {
	
	private String username; // 用户名
	
	private Long blogid; // 博客ID
	
	private String salt; // 盐值6位
	
	private String password; // 64位MD5密码
	
	private String email; // 邮箱地址
	
	private String mobile; // 手机号码
	
	private Boolean emailvalidation; // 邮箱是否验证
	
	private Boolean mobilevalidation; // 手机号码是否验证

	public AuthorEntity() {
		super();
	}

	public AuthorEntity(String username, Long blogid, String salt, String password, String email, String mobile,
			Boolean emailvalidation, Boolean mobilevalidation) {
		super();
		this.username = username;
		this.blogid = blogid;
		this.salt = salt;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.emailvalidation = emailvalidation;
		this.mobilevalidation = mobilevalidation;
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
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the emailvalidation
	 */
	public Boolean getEmailvalidation() {
		return emailvalidation;
	}

	/**
	 * @param emailvalidation the emailvalidation to set
	 */
	public void setEmailvalidation(Boolean emailvalidation) {
		this.emailvalidation = emailvalidation;
	}

	/**
	 * @return the mobilevalidation
	 */
	public Boolean getMobilevalidation() {
		return mobilevalidation;
	}

	/**
	 * @param mobilevalidation the mobilevalidation to set
	 */
	public void setMobilevalidation(Boolean mobilevalidation) {
		this.mobilevalidation = mobilevalidation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthorEntity [" + (username != null ? "username=" + username + ", " : "")
				+ (blogid != null ? "blogid=" + blogid + ", " : "") + (salt != null ? "salt=" + salt + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (mobile != null ? "mobile=" + mobile + ", " : "")
				+ (emailvalidation != null ? "emailvalidation=" + emailvalidation + ", " : "")
				+ (mobilevalidation != null ? "mobilevalidation=" + mobilevalidation : "") + "]";
	}
	
}
