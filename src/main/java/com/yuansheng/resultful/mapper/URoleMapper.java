package com.yuansheng.resultful.mapper;

import com.yuansheng.resultful.domain.URole;
import com.yuansheng.resultful.domain.URoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface URoleMapper {
    long countByExample(URoleExample example);

    int deleteByExample(URoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    List<URole> selectByExample(URoleExample example);

    URole selectByPrimaryKey(Long id);

    //查询所有角色
    List<URole> selectRoleByAll();
    
    int updateByExampleSelective(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByExample(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);
}