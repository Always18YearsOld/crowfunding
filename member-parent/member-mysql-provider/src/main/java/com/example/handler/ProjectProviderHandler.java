package com.example.handler;

import com.example.entity.vo.DetailProjectVO;
import com.example.entity.vo.PortalProjectVO;
import com.example.entity.vo.PortalTypeVO;
import com.example.entity.vo.ProjectVO;
import com.example.service.api.ProjectProviderService;
import com.example.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/24
 */
@RestController
public class ProjectProviderHandler {
    @Autowired
    private ProjectProviderService projectProviderService;

    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId){

        try {
            DetailProjectVO detailProjectVO = projectProviderService.getDetailProjectVO(projectId);
            return ResultEntity.sucessWithData(detailProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }



    }

    // 查询t_type分类，进行距最新四个遍历
    @RequestMapping("get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote(){
        try {
            List<PortalTypeVO> portalTypeVOList = projectProviderService.getPortalTypeVO();
            return ResultEntity.sucessWithData(portalTypeVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(
            @RequestBody ProjectVO projectVO,
            @RequestParam("memberId") Integer memberId){
        try {

            projectProviderService.saveProject(projectVO,memberId);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }
}
