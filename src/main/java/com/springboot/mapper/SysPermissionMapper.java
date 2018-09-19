package com.springboot.mapper;

import com.springboot.entity.SysPermission;
import org.apache.ibatis.annotations.*;


import java.util.Date;
import java.util.List;

/**
 * @author Administrator on 2018/9/18.
 */
@Mapper
public interface SysPermissionMapper {

    @Select("select * from sys_permission where perm_id = #{permId}")
    @Results(id = "SysPermMap", value = {
            @Result(id = true, column = "perm_id", property = "permId", javaType = Integer.class),
            @Result(column = "perm_name", property = "permName", javaType = String.class),
            @Result(column = "perm_caption", property = "permCaption", javaType = String.class),
            @Result(column = "perm_href", property = "permHref", javaType = String.class),
            @Result(column = "perm_parent_id", property = "permParentId", javaType = Integer.class),
            @Result(column = "is_button", property = "isButton", javaType = Boolean.class),
            @Result(column = "create_time", property = "createTime", javaType = Date.class),
            @Result(column = "create_by", property = "createBy", javaType = Integer.class),
            @Result(column = "update_time", property = "updateTime", javaType = Date.class),
            @Result(column = "update_by", property = "updateBy", javaType = Integer.class),
            @Result(column = "usable", property = "usable", javaType = Boolean.class),
            @Result(column = "remarks", property = "remarks", javaType = String.class),
            @Result(column = "perm_parent_id",property = "permParent",one = @One(select="com.springboot.mapper.SysPermissionMapper.findSysPermByPermId"))
        })
    public List<SysPermission> findSysPermByPermId(@Param("permId") Integer permId);
}
