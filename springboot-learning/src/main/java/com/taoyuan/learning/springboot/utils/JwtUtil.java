package com.taoyuan.learning.springboot.utils;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-26 15:38
 * @Description:
 * @Version: 1.0
 */


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author .29.
 * @create 2024-03-16 0:29
 * @description 生成JwtToken， 获取JwtToken中加密的信息， 判断JwtToken是否合法
 */
// @Configuration
public class JwtUtil {
    private static final String USER_CLAIMS_KEY = "user";

    // 过期时间
    @Value("${jwt.expire-time}")
    private long EXPIRE_TIME;

    // 密钥
    @Value("${jwt.secret}")
    private String SECRET;

    /**
     * 创建JWT Token
     *
     * @param payload 载荷（Claims）
     * @return JWT Token
     */
    public String createToken(Map<String, Object> payload) {
        // 1. 创建一个密钥
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        // 2. 创建JWT Builder
        // 注意：这里的签名算法不能是 RS256，需要使用 HS256
        io.jsonwebtoken.JwtBuilder builder = Jwts.builder()
                .setClaims(payload)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256);

        // 3. 生成JWT Token
        return builder.compact();
    }

    /**
     * 解析JWT Token
     *
     * @param token JWT Token
     * @return 载荷（Claims）
     */
    public Map<String, Object> parseToken(String token) {
        // 1. 获取密钥
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        // 2. 解析JWT Token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // 3. 将Claims里的内容转换成Map
        Map<String, Object> payload = new HashMap<>(claims);
        payload.remove("exp");
        payload.remove("iat");
        payload.remove("iss");
        payload.remove("aud");
        payload.remove("nbf");
        payload.remove("sub");
        payload.remove("jti");

        return payload;
    }

    /**
     * 获取JWT Token的过期时间
     *
     * @param token JWT Token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Claims claims = parseClaims(token);
        if (claims != null) {
            return claims.getExpiration();
        }
        return null;
    }

    /**
     * 验证JWT Token是否有效
     *
     * @param token JWT Token
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取payload中的用户信息
     *
     * @param token JWT Token
     * @return 用户信息
     */
    public Map<String, Object> getUserFromToken(String token) {
        Map<String, Object> user = null;
        Claims claims = parseClaims(token);
        if (claims != null) {
            user = (Map<String, Object>) claims.get(USER_CLAIMS_KEY);
        }
        return user;
    }

    /**
     * 解析JWT Token中的Claims
     *
     * @param token JWT Token
     * @return Claims
     */
    private Claims parseClaims(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }


}


