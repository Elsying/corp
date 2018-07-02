package com.yuansheng.resultful.service.impl;

import com.yuansheng.resultful.domain.Order;
import com.yuansheng.resultful.mapper.OrderMapper;
import com.yuansheng.resultful.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper ordermapper;


	//添加订单
	public String insertSelective(Order record){
		if(ordermapper.insertSelective(record)>0){
			return "Success";
		}
		return "erro";
	}

	//根据id查询
	public Order selectByPrimaryKey(int id) {

		return ordermapper.selectByPrimaryKey(id);
	}
	//删除指定id订单
	public int deleteByPrimaryKey(int id) {
		if(ordermapper.deleteByPrimaryKey(id)>0) {
			return 200;//删除成功
		}
		return 500;//删除失败
	}

	//查找所有
	public List<Order> selectByAll() {
		// TODO Auto-generated method stub
		return ordermapper.selectByAll();
	}
	

		

	

}
