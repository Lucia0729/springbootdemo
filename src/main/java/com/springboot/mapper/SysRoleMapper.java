package com.springboot.mapper;

import com.springboot.entity.SysRole;
import org.apache.ibatis.annotations.*;

import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator on 2018/9/18.
 */
@Mapper
public interface SysRoleMapper {

    @Select("select * from sys_role where role_id = #{roleId}")
    @Results(id = "SysRoleMap", value = {
            @Result(id = true, column = "role_id", property = "roleId", javaType = Integer.class),
            @Result(column = "role_name", property = "roleName", javaType = String.class),
            @Result(column = "create_time", property = "createTime", javaType = Date.class),
            @Result(column = "create_by", property = "createBy", javaType = Integer.class),
            @Result(column = "update_time", property = "updateTime", javaType = Date.class),
            @Result(column = "update_by", property = "updateBy", javaType = Integer.class),
            @Result(column = "usable", property = "usable", javaType = Boolean.class),
            @Result(column = "remarks", property = "remarks", javaType = String.class),
            @Result(column = "perm_id",property = "permList", many=@Many(select="com.springboot.mapper.SysPermissionMapper.findSysPermByRoleId",fetchType = FetchType.LAZY))

    })
    public List<SysRole> findSysPermByRoleId(Integer roleId);
}
