package com.yuansheng.resultful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuansheng.resultful.domain.UUser;
import com.yuansheng.resultful.mapper.UUserMapper;

import com.yuansheng.resultful.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UUserMapper userMapper;

	//查找用户
	public UUser findByUsername(String name) {
		return userMapper.findByUsername(name);
	}
	
	
    //列出所有用户
	public List<UUser> selectUserByAll() {
		// TODO Auto-generated method stub
		return userMapper.selectUserByAll();
	}

    //添加用户，添加成功返回success
	public String insertuserSelective(UUser record) {
		if(userMapper.insertSelective(record)>0) {
			return "success";		
		}
		else
			return "erro";
	}
	
	
}
