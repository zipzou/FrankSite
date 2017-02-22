package site.franksite.testunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountBusinessTest.class, ArticleBusinessTest.class, ArticleTypeBusinessTest.class,
		BlogBusinessTest.class, CommentBusinessTest.class })
public class AllBusinessTestSuit {

}
