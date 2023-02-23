package com.project.youknow.jwt;

import com.google.gson.Gson;
import com.project.youknow.common.response.ResVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Gson gson = new Gson();
        PrintWriter writer = response.getWriter();
        HttpStatus errorCode = HttpStatus.UNAUTHORIZED;
        ResVO.ResVOBuilder res = ResVO.builder()
                .status(errorCode.value())
                .message(errorCode.getReasonPhrase());

        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);;
            writer.write(gson.toJson(res));
        } catch (NullPointerException e) {
            LOGGER.error("응답 메시지 작성 에러", e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        response.getWriter().write(gson.toJson(res));
    }
}
