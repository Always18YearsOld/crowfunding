import com.example.entity.Admin;
import com.example.util.CrowdUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author Administrator
 * @Date 2020/5/8
 */
public class StringTest {
    @Test
    public void testMd5(){
        String source="123123";
        String encode = CrowdUtil.md5(source);
        System.out.println(encode);
    }//4297F44B13955235245B2497399D7A93
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    public void jie() {
        String hh = passwordEncoder.encode("123123");
        System.out.println(hh);
    }
}
