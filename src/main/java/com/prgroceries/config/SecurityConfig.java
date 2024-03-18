package com.prgroceries.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	@Bean
 	public UserDetailsService userDetailsService() {
 		UserDetails user = User.withUsername("prakhar")
 			.password(passwordEncoder().encode("password1"))
 			.roles("USER")
 			.build();
 		UserDetails admin = User.withUsername("shikhar")
 			.password(passwordEncoder().encode("password2"))
 			.roles("ADMIN")
 			.build();
 		return new InMemoryUserDetailsManager(user, admin);
 	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http.authorizeHttpRequests(myCustomizer -> {
 			myCustomizer.requestMatchers("/item").hasRole("ADMIN")
 						.requestMatchers(HttpMethod.GET,"/item").permitAll()
 						.requestMatchers("/order").hasRole("USER")
 						.anyRequest().authenticated();
 			})
 			.csrf(csrfCustomizer -> csrfCustomizer.disable())
 			.httpBasic(withDefaults());
 		return http.build();
 	}


}
