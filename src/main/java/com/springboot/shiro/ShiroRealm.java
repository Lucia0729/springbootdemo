package com.springboot.shiro;

import com.springboot.entity.Employee;
import com.springboot.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.util.Cache;



/**
 * @author Administrator on 2018/9/14.
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    public static final String SALT = "lucia";

    @Autowired
    private EmployeeService employeeService;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken)token;
        String username = utoken.getUsername();
        Employee emp = employeeService.findEmployeeByUserName(username);
        if(emp!=null){
            return new SimpleAuthenticationInfo(emp.getEmpLoginName(), emp.getEmpPassword(), ByteSource.Util.bytes(SALT),username);
        }
        return null;
    }
}
