package site.franksite.testunit;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegTest {

	@Test
	public void test() {
//		String div = "<div class=\"hml\"></div>";
//		
//		String img = "<img alt=\"img\"/>";
//		
//		String meta = "<meta>";
//		
//		String reg = "<[^>]*g";
//		
//		if (!div.matches(reg)) {
//			fail("div匹配失败");
//		}
//		if (!img.matches(reg)) {
//			fail("img匹配失败");
//		}
//		if (!meta.matches(reg)) {
//			fail("meta匹配失败");
//		}
		String reg = "测试";
		
		String str = "这是一个测试的字符串";
		if (!str.contains(reg)) {
			fail();
		}
	}

}
