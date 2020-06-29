package com.example.service.api;

import com.example.entity.vo.DetailProjectVO;
import com.example.entity.vo.PortalTypeVO;
import com.example.entity.vo.ProjectVO;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/24
 */
public interface ProjectProviderService {
    void saveProject(ProjectVO projectVO, Integer memberId);
    List<PortalTypeVO> getPortalTypeVO();
    DetailProjectVO getDetailProjectVO(Integer projectId);
}
