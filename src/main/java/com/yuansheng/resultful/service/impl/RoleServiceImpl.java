package com.yuansheng.resultful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuansheng.resultful.domain.URole;
import com.yuansheng.resultful.mapper.OrderMapper;
import com.yuansheng.resultful.mapper.URoleMapper;
import com.yuansheng.resultful.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private URoleMapper roleMapper;
	
	//所有角色
	public List<URole> selectRoleByAll() {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleByAll();
	}

}
