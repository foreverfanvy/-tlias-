package com.yaojingxi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGenerateJwt() {
        //生成Jwt令牌
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("username","yaojingxi");

        String key = "itheimaitcastgoatbianchengxiatlias123456789abc"; // 移除所有破折号，保持32字节以上

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key) //指定算法和密钥
                .addClaims(map) //添加自定义的信息
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //设置过期时间,为10h
                .compact(); //生成Jwt令牌

        //输出生成的Jwt令牌
        System.out.println("完整的JWT令牌：" + jwt);

        // 按照点号分割JWT并输出
        String[] parts = jwt.split("\\.");
        System.out.println("JWT令牌的Header：" + parts[0]);
        System.out.println("JWT令牌的Payload：" + parts[1]);
        System.out.println("JWT令牌的Signature：" + parts[2]);
    }
    @Test
    public  void testParseJwt() {
        //解析Jwt令牌
        Claims claims = Jwts.parser().setSigningKey("itheimaitcastgoatbianchengxiatlias123456789abc").
                parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ5YW9qaW5neGkiLCJleHAiOjE3NTI2NjkxOTV9.jauYChtb6phHM7m3KIGqbw8G0uhH8-D8EGDmjoJu9HI")
                .getBody();
        System.out.println(claims);
    }
}
