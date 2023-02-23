package com.project.youknow.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private JwtAuthProvider jwtAuthProvider;

    public JwtAuthenticationFilter(JwtAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String jwt = resolveToken(httpReq);
        String requestURI = httpReq.getRequestURI();

        if (StringUtils.hasText(jwt) && jwtAuthProvider.validateToken(jwt)) {
            Authentication authentication = jwtAuthProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.info("Security Context에 '{}' 인증 정보를 저장했습니다. uri: {}", authentication.getName(), requestURI);
        } else {
            LOGGER.info("유효한 JWT 토큰이 없습니다., uri: {}", requestURI);
        }
        chain.doFilter(httpReq, response);
    }
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
