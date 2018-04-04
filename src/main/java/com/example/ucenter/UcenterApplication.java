package com.example.ucenter;

import com.example.ucenter.Filter.HTTPBearerAuthorizeAttribute;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
@MapperScan("com.example.ucenter.mapper")
@EnableTransactionManagement
public class UcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcenterApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean  jwtFilterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
		registrationBean.setFilter(httpBearerFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/getusers");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	@Bean
   public BCryptPasswordEncoder passwordEncoder()
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
