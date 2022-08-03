package com.xskj.dongbao.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xskj.dongbao.common.base.result.ResultWrapper;
import com.xskj.dongbao.ums.entity.DTO.UmsMemberLoginParamDTO;
import com.xskj.dongbao.ums.entity.DTO.UmsMemberRegisterParamDTO;
import com.xskj.dongbao.ums.entity.UmsMember;


/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 王家欣
 * @since 2022-07-03
 */
public interface UmsMemberService  {

    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

    ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

    ResultWrapper edit(UmsMember umsMember);

}
