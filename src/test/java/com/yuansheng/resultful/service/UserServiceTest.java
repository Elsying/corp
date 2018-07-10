package com.yuansheng.resultful.service;

import com.yuansheng.resultful.domain.UUser;
import com.yuansheng.resultful.mapper.UUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 可以测试每个类，快捷生成方式crtl+shift+t    也可以测试数据库操作等
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybaits.xml")
public class UserServiceTest {
    @Autowired
    private UUserMapper userMapper;

    @Test
    public void findByUsername() {
        UUser uUser=userMapper.findByUsername("lsy");
        System.out.println(uUser.getPassword());
    }

//    @Test
//    public void selectUserByAll() {
//    }
//
//    @Test
//    public void insertuserSelective() {
//    }
}