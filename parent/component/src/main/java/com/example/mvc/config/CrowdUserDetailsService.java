package com.example.mvc.config;

import com.example.entity.Admin;
import com.example.entity.Role;
import com.example.service.api.AdminService;
import com.example.service.api.AuthService;
import com.example.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/15
 */
@Component
public class CrowdUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据账号名称查询Admin对象
        Admin admin = adminService.getAdminByLoginAcct(username);
        Integer adminId = admin.getId();
        // 根据adminId查询角色信息
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        //权限信息
        List<String> authNameList= authService.getAssignedAuthNameByAdminId(adminId);
        //GrantedAuthority存储集合
        List<GrantedAuthority> authorities=new ArrayList<>();
        //存入角色信息
        for (Role role : assignedRoleList) {
            // 角色前缀
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        //存入权限信息
        for (String authName : authNameList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }
        //封装SecurityAdmin对象
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);


        return securityAdmin;
    }
}
