package com.reactiveexample.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
//@EnableWebFlux
public class WebSecurityConfig implements WebFluxConfigurer{

@Autowired
private JwtTokenProvider jwtTokenProvider;

@Autowired
private AuthEntryPointJwt unauthorizedHandler;


/*  ဒါက  synchronous and blocking I/O မှာသုံးတာ ။ CORS ကို globally configure လုပ်တာ
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
        }
    };
} 
*/

/* ဒါက  Asynchronous and non-blocking I/O မှာသုံးတာ ။ CORS ကို globally configure လုပ်တာ */
@Override
public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**")
    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	WebFluxConfigurer.super.addCorsMappings(registry);
}


//ဒါက  Spring security configuation နည်းလမ်းအသစ်ပါ
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			 
  http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
      .and()
      //not storing the Spring Context in the session
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      
      
      //Entry points
  		.authorizeHttpRequests()
  		//.requestMatchers("/consume").permitAll()
  		.requestMatchers("/login").permitAll()
  		//.requestMatchers("/hello*").permitAll()
  		//.requestMatchers("/ws/echo*").permitAll()
  		.requestMatchers("/register**").permitAll()
  		.requestMatchers("/**").permitAll()

  		.anyRequest().authenticated();
  		
  		
  		//If a user try to access a resource without having enough permissions
  		http.exceptionHandling().accessDeniedPage("/login");

  
  		//Apply JWT
  		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
  		
  	
  		http.httpBasic();
  		
  	return http.build();
}


@Bean
public static  PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder(12);
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
  return authConfiguration.getAuthenticationManager();
}

}	