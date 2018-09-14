package com.springboot.service.impl;

import com.springboot.entity.Employee;
import com.springboot.mapper.EmployeeMapper;
import com.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator on 2018/9/10.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee findEmployeeByUserName(String username) {
        return employeeMapper.findEmployeeByUserName(username);
    }
}
