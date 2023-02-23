package com.project.youknow.security;

import com.project.youknow.jwt.JwtAccessDeniedHandler;
import com.project.youknow.jwt.JwtAuthProvider;
import com.project.youknow.jwt.JwtAuthenticationEntryPoint;
import com.project.youknow.jwt.JwtAuthenticationFilter;
import com.project.youknow.member.enumType.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    private final JwtAuthProvider jwtAuthProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("accessToken");
        configuration.addExposedHeader("content-disposition");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/favicon.ico"));
    }

    @Bean
    public void filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signin/**").permitAll()
                .antMatchers("/exception/**").permitAll()
                .anyRequest().hasAnyRole(MemberRole.MEMBER.getCode())
                .and()
                .cors()
                .and()
                .exceptionHandling().accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtAuthProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
