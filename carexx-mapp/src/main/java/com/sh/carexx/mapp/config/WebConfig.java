package com.sh.carexx.mapp.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.ImmutableList;
import com.sh.carexx.mapp.interceptor.AuthInterceptor;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final String CHARSET = "utf-8";

	public WebConfig() {
		super();
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		final StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(
				ImmutableList.of(MediaType.TEXT_PLAIN, MediaType.TEXT_XML, MediaType.APPLICATION_JSON));
		converter.setDefaultCharset(Charset.forName(CHARSET));
		return converter;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(ImmutableList.of(MediaType.APPLICATION_JSON));
		converter.setDefaultCharset(Charset.forName(CHARSET));
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJacksonHttpMessageConverter());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
		registry.addResourceHandler("/templates/**")
				.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
		super.addResourceHandlers(registry);
	}

	@Bean
	AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns("/auth/**")
				.excludePathPatterns("/callback/**");
		super.addInterceptors(registry);
	}
}
