package com.xskj.dongbao.ums.entity.response;

import com.xskj.dongbao.ums.entity.UmsMember;
import lombok.Data;

@Data
public class UserMemberLoginResponse {
    private String token;
    private UmsMember umsMember;
}
