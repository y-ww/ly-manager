package com.ly.lyadmin.modules.bus.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/22 12:29 上午
 * @Version V1.0
 */
public class TInfo implements Serializable {

    private static final long serialVersionUID = 2556286541773861083L;

    private String id;//ID

    private String title; //标题

    private String summary;//摘要

    private String content;//内容

    private String minPicAddress;//缩略图地址

    private String picAddress;//图片地址

    private String picName;//图片名称

    private String videoAddress;//视频地址

    private String colid;//栏目ID

    private String type;//信息类型（暂时不用）

    private String platform;//平台ID

    private Integer isdelete;//是否删除 1 使用 0 删除

    private Integer isFbtype;//是否发布 1 发布 0 未发布

    private Integer orderNum;//排序字段

    private String integral;//积分

    private Integer amountRead;//阅读量

    private Long personid;//发布人编号

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMinPicAddress() {
        return minPicAddress;
    }

    public void setMinPicAddress(String minPicAddress) {
        this.minPicAddress = minPicAddress;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public String getColid() {
        return colid;
    }

    public void setColid(String colid) {
        this.colid = colid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getIsFbtype() {
        return isFbtype;
    }

    public void setIsFbtype(Integer isFbtype) {
        this.isFbtype = isFbtype;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public Integer getAmountRead() {
        return amountRead;
    }

    public void setAmountRead(Integer amountRead) {
        this.amountRead = amountRead;
    }

    public Long getPersonid() {
        return personid;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", minPicAddress='" + minPicAddress + '\'' +
                ", picAddress='" + picAddress + '\'' +
                ", picName='" + picName + '\'' +
                ", videoAddress='" + videoAddress + '\'' +
                ", colid='" + colid + '\'' +
                ", type='" + type + '\'' +
                ", platform='" + platform + '\'' +
                ", isdelete=" + isdelete +
                ", isFbtype=" + isFbtype +
                ", orderNum=" + orderNum +
                ", integral=" + integral +
                ", amountRead=" + amountRead +
                ", personid=" + personid +
                ", createTime=" + createTime +
                '}';
    }
}
