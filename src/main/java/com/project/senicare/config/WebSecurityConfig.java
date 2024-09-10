package com.project.senicare.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.senicare.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// Spring Web 보안 설정
@Configurable
@Configuration // Bean을 읽기위해 사용
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  protected SecurityFilterChain configure(HttpSecurity secuirty) throws Exception {
    secuirty
        // basic 인증 방식 미사용
        .httpBasic(HttpBasicConfigurer::disable)
        // session 미사용 (유지 X)
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // CSRF 취약점 대비 미지정
        .csrf(CsrfConfigurer::disable)
        // CORS 정책 설정
        .cors(cors -> cors.configurationSource(configurationSource()))
        // URL 패턴 및 HTTP 메서드에 따라 인증 및 인가 여부 지정
        .authorizeHttpRequests(request -> request
            .requestMatchers("/api/v1/auth/**", "/").permitAll()
            .anyRequest().authenticated())
        // 필터 등록
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return secuirty.build();
  }

  @Bean
  protected CorsConfigurationSource configurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*"); // 모든 곳에서의 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}