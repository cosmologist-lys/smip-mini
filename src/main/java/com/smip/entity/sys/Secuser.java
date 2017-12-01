package com.smip.entity.sys;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by kepler@gmail.com on 2017/11/9.
 */
@ApiModel(value="操作员对象",description="操作员")
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

    public Secuser setToken(String token) {
        this.token = token;return this;
    }

    public Integer getId() {
        return id;
    }

    public Secuser setId(Integer id) {
        this.id = id;return this;
    }

    public String getUserName() {
        return userName;
    }

    public Secuser setUserName(String userName) {
        this.userName = userName;return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public Secuser setPassWord(String passWord) {
        this.passWord = passWord;return this;
    }

    public String getName() {
        return name;
    }

    public Secuser setName(String name) {
        this.name = name;return this;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public Secuser setDeptcode(String deptcode) {
        this.deptcode = deptcode;return this;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public Secuser setDistrictId(Integer districtId) {
        this.districtId = districtId;return this;
    }

    public String getRoles() {
        return roles;
    }

    public Secuser setRoles(String roles) {
        this.roles = roles;return this;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public Secuser setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;return this;
    }

    public String getShortcut() {
        return shortcut;
    }

    public Secuser setShortcut(String shortcut) {
        this.shortcut = shortcut;return this;
    }
}
