package com.ly.lyadmin.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 菜单信息
 *
 * @Author: SLIGHTLEE
 * @Date: 2019/10/15 11:00 下午
 *
 */
@Setter
@Getter
@ToString
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -7887193109449729797L;

    /**
     * 菜单ID
     */
    @TableId
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    private String name;

    private String url;

    private String perms;

    private Integer type;

    private Integer menuType;

    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * ztree属性
     */
    @TableField(exist = false)
    private Boolean open;

    @TableField(exist = false)
    private List<?> list;


}
