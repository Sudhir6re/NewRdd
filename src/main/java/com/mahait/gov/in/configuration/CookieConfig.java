package com.mahait.gov.in.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class CookieConfig {

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("JSESSIONID");
		serializer.setSameSite("Strict");
		serializer.setUseSecureCookie(true);
		serializer.setCookiePath("/");
		serializer.setUseHttpOnlyCookie(true);
		return serializer;
	}

}
