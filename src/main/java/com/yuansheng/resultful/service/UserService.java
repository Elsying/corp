package com.yuansheng.resultful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yuansheng.resultful.domain.Order;
import com.yuansheng.resultful.domain.UUser;


public interface UserService {

	//查找用户
	UUser findByUsername(String name);
	
	//用户列表查询所有
	List<UUser> selectUserByAll();
	
	//添加用户
	String insertuserSelective(UUser user);
	
	
}
