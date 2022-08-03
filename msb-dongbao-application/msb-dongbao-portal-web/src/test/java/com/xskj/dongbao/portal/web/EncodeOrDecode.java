package com.xskj.dongbao.portal.web;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class EncodeOrDecode {
    @Test
    public void md5(){
        String sourceString = "123456";
        String s = DigestUtils.md5DigestAsHex(sourceString.getBytes(StandardCharsets.UTF_8));
        System.out.println("原值："+sourceString+"---加密值:"+s);
    }
    @Test
    public void bcrypt(){
        String sourceString = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第一次加密:"+encode);
        boolean matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第一次验证"+matches);
         encode = bCryptPasswordEncoder.encode(sourceString);
        matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第一次验证"+matches);
        System.out.println("第二次加密："+encode);

    }
}
