package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 10:54
 */
@Table(name = "t_mp_indexpush")
public class IndexPush implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "appName")
    private String appName;
    @Column(name = "msg")
    private String msg;
    @Column(name = "imgUrl")
    private String imgUrl;
    @Column(name = "actionPath")
    private String actionPath;
    @Column(name = "creatDate")
    private String creatDate;
    @Column(name = "appId")
    private String appId;
    @Column(name = "title")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getActionPath() {
        return actionPath;
    }

    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
