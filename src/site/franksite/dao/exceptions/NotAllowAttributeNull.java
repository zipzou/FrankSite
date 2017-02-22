/**
 * 
 */
package site.franksite.dao.exceptions;

/**
 * 不允许某个属性为空的异常
 * @author Frank
 *
 */
public class NotAllowAttributeNull extends Exception {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5186015770120133541L;

	/**
	 * 默认构造函数
	 */
	public NotAllowAttributeNull() {
		super("不允许一个空的属性");
	}

	/**
	 * @param message 异常消息
	 */
	public NotAllowAttributeNull(String message) {
		super(message);
	}
}
