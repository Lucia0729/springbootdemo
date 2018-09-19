package com.springboot.entityviewvo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Administrator on 2018/9/18.
 */
@Getter
@Setter
public class SysMenuVO {
    private Integer permId;
    private String permCaption;
    private String permHref;
    private List<SysMenuVO> menuVOList;
}
