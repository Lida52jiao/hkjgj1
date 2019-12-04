package com.yj.bj;

import com.yj.bj.util.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.yj.bj.mapper")
public class HKJGJServerApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(HKJGJServerApplication.class);
	}

	@Bean
	public SnowflakeIdWorker getSnowflakeIdWorker() {
		return new SnowflakeIdWorker(12,13);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/makePlan").setViewName("static/makePlan.html");
		registry.addViewController("/success").setViewName("static/success.html");
		registry.addViewController("/index").setViewName("static/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
		configurer.setUseSuffixPatternMatch(true)
				.setUseTrailingSlashMatch(true);
	}
}
