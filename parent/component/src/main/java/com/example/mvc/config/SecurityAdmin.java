package com.example.mvc.config;

import com.example.entity.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 考了user对象包含账号和密码，
 * 为了能够获取原始的admin对象，专门创建这个类对User进行扩展
 * @Author Administrator
 * @Date 2020/5/15
 */

public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;
    private Admin orignaAdmin;
    public SecurityAdmin(
            // 传入原始的admin对象
            Admin orignaAdmin,
            // 创建角色、权限信息的集合
            List<GrantedAuthority> authorities){
        // 调用父类构造器
        super(orignaAdmin.getLoginAcct(),orignaAdmin.getUserPswd(),authorities);
        this.orignaAdmin=orignaAdmin;

        // 将原始Admin对象的密码擦除
        this.orignaAdmin.setUserPswd(null);
    }
    public Admin getOrignaAdmin() {
        return orignaAdmin;
    }


}
