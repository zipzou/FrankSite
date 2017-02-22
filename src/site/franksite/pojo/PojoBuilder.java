package site.franksite.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class PojoBuilder {
	
	/**
	 * 成功响应体
	 * @return 响应实体，状态为true
	 */
	public static ResponseResultEntity responseResult() {
		return new ResponseResultEntity();
	}
	
	/**
	 * 失败响应体
	 * @param reason 失败原因
	 * @return 响应实体，状态为FALSE，原因为reason
	 */
	public static ResponseResultEntity responseResult(String reason) {
		return new ResponseResultEntity(false, reason);
	}
	
	/**
	 * 上传失败的响应体构造
	 * @param message 失败消息
	 * @return 响应体
	 */
	public static FileUploadResponseEntity uploadFailResponse(String message) {
		return new FileUploadResponseEntity(message, 0, null);
	}
	
	/**
	 * 上传成功的响应体构造
	 * @param url 上传成功的URL
	 * @return 上传结果的响应实体
	 */
	public static FileUploadResponseEntity uploadSuccResponse(String url) {
		return new FileUploadResponseEntity(null, 1, url);
	}
	
	/**
	 * 产生字符型的ID
	 * @return ID字符串
	 */
	public static String generateTimeStringId() {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String prefix = format.format(now);
		String suffix = now.getTime() % 1000 + "";
		return prefix + suffix;
	}
	
	/**
	 * 构造邮件令牌实体
	 * @return 邮件令牌实体
	 */
	public static EmailTokenEntity emailtoken() {
		return new EmailTokenEntity();
	}
	
	/**
	 * 作者实体
	 * @return 创建一个默认的作者实体
	 */
	public static AuthorEntity author() {
		return new AuthorEntity();
	}
	
	/**
	 * 博客实体
	 * @return 创建一个默认的博客实体
	 */
	public static BlogEntity blog() {
		return new BlogEntity();
	}
	
	/**
	 * 文章类型实体
	 * @return 创建一个默认的文章类别
	 */
	public static ArticleTypeEntity  articletype() {
		return new ArticleTypeEntity();
	}
	
	/**
	 * 文章实体
	 * @return 创建一个默认的文章实体
	 */
	public static ArticleEntity article() {
		return new ArticleEntity();
	}
	
	/**
	 * 评论实体
	 * @return 创建一个默认的评论实体
	 */
	public static CommentEntity comment() {
		return new CommentEntity();
	}
	
	public static String buildSalt() {
		Random rdm = new Random();
		char []chars = new char[6];
		for (int i = 0; i < chars.length; i++) {
			char ch = (char)rdm.nextInt(256);
			if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				chars[i] = ch;
			} else {
				i--;
			}
		}
		return new String(chars);
	}
	/**
	 * 创建一个用于产生令牌的内容
	 * @return
	 */
	public static String buildTokenContent () {
		return DigestUtils.md5Hex(generateTimeStringId());
	}
}
