package com.yuansheng.resultful.mapper;

import com.yuansheng.resultful.domain.Order;
import com.yuansheng.resultful.domain.OrderExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    //总记录数
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);
    /**
     * 查询用户记录总数
     * @return
     */
    int selectCount();

    Order selectByPrimaryKey(Integer id);
    
    //查询所有
    List<Order> selectByAll();

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


}