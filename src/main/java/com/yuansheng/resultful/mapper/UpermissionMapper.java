package com.yuansheng.resultful.mapper;

import com.yuansheng.resultful.domain.Upermission;
import com.yuansheng.resultful.domain.UpermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpermissionMapper {
    long countByExample(UpermissionExample example);

    int deleteByExample(UpermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Upermission record);

    int insertSelective(Upermission record);

    List<Upermission> selectByExample(UpermissionExample example);

    Upermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Upermission record, @Param("example") UpermissionExample example);

    int updateByExample(@Param("record") Upermission record, @Param("example") UpermissionExample example);

    int updateByPrimaryKeySelective(Upermission record);

    int updateByPrimaryKey(Upermission record);
}