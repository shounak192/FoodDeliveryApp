package com.example.fooddeliveryapp.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.fooddeliveryapp.service.ICustomerService;
import com.example.fooddeliveryapp.util.PasswordEncoderGenerator;

@Configuration
public class SecurityConfiguration {

	private ICustomerService customerService;

	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public SecurityConfiguration(ICustomerService customerService, JwtRequestFilter jwtRequestFilter) {
		super();
		this.customerService = customerService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() {
		return new ProviderManager(List.of(getAuthenticationProvider()));
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests(autz -> autz.anyRequest().authenticated())
				.authenticationManager(getAuthenticationManager()).exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic();

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/*
	 * Security filter chain will allow to access antMatcher endpoints without jwt
	 * authentication
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().antMatchers("/customer/register").antMatchers("/customer/login")
				.antMatchers("/hotel/create").antMatchers("/food/create").antMatchers("/authenticate");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		return PasswordEncoderGenerator.getEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService((UserDetailsService) customerService);
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		authenticationProvider.setAuthoritiesMapper(new SimpleAuthorityMapper());
		return authenticationProvider;
	}

}
