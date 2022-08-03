package com.xskj.dongbao.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xskj.dongbao.ums.entity.UmsMember;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author 晁鹏飞
 * @since 2020-12-23
 */
@Repository
public interface UmsMemberMapper extends BaseMapper<UmsMember>  {

    UmsMember selectByName(String name);

}
