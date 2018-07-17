package com.example.demo.shiro1.realm;

import com.example.demo.constant.CommonCode;
import com.example.demo.exception.SpringBootException;
import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.ResourceService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 16:09 2018/5/7
 * @Modified By:
 */
@Component
public class MyRealm2 extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    ResourceService resourceService;

    @Override
    public String getName(){
        return "MyRealm2";
    }
    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof UsernamePasswordToken;
    }
    //Authorization授权，及权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer acount = Integer.parseInt(String.valueOf(principalCollection.getPrimaryPrincipal()));
        User user = userService.selectByAccount(acount);
        Integer roleId = user.getRoleId();
        Role role = roleService.selectByPrimaryKey(roleId);

        String pers = role.getResourceId();
        String[] ids = pers.split(",");
        List<String> perIds = Arrays.asList(ids);
        List<Integer> idss = perIds.stream().map(e->{
            return Integer.parseInt(e);
        }).collect(Collectors.toList());
        List<Permission> perList = resourceService.getPerList(idss);
        Set<String> roleNames = new HashSet<String>();
        roleNames.add(role.getRoleName());
        Set<String> perss = perList.stream().map(e->{
            return e.getPermission();
        }).collect(Collectors.toSet());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNames);
        authorizationInfo.setStringPermissions(perss);
        return authorizationInfo;
    }

    //Authentication身份认证/登录，验证用户是不是拥有相应的身份
    //如果验证成功，将返回AuthenticationInfo验证信息；此信息中包含了身份及凭证；如果验证失败将抛出相应的AuthenticationException实现。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        String accout = (String) authenticationToken.getPrincipal();//得到用户名
       // String passWord = new String((char[])authenticationToken.getCredentials()) ;//得到密码
     /*   UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken
        String name =  token.getUsername()*/
        User user = userService.selectByAccount(Integer.parseInt(accout));
        if(user == null){
           throw new SpringBootException(CommonCode.PARAMERROR);
        }
        String name = user.getAccount().toString();
        String pass = user.getPassword();

        String salt = accout+"10";
        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(
                name,
                pass,
                ByteSource.Util.bytes(salt),
                getName());
        //會默認自動去跟token裏的做比較

        System.out.println(ai.getCredentials());

        return ai;
    }
}
