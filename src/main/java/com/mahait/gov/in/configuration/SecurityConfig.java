package com.mahait.gov.in.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.mahait.gov.in.customfilter.CustomAuthenticationFailureHandler;
import com.mahait.gov.in.customfilter.CustomAuthenticationFilter;
import com.mahait.gov.in.customfilter.CustomLogoutSuccessHandler;
import com.mahait.gov.in.customfilter.CustomSimpleUrlAuthenticationSuccessHandler;
import com.mahait.gov.in.customfilter.UsernameDecryptFilter;
import com.mahait.gov.in.service.UserService;

@Configuration
public class SecurityConfig {

	@Autowired
	UserService userServiceImpl;


	@Autowired
	PasswordEncoder passwordEncoder;

  
	
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/","/i18n/*","/lib/**","/register", "/user/login","/user/logOut", "/css/**", "/js/**","/images/**","/pdf/**").permitAll()
                    .requestMatchers("/mdc/**").hasRole("MDC")
                    .requestMatchers("/user/**").permitAll()
                    .requestMatchers("/employee/**").hasRole("USER")
                    .requestMatchers("/ddoast/**").hasRole("DDO_AST")
              	  .requestMatchers("/ddo/**").hasRole("DDO")
              	  .requestMatchers("/super/**").hasRole("SUPER")
              	  .requestMatchers("/user/**").hasRole("USER")
              //	 .requestMatchers("/user/login").permitAll()
              	  .requestMatchers("/services/**").denyAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/user/login")
                    .successHandler(customAuthenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
                    .loginProcessingUrl("/login")
                    .permitAll()
            )
            .logout(logout -> logout
                   // .logoutUrl("/user/logOut")
                    .logoutSuccessHandler(customLogoutSuccessHandler())
                   // .logoutSuccessUrl("/login")
                    .permitAll()
                    .clearAuthentication(true) 
                    .invalidateHttpSession(true) 
                    .deleteCookies("JSESSIONID")
            )
				
				 .exceptionHandling(exceptions -> exceptions
				  .accessDeniedPage("/user/login?unauthorize") )
             .sessionManagement(session -> session
            	        .invalidSessionUrl("/user/login?expired")
            )
           .addFilterBefore(new UsernameDecryptFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
    @Bean 
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
	  CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
	  filter.setAuthenticationManager(authenticationManager());
	  filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
	  filter.setAuthenticationFailureHandler(authenticationFailureHandler());
	  return filter; 
	}
    
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		ProviderManager providerManager = new ProviderManager(authenticationProvider);
		providerManager.setEraseCredentialsAfterAuthentication(false);
		return providerManager;
    }
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    	return new CustomSimpleUrlAuthenticationSuccessHandler();
    }
    
    @Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
    
    @Bean
	public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

    @Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	// Register HttpSessionEventPublisher
	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
	}
}