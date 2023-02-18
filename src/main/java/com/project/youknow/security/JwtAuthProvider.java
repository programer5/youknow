package com.project.youknow.security;

import com.project.youknow.member.entitiy.Member;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtAuthProvider {

    @Value("${spring.jwt.secret.at}")
    private String atSecretKey;

    @PostConstruct
    protected void init() {
        atSecretKey = Base64.getEncoder().encodeToString(atSecretKey.getBytes());
    }

    private final UserDetailsService userDetailsService;

    /**
     * @throws Exception
     * @method 설명: jwt 토큰 발급
     */
    public String createToken(Member member) {
        /**
         * 토큰 발급을 위한 데이터는 UserDetails에서 담당
         * 따라서 UserDetails를 세부 구현한 CustomUserDetails로 회원정보 전달
         */
        CustomUserDetails user = new CustomUserDetails(member);

        // 유효기간 설정을 위한 Date 객체 선언
        Date date = new Date();
        final JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("accesstoken").setExpiration(new Date(date.getTime() * (1000L * 60 * 60 * 12)))
                .claim("member", member)
                .signWith(SignatureAlgorithm.HS256, atSecretKey);

        return builder.compact();
    }

    // 토큰에서 회원 정보 추출
    public String getMemberPK(String token) {
        return Jwts.parser().setSigningKey(atSecretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
