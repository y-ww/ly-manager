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

    private String id;

    private String title;

    private String summary;

    private String content;

    private String minPicAddress;

    private String picAddress;

    private String picName;

    private String videoAddress;

    private String colid;

    private String type;

    private String platform;

    private Integer isdelete;

    private Integer isFbtype;

    private Integer orderNum;

    private Integer personid;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
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
                ", personid=" + personid +
                ", createTime=" + createTime +
                '}';
    }
}
