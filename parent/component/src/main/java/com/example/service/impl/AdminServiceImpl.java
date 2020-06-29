package com.example.service.impl;

import com.example.constant.Constant;
import com.example.entity.Admin;
import com.example.entity.AdminExample;
import com.example.exception.LoginAcctAlreadyInUseException;
import com.example.exception.LoginAcctAlreadyInUseForUpdateException;
import com.example.exception.LoginFailedException;
import com.example.mapper.AdminMapper;
import com.example.service.api.AdminService;
import com.example.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author Administrator
 * @Date 2020/5/6
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    private Logger logger= LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveAdmin(Admin admin) {

        String userPswd = admin.getUserPswd();
        // 旧加密形式
//      userPswd = CrowdUtil.md5(userPswd);
        userPswd = passwordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        admin.setCreateTime(format1);
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常全类名="+e.getClass().getName());
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(Constant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }

        //throw new RuntimeException();
    }

    @Override
    public List<Admin> getAll() {

        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if (list == null || list.size() == 0){
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size()>1){
            throw new RuntimeException(Constant.MESSAGE_SYSTEM_LOGIN_NOT_UNIQUE);
        }
        Admin admin = list.get(0);
        if (admin == null){
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED);
        }
        String userPswdDB = admin.getUserPswd();
        String userPswdForm = CrowdUtil.md5(userPswd);
        if (!Objects.equals(userPswdDB,userPswdForm)){
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用PageHeloer的静态方法
        // 非侵入式设计，原本的查询不必有任何修改
        PageHelper.startPage(pageNum,pageSize);
        // 2.执行查询
        List<Admin> list = adminMapper.selectAdminKeyword(keyword);
        // 3.封装到PageInfo对象中
        return new PageInfo<>(list);


    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        return admin;
    }

    @Override
    public void update(Admin admin) {
        //表示有选择的更新，null不更新
        try {
            adminMapper.updateByPrimaryKey(admin);
        } catch (Exception e) {

            e.printStackTrace();
            logger.info("异常全类名="+e.getClass().getName());
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseForUpdateException(Constant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        //删除旧数据，再加新数据
        adminMapper.deleteRelationship(adminId);
        if (roleIdList!=null&&roleIdList.size()>0){

            adminMapper.insertNewRelationship(adminId,roleIdList);
        }    }

    @Override
    public Admin getAdminByLoginAcct(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAcctEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(example);
        Admin admin = admins.get(0);
        return admin;
    }
}
