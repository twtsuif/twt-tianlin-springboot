package com.twt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "twt.jwt")
public class JwtUtils {
    private String secret;
    private long expire;

    // 生成JWT
    public String generateToken(long userId) {

        // 当前时间和过期时间
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                // 设置Header
                .setHeaderParam("typ", "JWT")
                // 设置Body中的Subject
                .setSubject(userId + "")
                // 设置过期时间
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                // 加密算法
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 得到Token的Body部分
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

    // 验证Token是否过期
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
