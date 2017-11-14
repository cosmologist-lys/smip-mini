package com.smip.entity.sys;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by kepler@gmail.com on 2017/11/9.
 */
@Entity
public class Secuser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SECUSER_ID_SEQ" )
    @SequenceGenerator(sequenceName = "SECUSER_ID_SEQ", allocationSize = 1, name = "SECUSER_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name="username")
    private String userName;
    @NotNull
    @Column(name="password")
    private String passWord;
    @NotNull
    private String name;
    @NotNull
    private String deptcode;
    private Integer districtId;
    @NotNull
    private String roles;
    @NotNull
    @Column(name="stopflag")
    private String stopFlag;
    private String shortcut;
    @Transient
    private String token;

    @Override
    public String toString() {
        return "Secuser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", name='" + name + '\'' +
                ", deptcode='" + deptcode + '\'' +
                ", districtId=" + districtId +
                ", roles='" + roles + '\'' +
                ", stopFlag='" + stopFlag + '\'' +
                ", shortcut='" + shortcut + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
}
