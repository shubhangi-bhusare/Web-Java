package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //- required in earlier spring sec versions -enabled by default
@Configuration //equivalent to bean config xml file
@EnableGlobalMethodSecurity(prePostEnabled = true)// -was required in earlier versions
public class SecurityConfig {
	@Autowired
	private PasswordEncoder encoder;
	
	//
	
	//Configure the bean to customize spring security filter chain
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
		http		
		.csrf().disable() //disable CSRF(cross site req forgery) - CSRF token generation n verification
		.authorizeRequests()		
		.antMatchers("/products/view","/users/signup","/users/signin",
				"/v*/api-doc*/**","/swagger-ui/**").permitAll()
		
		.antMatchers("/products/purchase/**").hasRole("CUSTOMER")
		.antMatchers("/products/add").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		return http.build();
	}
}
