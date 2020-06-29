package com.example.mvc.handler;

import com.example.entity.Menu;
import com.example.service.api.MenuService;
import com.example.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Administrator
 * @Date 2020/5/12
 */
// @Controller
@RestController
public class MenuHandler {
    @Autowired
    private MenuService menuService;

    //@ResponseBody
    @RequestMapping("menu/remove.json")//.json不重要
    public ResultEntity<Menu> removeMenu(@RequestParam("id") Integer id) throws InterruptedException{

        menuService.removeMenu(id);
        return ResultEntity.successWithoutData();
    }

    //@ResponseBody
    @RequestMapping("/menu/update.json")//.json不重要
    public ResultEntity<Menu> updateMenu(Menu menu) throws InterruptedException{

        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }

    //@ResponseBody
    @RequestMapping("/menu/save.json")//.json不重要
    public ResultEntity<Menu> saveMenu(Menu menu) throws InterruptedException{

        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }
    //@ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")//.json不重要
    public ResultEntity<Menu> getWholeTree() {
        List<Menu> menuList = menuService.getAll();
        // 声明一个变量用来存储找到的根节点
        Menu root = null;
        Map<Integer, Menu> menuMap = new HashMap<>();

        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }
        // pid不为null有父节点
        for (Menu menu : menuList) {
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
                continue;
            }
            Menu father = menuMap.get(pid);
            father.getChildren().add(menu);
        }

        return ResultEntity.sucessWithData(root);
    }

}
//    public ResultEntity<Menu> getWholeTreeOld(){
//        List<Menu> menuList=menuService.getAll();
//        // 声明一个变量用来存储找到的根节点
//        Menu root = null;
//
//        for (Menu menu : menuList){
//            Integer pid = menu.getPid();
//            if (pid == null){
//                root=menu;
//                // 找到跟节点后继续下个循环
//                continue;
//            }
//            // pid不为null有父节点
//            for (Menu menuFather : menuList) {
//                Integer id = menuFather.getId();
//                if (Objects.equals(pid,id)){
//                    menuFather.getChildren().add(menu);
//                    break;
//                }
//
//
//            }
//
//        }
//        return ResultEntity.sucessWithData(root) ;
//    }
//}
