package org.starlightfinancial.wechatweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author senlin.deng
 */
@SpringBootApplication
public class WechatwebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WechatwebApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WechatwebApplication.class);
	}
}
