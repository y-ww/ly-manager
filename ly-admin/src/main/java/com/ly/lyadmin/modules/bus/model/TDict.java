package com.ly.lyadmin.modules.bus.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:  字典表
 * @Author SLIGHTLEE
 * @Date 2019/11/21 1:43 上午
 * @Version V1.0
 */
public class TDict implements Serializable {

    private static final long serialVersionUID = 3766037909413837385L;

    private String id;

    private String keyname;

    private String keyvalue;

    private String keycode;

    private Integer keydesc;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public String getKeycode() {
        return keycode;
    }

    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }

    public Integer getKeydesc() {
        return keydesc;
    }

    public void setKeydesc(Integer keydesc) {
        this.keydesc = keydesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TDict{" +
                "id='" + id + '\'' +
                ", keyname='" + keyname + '\'' +
                ", keyvalue='" + keyvalue + '\'' +
                ", keycode='" + keycode + '\'' +
                ", keydesc=" + keydesc +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
