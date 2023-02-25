package com.project.youknow.jwt;

import com.google.gson.Gson;
import com.project.youknow.common.response.ResVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Gson gson = new Gson();
        PrintWriter writer = response.getWriter();
        HttpStatus errorCode = HttpStatus.UNAUTHORIZED;
        ResVO res = ResVO.builder()
                .status(errorCode.value())
                .message(errorCode.getReasonPhrase()).build();

        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
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
