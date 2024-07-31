package com.app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity// - required in earlier spring sec versions -enabled by default
@Configuration //equivalent to bean config xml file
@EnableGlobalMethodSecurity(prePostEnabled = true) //-was required in earlier versions
public class SecurityConfig {
	@Autowired
	private PasswordEncoder encoder;
	//configure user details service as a spring bean
	@Bean
	public UserDetailsService users() {
		User admin=
				new User("Rama",encoder.encode("12345"),
						List.of(
								new SimpleGrantedAuthority("ROLE_ADMIN")));
		User customer=new User("Kiran",encoder.encode("2345"), 
				List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
		
		return new InMemoryUserDetailsManager(customer,admin);
	}
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
