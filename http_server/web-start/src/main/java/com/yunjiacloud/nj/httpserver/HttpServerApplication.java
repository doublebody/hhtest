package com.yunjiacloud.nj.httpserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class HttpServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(HttpServerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean(name="conversionService")
	public ConversionServiceFactoryBean getConversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<>();
		converters.add(new Converter<String, Date>(){

			@Override
			public Date convert(String s) {
				return new Date();
			}
		});
		bean.setConverters(converters);
		return bean;
	}
	/**
	 @Bean(destroyMethod = "close", initMethod = "init")
	 public DataSource writeDataSource() {
	 System.err.println("注入druid！！！");
	 DruidDataSource dataSource = new DruidDataSource();
	 dataSource.setUrl(propertyResolver.getProperty("url"));
	 dataSource.setUsername(propertyResolver.getProperty("username"));// 用户名
	 dataSource.setPassword(propertyResolver.getProperty("password"));// 密码
	 dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
	 dataSource.setInitialSize(2);
	 dataSource.setMaxActive(20);
	 dataSource.setMinIdle(0);
	 dataSource.setMaxWait(60000);
	 dataSource.setValidationQuery("SELECT 1");
	 dataSource.setTestOnBorrow(false);
	 dataSource.setTestWhileIdle(true);
	 dataSource.setPoolPreparedStatements(false);
	 return dataSource;
	 }
	 */
}
