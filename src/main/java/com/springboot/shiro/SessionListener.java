package com.springboot.shiro;

import org.apache.shiro.session.Session;

/**
 * @author Administrator on 2018/9/19.
 */
public class SessionListener implements org.apache.shiro.session.SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("会话开始：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期：" + session.getId());
    }
}
