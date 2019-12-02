package com.ly.lyadmin.modules.bus.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 轮播图
 * @Author SLIGHTLEE
 * @Date 2019/11/22 1:15 上午
 * @Version V1.0
 */
public class TCarousel implements Serializable {

    private static final long serialVersionUID = 6612525620506984520L;

    private String id;

    private String picUrl;

    private String contentId;

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
        return "TCarousel{" +
                "id='" + id + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", contentId='" + contentId + '\'' +
                ", ptCode='" + ptCode + '\'' +
                ", orderNum=" + orderNum +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
