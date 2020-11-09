package com.xyf.learnweb.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

@Configuration
public class ConfigBean implements WebMvcConfigurer {

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		// restTemplate.setErrorHandler(new ThrowErrorHandler());
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(10000);// 单位为ms
		factory.setConnectTimeout(5000);// 单位为ms
		return factory;
	}

	/**
	 * 注入封装RestTemplate
	 * 
	 * @Title: httpRequestUtils
	 * @return httpRequestUtils
	 */
	@Bean(name = "httpRequestUtils")
	public HttpRequestUtils httpRequestUtils(RestTemplate restTemplate) {
		HttpRequestUtils httpRequestUtils = new HttpRequestUtils();
		httpRequestUtils.setRestTemplate(restTemplate);
		return httpRequestUtils;
	}
	

}
