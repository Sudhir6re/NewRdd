package com.mahait.gov.in.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mahait.gov.in.customfilter.CustomAuthenticationFailureHandler;
import com.mahait.gov.in.customfilter.CustomAuthenticationFilter;
import com.mahait.gov.in.customfilter.CustomLogoutSuccessHandler;
import com.mahait.gov.in.customfilter.CustomSimpleUrlAuthenticationSuccessHandler;
import com.mahait.gov.in.customfilter.CustomUserDetailsAuthenticationProvider;

//@Configuration
public class SpringSecurityConfig {
	
	  
	 UserDetailsService userDetailsService;
	 
	  PasswordEncoder passwordEncoder;
	  

	  
	  @Bean public SecurityFilterChain securityFilterChain(HttpSecurity
	  httpSecurity, AuthenticationManager authenticationManager) throws Exception {
	  return httpSecurity.authorizeHttpRequests(authorize -> {
	  authorize.requestMatchers("/css/**", "/js/**",
	  "/images/**","/static/**").permitAll();
	  authorize.requestMatchers("/user/login", "/error/**", "/logout", "/",
	  "/home").permitAll(); authorize.requestMatchers("/mdc/**").hasRole("MDC");
	  authorize.requestMatchers("/ddoast/**").hasRole("DDO_AST");
	  authorize.requestMatchers("/ddo/**").hasRole("DDO");
	  authorize.requestMatchers("/super/**").hasRole("SUPER");
	  authorize.requestMatchers("/user/**").hasRole("USER");
	  authorize.requestMatchers("/services/**").denyAll();
	  authorize.anyRequest().authenticated(); }).formLogin(formLogin ->
	  formLogin.loginPage("/user/login") //.defaultSuccessUrl("/mdc/home", true)
	  .successHandler(myAuthenticationSuccessHandler()).permitAll()) .logout(logout
	  -> logout.logoutUrl("/user/logOut") //.logoutSuccessUrl("/user/logOut")
	  .logoutSuccessHandler(customLogoutSuccessHandler()).permitAll())
	  .addFilterBefore(authenticationFilter(authenticationManager),
	  UsernamePasswordAuthenticationFilter.class)
	  .csrf(AbstractHttpConfigurer::disable).build(); }
	  
	  @Bean public AuthenticationManager
	  authenticationManager(AuthenticationConfiguration
	  authenticationConfiguration) throws Exception { return
	  authenticationConfiguration.getAuthenticationManager(); }
	  
	  @Bean public CustomAuthenticationFilter
	  authenticationFilter(AuthenticationManager authenticationManager) {
	  CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
	  filter.setAuthenticationManager(authenticationManager);
	  filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler());
	  filter.setAuthenticationFailureHandler(authenticationFailureHandler());
	  return filter; }
	  
	  @Bean public CustomSimpleUrlAuthenticationSuccessHandler
	  myAuthenticationSuccessHandler() { return new
	  CustomSimpleUrlAuthenticationSuccessHandler(); }
	  
	  
	  @Bean public AuthenticationFailureHandler authenticationFailureHandler() {
	  return new CustomAuthenticationFailureHandler(); }
	  
	  @Bean public CustomLogoutSuccessHandler customLogoutSuccessHandler() { return
	  new CustomLogoutSuccessHandler(); }
	  
	  @Bean public SimpleUrlAuthenticationFailureHandler failureHandler() { return
	  new SimpleUrlAuthenticationFailureHandler("/user/login?error=true"); }
	  
	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.authenticationProvider(authProvider()); }
	 */
	  
		/*
		 * public AuthenticationProvider authProvider() {
		 * CustomUserDetailsAuthenticationProvider provider = new
		 * CustomUserDetailsAuthenticationProvider(passwordEncoder, userDetailsService);
		 * return provider; }
		 */
	 }
