package com.prgroceries.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.prgroceries.service.PRGroceryUserServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new PRGroceryUserServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authCustomizer -> {
			authCustomizer.requestMatchers("/inventory/available").hasRole("USER")
						  .requestMatchers("/inventory/**").hasRole("ADMIN")
						  .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
						  .requestMatchers("/orders").hasRole("USER")
						  .requestMatchers("/users").hasRole("ADMIN")
						  .anyRequest().authenticated();
		})
		.csrf(csrfCustomizer -> csrfCustomizer.disable())
		.httpBasic((basic) -> basic
	            .addObjectPostProcessor(new ObjectPostProcessor<BasicAuthenticationFilter>() {
	                @Override
	                public <O extends BasicAuthenticationFilter> O postProcess(O filter) {
	                    filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
	                    return filter;
	                }
	            })
	        );
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
