package site.franksite.service.app;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

public class FrankSiteApplication extends ResourceConfig {
	
	public FrankSiteApplication() {
		super();
		// 注册服务所在包
		packages("site.franksite.service");
		
		// 注册MVC
		register(JspMvcFeature.class);
		// 注册JSON
		register(JacksonJsonProvider.class);
		register(JacksonJaxbJsonProvider.class);
	}
}
