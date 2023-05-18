package com.lzj.admin.config.security;

import com.lzj.admin.config.ClassPathIdsLoader;
import com.lzj.admin.filters.CaptchaCodeFilter;
import com.lzj.admin.pojo.User;
import com.lzj.admin.service.IRbacService;
import com.lzj.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 放行静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/images/**" ,
                "/error/**","/js/**","/lib/**");
    }
    @Resource
    private DataSource dataSource;
    @Autowired
    private JxcAuthenticationFailedHandler jxcAuthenticationFailedHandler;
    @Autowired
    private JxcAuthenticationSuccessHandler jxcAuthenticationSuccessHandler;
    @Resource
    private JxcLogoutSuccessHandler jxcLogoutSuccessHandler;
    @Resource
    private CaptchaCodeFilter captchaCodeFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                addFilterBefore(captchaCodeFilter, UsernamePasswordAuthenticationFilter.class).
                headers().frameOptions().disable()
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .failureHandler(jxcAuthenticationFailedHandler)
                .successHandler(jxcAuthenticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(jxcLogoutSuccessHandler)
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(7*24*60*60)
                .tokenRepository(persistentTokenRepository())
                .and()
                .authorizeRequests().antMatchers("/index","/login","/image").permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
    @Resource
    private IUserService iUserService;

    @Resource
    private IRbacService rbacService;
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                User userDerails= iUserService.findUserByUserName(userName);
                /**
                 * 1.查询用户分配的juese
                 * 2.根据用户扮演的角色查询角色拥有的权限记录
                 */
                List<String> roleNames=rbacService.findRolesByUserName(userName);
                List<String> authorities=rbacService.findAuthoritiesByRoleName(roleNames);
                roleNames=roleNames.stream().map(role-> "ROLE_"+role).collect(Collectors.toList());
                authorities.addAll(roleNames);
                userDerails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",authorities)));
                return userDerails;
            }
        };
    }
    @Bean
    public PasswordEncoder encoder(){
       return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
    }

    /**
     *加载ClassPathLdsLoader
     */
    @Bean
    @ConditionalOnMissingBean(ClassPathIdsLoader.class)
   public ClassPathIdsLoader classPathIdsLoader(){
       return new ClassPathIdsLoader();
   }
}
