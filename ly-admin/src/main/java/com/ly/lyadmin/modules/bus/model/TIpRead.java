package com.ly.lyadmin.modules.bus.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/12/23 9:42 上午
 * @Version V1.0
 */
public class TIpRead implements Serializable {

    private static final long serialVersionUID = -2851524849897627881L;

    private Integer id;

    private String ip;

    /**
     * 阅读量
     *
     */
    private Integer readCount;

    /**
     *
     * 文章id
     */
    private String contentId;


    /**
     * 平台id
     */
    private String platform;

    /**
     * 访问时间
     *
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date visitTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return "TIpRead{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", readCount=" + readCount +
                ", contentId='" + contentId + '\'' +
                ", platform='" + platform + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
