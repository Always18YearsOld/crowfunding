package com.example.mvc.config;

import com.example.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Administrator
 * @Date 2020/5/15
 */
//表示当前类是一个配置类
@Configuration
// 启用web环境下权限控制
@EnableWebSecurity
// 启用全局方法权限控制功能，设置prePostEnabled = true，保证@PreAuthorize，@PostAuthority,@PreFilter,@PostFilter生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
//    @Bean
//    public BCryptPasswordEncoder getPasswordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        //临时使用内存模式测试代码
        //builder.inMemoryAuthentication().withUser("tom").password("123123").roles("ADMIN");

        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                     ;

    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()// 对请求进行授权
                .antMatchers("/admin/to/login/page.html")// 登录页面设置
                .permitAll()// 无条件访问
                .antMatchers("/css/**")                    // 对静态资源设置
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/crowd/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/WEB-INF/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                .antMatchers("/admin/get/page.html")
               // .hasRole("经理")
                .access("hasRole('经理') or hasAuthority('user:get')")
                .anyRequest()                                             //其他任意请求
                .authenticated()//认证后访问
                .and()
                .exceptionHandling() //自定义异常映射，以免在filter阶段抛出403异常
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("exception",new Exception(Constant.MESSAGE_ACCESS_DENIED));
                        httpServletRequest.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(httpServletRequest,httpServletResponse);
                    }
                })
                .and()
                .csrf() // 跨站请求伪造功能
                .disable() //取消
                .formLogin() // 开启表单登录功能
                .loginPage("/admin/to/login/page.html")               // 指定登录页面
                .loginProcessingUrl("/security/do/login.html")        // 处理登录请求的地址
                .defaultSuccessUrl("/admin/to/main/page.html")        // 登录成功后跳转的地址
                .usernameParameter("loginAcct")                         // 账号的请求参数名
                .passwordParameter("userPswd")                          // 密码的请求参数名
                .and()
                .logout()
                .logoutUrl("/security/do/logout.html")               // 处理退出请求的地址
                .logoutSuccessUrl("/admin/to/login/page.html")       // 登录退出后跳转的地址
                ;

    }
}
