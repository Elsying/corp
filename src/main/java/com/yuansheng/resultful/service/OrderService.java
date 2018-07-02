package com.yuansheng.resultful.service;


import java.util.List;

import com.yuansheng.resultful.domain.Order;

public interface OrderService {
	// limit查询    currentPage为当前页
	//查询总页数
	// int selectCount();
	//添加订单
	String insertSelective(Order record);
	//根据id查找订单
	 Order selectByPrimaryKey(int id);
	//删除某订单
	int deleteByPrimaryKey(int id);
	//查询所有
	List<Order> selectByAll();
	
}
