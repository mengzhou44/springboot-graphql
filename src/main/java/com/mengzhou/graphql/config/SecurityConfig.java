package com.mengzhou.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		 UserDetails user = User.withUsername("user").password("{noop}password").roles("USER").build();
		 UserDetails admin = User.withUsername("admin").password("{noop}password").roles("USER", "ADMIN").build();

		return new InMemoryUserDetailsManager( user, admin);
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return   http
				.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated()
				)
				.sessionManagement(sessionManagement -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults())

				.build();
	}
}
