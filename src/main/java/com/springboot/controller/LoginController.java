package com.springboot.controller;


import com.springboot.entity.Employee;
import com.springboot.entity.SysPermission;
import com.springboot.entityviewvo.SysMenuVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Administrator on 2018/9/14.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequiresPermissions("")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap map){
        List<SysPermission> perms = new ArrayList<SysPermission>();
        SysPermission perm = new SysPermission();
        perm.setPermId(1);
        perm.setPermCaption("系统管理");
        perms.add(perm);
        SysPermission perm1 = new SysPermission();
        perm1.setPermId(2);
        perm1.setPermCaption("用户管理");
        perm1.setPermHref("sys/user");
        perm1.setPermParent(perm);
        perms.add(perm1);
        SysPermission perm2 = new SysPermission();
        perm2.setPermId(3);
        perm2.setPermCaption("角色管理");
        perm2.setPermHref("sys/role");
        perm2.setPermParent(perm);
        perms.add(perm2);
        SysPermission perm3 = new SysPermission();
        perm3.setPermId(4);
        perm3.setPermCaption("账户管理");
        perms.add(perm3);
        SysPermission perm4 = new SysPermission();
        perm4.setPermId(5);
        perm4.setPermCaption("账户开户");
        perm4.setPermHref("account/role");
        perm4.setPermParent(perm3);
        perms.add(perm4);
        List<SysMenuVO> menus = changePermsToMenus(perms);
        map.addAttribute("menus",menus);
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee emp ,ModelMap map){
        Subject currentUser =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(emp.getEmpLoginName(), emp.getEmpPassword());
        try {
            if(!currentUser.isAuthenticated()) {
                currentUser.login(token);
            }
            Session session = currentUser.getSession();
            session.setAttribute("current", currentUser);
            return "redirect:/index";
        } catch (UnknownAccountException uae) {
            //username wasn't in the system, show them an error message?
        } catch (IncorrectCredentialsException ice) {
            //password didn't match, try again?
        } catch (LockedAccountException lae) {
            //account for that username is locked - can't login.  Show them a message?
        } catch (AuthenticationException ae) {
            //unexpected condition - error?
        } catch (Exception e) {

        }
        return "redirect:/login";
    }
    private List<SysMenuVO> changePermsToMenus(List<SysPermission> perms ){
        List<SysMenuVO> sysMenuVOS = new ArrayList<>();
        for(SysPermission perm : perms){
            if(perm.getPermParent()==null) {
                SysMenuVO sysMenuVO = new SysMenuVO();
                sysMenuVO.setPermId(perm.getPermId());
                sysMenuVO.setPermCaption(perm.getPermCaption());
                sysMenuVO.setPermHref(perm.getPermHref());
                List<SysMenuVO> sysMenuS = new ArrayList<>();
                for (SysPermission per : perms) {
                    SysMenuVO sysMenu = new SysMenuVO();
                    sysMenu.setPermId(per.getPermId());
                    sysMenu.setPermCaption(per.getPermCaption());
                    sysMenu.setPermHref(per.getPermHref());
                    if (null!=per.getPermParent() && per.getPermParent().getPermId().equals(perm.getPermId())) {
                        sysMenuS.add(sysMenu);
                    }
                }
                sysMenuVO.setMenuVOList(sysMenuS);
                sysMenuVOS.add(sysMenuVO);
            }
     }
     return sysMenuVOS;
 }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public ModelAndView login(Employee emp ){
//        ModelAndView modelAndView = new ModelAndView("index");
//            Subject currentUser =SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(emp.getEmpLoginName(), emp.getEmpPassword());
//            try {
//                if(!currentUser.isAuthenticated()) {
//                    currentUser.login(token);
//                }
//                Session session = currentUser.getSession();
//                session.setAttribute("current", currentUser);
//                List<SysPermission> perms = new ArrayList<SysPermission>();
//                SysPermission perm = new SysPermission();
//                perm.setPermId(1);
//                perm.setPermCaption("系统管理");
//                perms.add(perm);
//                SysPermission perm1 = new SysPermission();
//                perm1.setPermId(2);
//                perm1.setPermCaption("用户管理");
//                perm1.setPermHref("sys/user");
//                perm1.setPermParent(perm);
//                perms.add(perm1);
//                SysPermission perm2 = new SysPermission();
//                perm2.setPermId(2);
//                perm2.setPermCaption("角色管理");
//                perm2.setPermHref("sys/role");
//                perm2.setPermParent(perm);
//                perms.add(perm2);
//                SysPermission perm3 = new SysPermission();
//                perm3.setPermId(3);
//                perm3.setPermCaption("账户管理");
//                perms.add(perm3);
//                SysPermission perm4 = new SysPermission();
//                perm4.setPermId(4);
//                perm4.setPermCaption("账户开户");
//                perm4.setPermHref("account/role");
//                perm4.setPermParent(perm3);
//                perms.add(perm4);
//                modelAndView.addObject("perm",perm);
//                return modelAndView;
//            } catch (UnknownAccountException uae) {
//                //username wasn't in the system, show them an error message?
//            } catch (IncorrectCredentialsException ice) {
//                //password didn't match, try again?
//            } catch (LockedAccountException lae) {
//                //account for that username is locked - can't login.  Show them a message?
//            } catch (AuthenticationException ae) {
//                //unexpected condition - error?
//            } catch (Exception e) {
//
//            }
//        return null;
//    }
}
