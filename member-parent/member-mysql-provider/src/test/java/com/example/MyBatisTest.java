package com.example;

import com.example.entity.po.MemberPO;
import com.example.entity.vo.DetailProjectVO;
import com.example.entity.vo.PortalProjectVO;
import com.example.entity.vo.PortalTypeVO;
import com.example.mapper.MemberPOMapper;
import com.example.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberPOMapper memberPOMapper;
    @Autowired
    private ProjectPOMapper projectPOMapper;

    private Logger logger= LoggerFactory.getLogger(MyBatisTest.class);

    @Test
    public void testLoadDetail(){
        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(1);
        logger.info(detailProjectVO.getProjectId()+"");
        logger.info(detailProjectVO.toString());
    }


    @Test
    public void testLoadTyeData(){
        List<PortalTypeVO> portalTypeVOList = projectPOMapper.selectPortalTypeVOList();
        for (PortalTypeVO portalTypeVO : portalTypeVOList) {
            String name = portalTypeVO.getName();
            String remark = portalTypeVO.getRemark();
            logger.info(name,remark);
            List<PortalProjectVO> portalProjectVOList = portalTypeVO.getPortalProjectVOList();
            for (PortalProjectVO portalProjectVO : portalProjectVOList) {
                if (portalProjectVO == null){
                    continue;
                }
                logger.info(portalProjectVO.toString());
            }
        }

    }
    @Test
    public void testCon() throws SQLException {
        Connection connection = dataSource.getConnection();
        logger.info(connection.toString());

    }
    @Test
    public void testMapper(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String sourse ="123123";
        String encode = passwordEncoder.encode(sourse);
        MemberPO record = new MemberPO(null, "ai", encode, "爱", "1@qq.com", 1, 1, "1", "1", 1);
        memberPOMapper.insert(record);
    }
    @Test
    public void testmima(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String sourse ="123123";
        String encode = passwordEncoder.encode(sourse);
        System.out.println(encode);
    }
}
