package com.embracesource.traffic.base.config;

import com.embracesource.traffic.admin.service.impl.HrService;
import com.embracesource.traffic.base.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 08:52
 * @description：权限配置管理
 * @version:
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //全局
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 实现了UserDetailsService接口
     */
    @Autowired
    private HrService hrService;

    /**
     * 权限过滤器（当前url所需要的访问权限）
     */
    @Autowired
    MyFilterInvocationSecurityMetadataSource filterMetadataSource;
    /**
     * 权限决策器
     */
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    /**
     * 自定义错误(403)返回数据
     */
    @Autowired
    private MyAccessDeniedHandler deniedHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    /**
     * 登录成功处理器
     */
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    /**
     * 退出处理器
     */
    @Autowired
    MyLogoutSuccessHandler MyLogoutSuccessHandler;
    /**
     * 会话信息过期策略
     */
    @Autowired
    CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;


    /**
     * @Description: 配置userDetails的数据源，密码加密格式
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * @Description: 配置放行的资源
     **/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login_p", "/**")
                // 给 swagger 放行；不需要权限能访问的资源
                .antMatchers("/doc.html", "/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security", "/time/**")
                .antMatchers("/error");
    }

    /**
     * @Description: HttpSecurity包含了原数据（主要是url）
     * 通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
     * 此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
     * 如果不匹配，返回 MyAccessDeniedHandler
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(filterMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                //.usernameParameter("username").passwordParameter("password")
                .failureHandler(myAuthenticationFailureHandler)
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(MyLogoutSuccessHandler)
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .and()
                .sessionManagement().maximumSessions(1).
                expiredSessionStrategy(customizeSessionInformationExpiredStrategy);
    }
}