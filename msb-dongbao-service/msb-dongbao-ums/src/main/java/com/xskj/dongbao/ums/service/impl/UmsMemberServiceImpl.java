package com.xskj.dongbao.ums.service.impl;

import com.xskj.dongbao.common.base.result.ResultWrapper;
import com.xskj.dongbao.common.util.JwtUtils;
import com.xskj.dongbao.ums.entity.DTO.UmsMemberLoginParamDTO;
import com.xskj.dongbao.ums.entity.DTO.UmsMemberRegisterParamDTO;
import com.xskj.dongbao.ums.entity.UmsMember;
import com.xskj.dongbao.ums.entity.response.UserMemberLoginResponse;
import com.xskj.dongbao.ums.mapper.UmsMemberMapper;
import com.xskj.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 王家欣
 * @since 2022-07-03
 */
@Service
public class UmsMemberServiceImpl  implements UmsMemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        UmsMember u = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO,u);
        String encode = passwordEncoder.encode(umsMemberRegisterParamDTO.getPassword());
        u.setPassword(encode);
        umsMemberMapper.insert(u);
        return ResultWrapper.getSuccessBuilder().build();
    }

    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO){
        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());
        if (null != umsMember){
            String passwordDb = umsMember.getPassword();
            if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(),passwordDb)){
                return ResultWrapper.getFailBuilder().build();
            }
        }else {
            return ResultWrapper.getFailBuilder().build();
        }
        String token = JwtUtils.createToken(umsMemberLoginParamDTO.getUsername());
        UserMemberLoginResponse userMemberLoginResponse = new UserMemberLoginResponse();
        userMemberLoginResponse.setToken(token);
        umsMember.setPassword("");
        userMemberLoginResponse.setUmsMember(umsMember);
        return ResultWrapper.getSuccessBuilder().data(userMemberLoginResponse).build();
    }

    @Override
    public ResultWrapper edit(UmsMember umsMember) {
        umsMemberMapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data(umsMember).build();
    }
}
