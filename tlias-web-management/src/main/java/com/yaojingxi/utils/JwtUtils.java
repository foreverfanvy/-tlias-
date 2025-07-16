package com.yaojingxi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

public class JwtUtils {
    private static final String SECRET_KEY = "itheimaitcastgoatbianchengxiatlias123456789abc";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分payload中存储的内容
     * @return JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(KEY)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分payload中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
