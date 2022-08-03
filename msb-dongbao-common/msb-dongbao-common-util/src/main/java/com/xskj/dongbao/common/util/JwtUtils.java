package com.xskj.dongbao.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtils {

    private static final String secret = "asdfsaf";
    public static String createToken(String subject){
        String token=Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+1000*3))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
        return token;
    }

    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) {
        String name = "王家欣";
        String token = createToken(name);
        System.out.println("token"+token);

        String token1 = parseToken(token);
        System.out.println("解析出来来："+token1);

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        token1 = parseToken(token);
        System.out.println("解析出来来："+token1);
    }
}
