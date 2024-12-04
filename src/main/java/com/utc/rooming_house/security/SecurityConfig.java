package com.utc.rooming_house.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.utc.rooming_house.security.securityv2.CustomUserDetailsService;
import com.utc.rooming_house.security.securityv2.JwtAuthenticationEntryPoint;
import com.utc.rooming_house.security.securityv2.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	CustomUserDetailsService customUserDetailsService;
 
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Autowired
	private NoPasswordAuthenticationProvider noPasswordAuthenticationProvider;

//	  @Bean
//	  public AuthTokenFilter authenticationJwtTokenFilter() {
//	    return new AuthTokenFilter();
//	  }
	
	private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/auth/**",
            "/**"
	};
	  
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(customUserDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	  @Bean
	  public JwtAuthenticationFilter authenticationJwtTokenFilter() {
	    return new JwtAuthenticationFilter();
	  }
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	    .cors().and()
	    .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests((auth) -> 
	          auth.requestMatchers(WHITE_LIST_URL
					).permitAll()
	          .requestMatchers("/swagger-ui/index.html#/").permitAll()
	              .anyRequest().authenticated()
	        );
	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*"); // Allows all origins
		config.addAllowedHeader("*"); // Allows all headers
		config.addAllowedMethod("*"); // Allows all methods (GET, POST, etc.)
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}