package com.springboot.controller;


import com.springboot.entity.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;


/**
 * @author Administrator on 2018/9/14.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee emp){
        Subject currentUser =SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(emp.getEmpLoginName(), emp.getEmpPassword());
            try {
                currentUser.login(token);
                Session session = currentUser.getSession();
                session.setAttribute("current", currentUser);
                return "redirect:/index.html";
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
        }
        return "redirect:/login.html";
    }
}
