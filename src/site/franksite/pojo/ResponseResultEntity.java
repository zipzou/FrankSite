package site.franksite.pojo;

public class ResponseResultEntity {

	
	private Boolean status; // 状态
	
	private String reason; // 失败原因

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	public ResponseResultEntity(Boolean status, String reason) {
		super();
		this.status = status;
		this.reason = reason;
	}
	
	public ResponseResultEntity(String reason) {
		this.status = false;
		this.reason = reason;
	}
	
	public ResponseResultEntity(Boolean status) {
		this.status = true;
		this.reason = null;
	}

	public ResponseResultEntity() {
		super();
		this.status = true;
	}
	
}
