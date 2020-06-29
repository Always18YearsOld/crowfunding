
import com.example.entity.Admin;
import com.example.entity.Role;
import com.example.mapper.AdminMapper;

import com.example.mapper.RoleMapper;
import com.example.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author Administrator
 * @Date 2020/5/6
 */
// 指定 Spring 给 Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)
// 加载 Spring 配置文件的注解
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdSpringTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void testRoleSave(){
        for (int i=0;i<256;i++){
            roleMapper.insert(new Role(null,"role"+i));
        }
    }
    @Test
    public void testadd(){
        for (int i=0;i<256;i++){
            adminMapper.insert(new Admin(null,"loginAcct"+i,"userpswd"+i,"userName"+i,"email"+i,"createTime"+i));
        }
    }
    @Test
    public void testTX(){
        Admin admin = new Admin(null,"tx","123","123","123","123");
        adminService.saveAdmin(admin);

    }
    @Test
    public void test(){


        Admin admin = new Admin(12, "a", "123", "t", "tom@qq.com", "111");

        int insert = adminMapper.insert(admin);
        System.out.println(insert);

    }
    @Test
    public void testDataSource() throws SQLException {
        // 1.通过数据源对象获取数据源连接
        Connection connection = dataSource.getConnection();
        // 2.打印数据库连接
        System.out.println(connection);
    }
    @Test
    public void testLog()  {
        Admin admin = new Admin(6, "a", "123", "t", "tom@qq.com", "111");
        // 获取日志记录对象
        Logger logger = LoggerFactory.getLogger(CrowdSpringTest.class);
        int insert = adminMapper.insert(admin);
        // 按照 Debug 级别打印日志
        logger.debug(String.valueOf(insert));
    }
}