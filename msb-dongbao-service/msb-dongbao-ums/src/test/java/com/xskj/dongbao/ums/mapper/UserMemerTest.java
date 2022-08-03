package com.xskj.dongbao.ums.mapper;

import com.xskj.dongbao.ums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMemerTest {
    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Test
    public void testInsert(){
        UmsMember t = new UmsMember();
        t.setUsername("cpf2");
        t.setStatus(0);
        t.setPassword("1");
        t.setNote("note");
        t.setNickName("nick");
        t.setEmail("email");
        umsMemberMapper.insert(t);
    }
    @Test
    public void testUpdate(){
        UmsMember t = new UmsMember();
        t.setId(64L);
        umsMemberMapper.updateById(t);
    }
}
