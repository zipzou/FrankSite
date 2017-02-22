/**
 * 
 */
package site.franksite.pojo;

/**
 * 图片上传的响应实体
 * @author Frank
 *
 */
public class FileUploadResponseEntity {

	private String message; // 错误消息
	
	private Integer success; // 成功指示
	
	private String url; // 返回的地址

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the success
	 */
	public Integer getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(Integer success) {
		this.success = success;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public FileUploadResponseEntity() {
		super();
	}

	public FileUploadResponseEntity(String message, Integer success, String url) {
		super();
		this.message = message;
		this.success = success;
		this.url = url;
	}
	
	
}
