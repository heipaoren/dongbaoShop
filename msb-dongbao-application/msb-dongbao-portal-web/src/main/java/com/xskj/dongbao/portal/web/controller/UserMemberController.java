package com.xskj.dongbao.portal.web.controller;

import com.xskj.dongbao.common.base.annotations.TokenCheck;
import com.xskj.dongbao.common.base.result.ResultWrapper;
import com.xskj.dongbao.common.util.JwtUtils;
import com.xskj.dongbao.ums.entity.DTO.UmsMemberLoginParamDTO;
import com.xskj.dongbao.ums.entity.DTO.UmsMemberRegisterParamDTO;
import com.xskj.dongbao.ums.entity.UmsMember;
import com.xskj.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        return umsMemberService.register(umsMemberRegisterParamDTO);
    }
    @PostMapping("/login")
    public ResultWrapper login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO){
       return umsMemberService.login(umsMemberLoginParamDTO);
    }

    @PostMapping("/edit")
    @TokenCheck
    public ResultWrapper edit(@RequestBody UmsMember  umsMember){
        return  umsMemberService.edit(umsMember);
    }

    /**
     * 测试的
     * @param token
     * @return
     */
    @GetMapping("verify")
    public String verify(String token){
        String s = JwtUtils.parseToken(token);
        return s;
    }
}
