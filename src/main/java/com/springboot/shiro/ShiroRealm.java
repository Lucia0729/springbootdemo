package com.springboot.shiro;

import com.springboot.entity.Employee;
import com.springboot.entity.SysPermission;
import com.springboot.entity.SysRole;
import com.springboot.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.util.Cache;

import java.security.Principal;
import java.util.List;
import java.util.Set;


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

        Principal principal = (Principal) getAvailablePrincipal(principals);

        Employee emp = employeeService.findEmployeeByUserName(principal.getName());
        if (emp == null) {
            return null;
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("empId", emp.getEmpId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roles = emp.getRoleList();
        for (SysRole role : roles) {
            info.addRole(role.getRoleName());
            if(("admin").equals(role.getRoleName())){
                Set<SysPermission> permissions = role.getPermList();
                for (SysPermission permission : permissions) {
                    info.addStringPermission(permission.getPermName());
                }
                break;
            }
            Set<SysPermission> permissions = role.getPermList();
            for (SysPermission permission : permissions) {
                info.addStringPermission(permission.getPermName());
            }
        }
        info.addRole("user");
         return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken)token;
        String username = utoken.getUsername();
        Employee emp = employeeService.findEmployeeByUserName(username);
        if(emp == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(Boolean.FALSE.equals(emp.getUsable())) {
            throw new LockedAccountException(); //帐号锁定
        }
        return new SimpleAuthenticationInfo(emp.getEmpLoginName(), emp.getEmpPassword(), ByteSource.Util.bytes(SALT),username);
    }
}
