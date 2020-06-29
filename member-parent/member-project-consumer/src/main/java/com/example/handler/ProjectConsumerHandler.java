package com.example.handler;

import com.example.api.MySQLRemoteService;
import com.example.config.OSSProperties;
import com.example.constant.Constant;
import com.example.entity.vo.*;
import com.example.util.CrowdUtil;
import com.example.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/24
 */
@Controller
public class ProjectConsumerHandler {
    @Autowired
    private OSSProperties ossProperties;
    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/get/project/detail/{projectId}")
    public String getProjectDetail(@PathVariable("projectId")Integer projectId, Model model){

        ResultEntity<DetailProjectVO> resultEntity = mySQLRemoteService.getDetailProjectVORemote(projectId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            DetailProjectVO detailProjectVO = resultEntity.getData();
            model.addAttribute("detailProjectVO",detailProjectVO);

        }
        return "project-show-detail";
    }
    @RequestMapping("/create/confirm")
    public String saveConfig(HttpSession session,
                             MemberConfirmInfoVO memberConfirmInfoVO,
                             ModelMap modelMap){
        // 1.从Session读取之前存储的ProjectVO对象
        ProjectVO projectVO= (ProjectVO) session.getAttribute(Constant.ATTR_MANE_TEMPLE_PROJECT);
        // 2.判断是否为null
        if (projectVO == null){
            throw new RuntimeException(Constant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }
        // 确认信息设置到projectVO对象中
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);

        Integer memberId = memberLoginVO.getId();
        //  调用远程方法保存projectVO
        ResultEntity<String> saveResultEntity=mySQLRemoteService.saveProjectVORemote(projectVO,memberId);

        String result = saveResultEntity.getResult();
        if (ResultEntity.FAILED.equals(result)){
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE,saveResultEntity.getMessage());
            return "project-confirm";
        }
        // 临时的projectVO对象从session移除
        session.removeAttribute(Constant.ATTR_MANE_TEMPLE_PROJECT);
        return "redirect://localhost:80/project/create/success";

    }
    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session) {
        try {
            // 1.从session中读取之前的ProjectVO对象
            ProjectVO projectVO = (ProjectVO) session.getAttribute(Constant.ATTR_MANE_TEMPLE_PROJECT);
            // 2.判断是否为null
            if (projectVO == null){
                return ResultEntity.failed(Constant.MESSAGE_TEMPLE_PROJECT_MISSING);

            }
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();
            // 判断集合是否有效
            if (returnVOList ==null|| returnVOList.size() == 0){
                // 初始化returnVOList
                returnVOList=new ArrayList<>();
                //初始化projectVO中的returnVOList
                projectVO.setReturnVOList(returnVOList);
            }
            // 存入returnVOList,要有不然returnVOList会无数据为空
            returnVOList.add(returnVO);
            // 把数据有变化的projectVO重新存入Session域，以确保更新数据存入Redis
            session.setAttribute(Constant.ATTR_MANE_TEMPLE_PROJECT,projectVO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }


    // formData.append("returnPicture", file);
    // returnPicture参数名
    // file请求参数的值，也是要上传的文件
    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(

            // 接收用户上传文件
            @RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {

        //图片上传，执行
        ResultEntity<String> uploadReturePicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());
        // 返回上传的结果

        return uploadReturePicResultEntity;
    }

    @RequestMapping("/create/project/information")
    public String saveProjectBasicInFo(
            // 接受除了上传图片外其他普通数据
            ProjectVO projectVO,
            // 接受上传的头图

            @RequestParam("headerPicture") MultipartFile headerPicture,
            // 接收上传的详情图片
            @RequestParam("detailPictureList") List<MultipartFile> detailPictureList,

            // 用来将收集了一部分数据的ProjectVO对象存入Session域
            HttpSession session,
            // 用来失败时后放回上个表单页面时携带信息
            ModelMap modelMap) throws IOException {

        // 1.头图上传
        boolean headPictureEmpty = headerPicture.isEmpty();
        if (headPictureEmpty) {
            // 如果上传失败后返回表单并显示错误消息
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, Constant.MESSAGE_HEADER_PIC_EMPTY);
            return "project-launch";

        }
        //头图上传，执行
        ResultEntity<String> uploadHeaderPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename());
        String result = uploadHeaderPicResultEntity.getResult();
        // 判断头图是否上传成功
        if (ResultEntity.SUCCESS.equals(result)) {
            //从返回的图片中获取图片访问路径
            String headerPicturePath = uploadHeaderPicResultEntity.getData();

            // 存入到projectVO对象中
            projectVO.setHeaderPicturePath(headerPicturePath);
        }

        // 创建一个存放详情图片路径的集合
        List<String> detailPicturePathList = new ArrayList<>();
        if (detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, Constant.MESSAGE_DETAL_PIC_EMPTY);
            return "project-launch";
        }
        // 遍历detailPictureList集合
        for (MultipartFile detailPicture : detailPictureList) {
            if (detailPicture.isEmpty()) {
                modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, Constant.MESSAGE_DETAL_PIC_EMPTY);
                return "project-launch";
            }

            // 执行上传
            ResultEntity<String> detailUploadResultEntity = CrowdUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    detailPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    detailPicture.getOriginalFilename());
            // 检查上传结果
            String detailUploadResult = detailUploadResultEntity.getResult();
            if (ResultEntity.SUCCESS.equals(detailUploadResult)) {
                String detailPicturePath = detailUploadResultEntity.getData();
                detailPicturePathList.add(detailPicturePath);

            } else {
                modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, Constant.MESSAGE_DETAL_PIC_UPLOAD_FAILD);
                return "project-launch";
            }
        }

        // 将存放详情图片的集合存入ProjectVO
        projectVO.setDetailPicturePathList(detailPicturePathList);

        // 存入Session
        session.setAttribute(Constant.ATTR_MANE_TEMPLE_PROJECT, projectVO);
        // 以完整的访问路径前往收集回报的界面
        return "redirect:http://localhost:80/project/return/info/page";
    }
}
