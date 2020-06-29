package com.example.service.api;

import com.example.entity.Admin;
import com.example.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2020/5/14
 */
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
