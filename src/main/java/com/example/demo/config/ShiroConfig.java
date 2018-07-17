package com.example.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.demo.shiro1.realm.MyRealm2;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 16:59 2018/5/17
 * @Modified By:
 */
@Configuration
public class ShiroConfig {


/*  @Bean("retryLimitHashedCredentialsMatcher")
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");
        retryLimitHashedCredentialsMatcher.setHashIterations(2);
        retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return retryLimitHashedCredentialsMatcher;
    }*/

    @Bean(name="hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//设置加密算法
        hashedCredentialsMatcher.setHashIterations(1);  //设置加密次数
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean(name="myRealm")
    public MyRealm2 myShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm2 myRealm = new MyRealm2();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);//设置加密器后，系统会自动给token里的密码加密
        return myRealm;
    }

    @Bean
    public EhCacheManager cacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile("classpath:spring/ehcache.xml");
        return manager;
    }

    /*安全管理器securityManager包含几样东西：
    * 1.缓存技术：缓存管理 （EhCacheManager）
    * 2.负责获取处理数据的 （Realm）
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") MyRealm2 myRealm2){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(myRealm2);
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultSecurityManager defaultSecurityManager
                                                         /*@Qualifier("")*/){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultSecurityManager);
        factoryBean.setLoginUrl("/start"); //没有通过验证的会跳到该页面  如果不设置默认会自动寻找工程根目录下的"/login.jsp"页面
        factoryBean.setSuccessUrl("/toSuccess"); //登陆成功转到页面
        factoryBean.setUnauthorizedUrl("/error/403");  //登录失败转到的页面（没有权限的页面）

        Map<String,Filter> filters = new HashMap<>();
/*        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/start");
        filters.put("logout",logoutFilter);*/
        filters.put("user",new UserFilter());
        factoryBean.setFilters(filters );

        Map<String,String> filterChainDefinitions = new LinkedHashMap<>();
        //按顺序判断，anon和authc加入的顺序反过来可能会出错
        //anon的路径可以匿名访问，即未登录也可以访问，而authc是必须要认证通过才可以访问
        filterChainDefinitions.put("/static/*","anon");
        filterChainDefinitions.put("/public/*","anon");
        filterChainDefinitions.put("/start","anon,user");
        filterChainDefinitions.put("/*","authc");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
        //filterChainDefinitions过滤器链定义
        return factoryBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean (){
        //支持web Filter 排序的使用
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifeCycle","true");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
        //必须要有一个这样的实例，用来管理Spring容器当中的shiro常见的对象
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
        //启用shiro注解
    }

}
