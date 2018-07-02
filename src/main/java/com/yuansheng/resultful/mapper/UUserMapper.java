package com.yuansheng.resultful.mapper;

import com.yuansheng.resultful.domain.UUser;
import com.yuansheng.resultful.domain.UUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UUserMapper {
    long countByExample(UUserExample example);

    int deleteByExample(UUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    List<UUser> selectByExample(UUserExample example);

   //查找用户
    UUser findByUsername(String name);
    
    //用户列表查询所有
	List<UUser> selectUserByAll();
    
    UUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByExample(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);
}