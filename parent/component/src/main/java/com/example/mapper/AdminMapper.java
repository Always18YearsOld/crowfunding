package com.example.mapper;


import com.example.entity.Admin;
import com.example.entity.AdminExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    List<Admin> selectAdminKeyword(String keyword);
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    void deleteRelationship(Integer adminId);

    void insertNewRelationship(@Param("adminId")Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
}