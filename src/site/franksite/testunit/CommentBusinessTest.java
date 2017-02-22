/**
 * 
 */
package site.franksite.testunit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import site.franksite.dao.exceptions.NotAllowAttributeNull;
import site.franksite.pojo.ArticleEntity;
import site.franksite.pojo.AuthorEntity;
import site.franksite.pojo.CommentEntity;
import site.franksite.pojo.PojoBuilder;
import site.franksite.pojo.ResponseResultEntity;
import site.franksite.service.business.CommentBusiness;
import site.franksite.service.business.CommentBusinessImpl;

/**
 * @author Frank
 *
 */
public class CommentBusinessTest {

	private CommentBusiness commentBusinessImpl = new CommentBusinessImpl();
	
	/**
	 * Test method for {@link site.franksite.service.business.CommentBusinessImpl#publishComment(site.franksite.pojo.CommentEntity)}.
	 */
	@Test
	public void testPublishComment() {
		CommentEntity comment = PojoBuilder.comment();
		comment.setNickname("测试用户");
		comment.setArticleid("20170122225237580");
		comment.setVisiable(true);
		comment.setContent("这是测试评论！");
		try {
			ResponseResultEntity result = commentBusinessImpl.publishComment(comment);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.CommentBusinessImpl#hideComment(site.franksite.pojo.AuthorEntity, site.franksite.pojo.CommentEntity)}.
	 */
	@Test
	public void testHideComment() {
		
		AuthorEntity author = PojoBuilder.author();
		author.setUsername("frank");
		
		CommentEntity comment = PojoBuilder.comment();
		comment.setCommentid("20170123114218403");
		
		try {
			ResponseResultEntity result = commentBusinessImpl.hideComment(author, comment);
			if (!result.getStatus()) {
				fail(result.getReason());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link site.franksite.service.business.CommentBusinessImpl#getComments(site.franksite.pojo.ArticleEntity)}.
	 */
	@Test
	public void testGetComments() {
		ArticleEntity article = PojoBuilder.article();
		article.setArticleid("20170122225237580");
		try {
			List<CommentEntity> comments = commentBusinessImpl.getComments(article);
			for (CommentEntity commentEntity : comments) {
				System.out.println(commentEntity.toString());
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link site.franksite.service.business.CommentBusinessImpl#getComment(site.franksite.pojo.CommentEntity)}.
	 */
	@Test
	public void testGetComment() {
		CommentEntity comment = PojoBuilder.comment();
		comment.setCommentid("20170123114236437");
		try {
			comment = commentBusinessImpl.getComment(comment);
			if (null == comment) {
				fail("查找失败，请查看日志！");
			}
		} catch (NotAllowAttributeNull e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
