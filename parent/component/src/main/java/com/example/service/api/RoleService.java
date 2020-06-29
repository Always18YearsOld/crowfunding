package com.example.service.api;

import com.example.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/11
 */
public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);

    void saveRole(Role role);

    void updateRole(Role role);
    void remove(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
