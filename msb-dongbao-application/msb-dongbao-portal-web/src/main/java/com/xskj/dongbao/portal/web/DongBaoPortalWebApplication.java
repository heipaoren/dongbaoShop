package com.xskj.dongbao.portal.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages= {"com.xskj"})
@MapperScan("com.xskj.dongbao.ums.mapper")
public class DongBaoPortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DongBaoPortalWebApplication.class,args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
