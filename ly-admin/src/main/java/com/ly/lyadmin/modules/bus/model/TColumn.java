package com.ly.lyadmin.modules.bus.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/14 10:00 下午
 * @Version V1.0
 */
@TableName("t_column")
public class TColumn implements Serializable {

    private static final long serialVersionUID = 2198391283093449820L;

    private String id;

    private String columnName;

    private String parentId;

    private Integer status;

    private String ptCode;

    private String linkurl;

    private Integer orderNum;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    @TableField(exist = false)
    private List<?> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TColumn{" +
                "id='" + id + '\'' +
                ", columnName='" + columnName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", status=" + status +
                ", ptCode='" + ptCode + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", orderNum=" + orderNum +
                ", parentName='" + parentName + '\'' +
                ", list=" + list +
                '}';
    }
}
