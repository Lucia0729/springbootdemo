package com.springboot.service.impl;

import com.springboot.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator on 2018/9/18.
 */
@Service
public class SysPermissionServiceImpl {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;
}
