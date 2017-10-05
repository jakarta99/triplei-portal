package tw.com.triplei.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.fckeditor.connector.ConnectorServlet;
import net.fckeditor.handlers.PropertiesLoader;


@Configuration
public class FckeditorConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ServletRegistrationBean getConnectorServlet(HttpServletRequest request){
		ConnectorServlet connector = new ConnectorServlet();
		ServletRegistrationBean registrationBean=new ServletRegistrationBean();
		registrationBean.setServlet(connector);
		List<String> urlMappings=new ArrayList<String>();
		urlMappings.add("/fckeditor/editor/filemanager/connectors/*");
		
		Map<String,String> initparm = new HashMap<>();
		initparm.put("baseDir", "/userfiles/");
		initparm.put("debug", "true");
		PropertiesLoader.setProperty("connector.userActionImpl","net.fckeditor.requestcycle.impl.EnabledUserAction");
		registrationBean.setInitParameters(initparm);
		registrationBean.setUrlMappings(urlMappings);
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}
}
