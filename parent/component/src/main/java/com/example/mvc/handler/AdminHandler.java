package com.example.mvc.handler;

import com.example.constant.Constant;
import com.example.entity.Admin;
import com.example.service.api.AdminService;

import com.example.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/8
 */
@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/update.html")
    public String update(//@RequestParam("adminId") Integer adminId,
                       @RequestParam("pageNum") Integer pageNum,
                       @RequestParam("keyword") String keyword,
                       Admin admin
    ){
      adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/admin/to/edit/page.html")
    public String edit(@RequestParam("adminId") Integer adminId,
                       @RequestParam("pageNum") Integer pageNum,
                       @RequestParam("keyword") String keyword,
                       ModelMap modelMap
                       ){

        Admin admin=adminService.getAdminById(adminId);
        modelMap.addAttribute("admin",admin);
        return "admin-edit";
    }
    @PreAuthorize("hasAuthority('user:save')")
    @RequestMapping("/admin/save.html")
    public String save(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword){
        //执行参数
        adminService.remove(adminId);
        //不带数据 查询不了数值
        //return "admin-page";
        //forword：重复删除
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            // 配置默认值
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            ModelMap modelMap
    ) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute(Constant.ATTR_NAME_NAME_INFO,pageInfo);
        return "admin-page";
    }
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){
        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
    // 改成security没有引用了
    @RequestMapping("/admin/do/login.html")
    public String doLogin(HttpServletRequest request,

          // 错误原因：由于访问地址弄成/admin/do/login.html使得不经过登录提交直接跳转，使得参数为null
                          // 应该admin/to/login/page.html进行访问再由登录跳转admin/do/login.html，获得数据
           @RequestParam("loginAcct") String loginAcct,
           @RequestParam("userPswd") String userPswd,
            HttpSession session
            ){
        // 进行账号密码判断
        //String loginAcct = request.getParameter("loginAcct");
       // String userPswd = request.getParameter("userPswd");

        Admin admin=adminService.getAdminByLoginAcct(loginAcct,userPswd);
        session.setAttribute(Constant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/to/main/page.html";


    }
    @ResponseBody
    @PostAuthorize("returnObject.data.loginAcct == principal.username")
    @RequestMapping("/admin/test/post/filter.json")
    public ResultEntity<Admin> getAdminById(){
        Admin admin = new Admin();
        admin.setLoginAcct("adminOperator");
        return ResultEntity.sucessWithData(admin);
    }
    @PreFilter(value = "filterObject==0")
    @ResponseBody
    @RequestMapping("admin/test/pre/filter")
    public ResultEntity<List<Integer>> saveList(
            @RequestBody List<Integer> valueList){
        return ResultEntity.sucessWithData(valueList);
    }
}
