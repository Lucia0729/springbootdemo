package com.springboot.entity;

/**
 * @author Administrator on 2018/9/18.
 */

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 角色实体
 */
@Getter
@Setter
public class SysRole extends BaseEntity {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    @NotNull
    @Length(max = 20, message = "菜单名称不能超过20个字")
    private String roleName;

//    private List<Integer> roleDistList;
//
//    private List<Integer> roleOrgList;

    /**
     * 角色权限
     */
    private Set<SysPermission> permList;



    public SysRole(Integer roleId, String roleName, Date createTime, Integer createBy, Date updateTime, Integer updateBy,
                   Boolean usable, String remarks) {
        super(createTime, createBy, updateTime, updateBy, usable, remarks);
        this.roleId = roleId;
        this.roleName = roleName;

    }

    public SysRole() {
        super();
    }


}
