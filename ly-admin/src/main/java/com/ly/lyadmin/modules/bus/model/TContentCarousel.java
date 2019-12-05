package com.ly.lyadmin.modules.bus.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 内容轮播图
 * @Email: lmm_work@163.com
 * @Author: SLIGHTLEE
 * @Date: 2019/12/5 9:57 下午
 */
public class TContentCarousel implements Serializable {

    private static final long serialVersionUID = -583883152997524836L;

    private String id;

    private String picUrl;

    private String contentId;

    private String contentTitle;

    private String ptCode;

    private Integer orderNum;

    private Integer status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TContentCarousel{" +
                "id='" + id + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", contentId='" + contentId + '\'' +
                ", contentTitle='" + contentTitle + '\'' +
                ", ptCode='" + ptCode + '\'' +
                ", orderNum=" + orderNum +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
