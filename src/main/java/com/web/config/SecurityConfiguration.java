package com.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.web.security.CustomSuccessHandler;
import com.web.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests( auth -> auth
				.requestMatchers("/admin/**", "/admin-home").hasRole("ADMIN")
				.requestMatchers("/cart-*", "/order-payment").hasRole("USER")
				.requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/davy/login", "/davy/register", "/davy/shop", "/register-acc", "/products-list", "/products-list/**", "/products-search", "/products-search/**", "/products-detail-*", "/add-to-cart", "/cart-delete").permitAll()
				.anyRequest().permitAll()
				)
		.requestCache(cache -> cache
				.requestCache(new HttpSessionRequestCache()))
		.formLogin(login -> login
				.loginPage("/davy/login").usernameParameter("username").passwordParameter("password").permitAll()
        		.loginProcessingUrl("/form-login") // Action URL in form
                .successHandler(myAuthenticationSuccessHandler()) // URL chuyển sau đăng nhập                
        		)
        .logout(log -> log
        		.logoutUrl("/davy/logout").deleteCookies("JSESSIONID", "PRE_LOGIN_URL")//xóa cookie JSESSIONID (JSESSIONID là mặc định do Java Servlet (và Spring Boot) tạo ra, dùng để quản lý session người dùng) 
                .logoutSuccessUrl("/davy/login?logout")
                .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/davy/access-denied")//khi người dùng đăng nhập không có quyền hệ thống sẽ chuyển sang /access-denied
                )
                .sessionManagement(session -> session
                		.maximumSessions(1)
                		.expiredUrl("/davy/login?sessionTimeout")
                );
        return http.build();		
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public CustomUserDetailService customUserDetailService() {
	    	return new CustomUserDetailService();
	    }
	    
	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(customUserDetailService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	    
	    @Bean
	    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
	    	return new CustomSuccessHandler();
	    }
	    
//	    @Bean
//	    public WebSecurityCustomizer webSecurityCustomizer() {
//	        return (web) -> web.ignoring().requestMatchers("/assets/**", "/favicon.ico");
//	    }
}
