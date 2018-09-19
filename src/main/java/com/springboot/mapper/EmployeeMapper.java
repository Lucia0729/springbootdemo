package com.springboot.mapper;

import com.springboot.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.Date;

/**
 * @author Administrator on 2018/9/10.
 */
@Mapper
public interface EmployeeMapper {


    @Select("select * from employee where emp_login_name = #{username}")
    @Results(id = "employeeMap", value = {
            @Result(id = true, column = "emp_id", property = "empId", javaType = Integer.class),
            @Result(column = "emp_number", property = "empNumber", javaType = String.class),
            @Result(column = "emp_name", property = "empName", javaType = String.class),
            @Result(column = "emp_org_id", property = "empOrgId", javaType = Integer.class),
            @Result(column = "emp_district_id", property = "empDistrictId", javaType = Integer.class),
            @Result(column = "emp_login_name", property = "empLoginName", javaType = String.class),
            @Result(column = "emp_password", property = "empPassword", javaType = String.class),
            @Result(column = "emp_email", property = "empEmail", javaType = String.class),
            @Result(column = "emp_phone", property = "empPhone", javaType = String.class),
            @Result(column = "emp_mobile", property = "empMobile", javaType = String.class),
            @Result(column = "emp_address", property = "empAddress", javaType = String.class),
            @Result(column = "emp_type", property = "empType", javaType = String.class),
            @Result(column = "emp_management_dist_id", property = "empManagementDistId", javaType = String.class),
            @Result(column = "emp_login_ip", property = "empLoginIp", javaType = String.class),
            @Result(column = "emp_login_date", property = "empLoginDate", javaType = Date.class),
            @Result(column = "emp_login_flag", property = "empLoginFlag", javaType = Boolean.class),
            @Result(column = "create_time", property = "createTime", javaType = Date.class),
            @Result(column = "create_by", property = "createBy", javaType = Integer.class),
            @Result(column = "update_time", property = "updateTime", javaType = Date.class),
            @Result(column = "update_by", property = "updateBy", javaType = Integer.class),
            @Result(column = "usable", property = "usable", javaType = Boolean.class),
            @Result(column = "remarks", property = "remarks", javaType = String.class),
            @Result(column = "role_id",property = "roleList", many=@Many(select="com.springboot.mapper.SysRoleMapper.findSysRoleByEmpId",fetchType = FetchType.LAZY))

    })
    public Employee findEmployeeByUserName(@Param("username") String username);
}
