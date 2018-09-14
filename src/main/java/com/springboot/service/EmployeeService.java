package com.springboot.service;

import com.springboot.entity.Employee;




/**
 * @author Administrator on 2018/9/10.
 */

public interface EmployeeService {
    public Employee findEmployeeByUserName(String username);
}
