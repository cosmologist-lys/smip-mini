package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 小区信息
 */


@ApiModel(value="区域设置",description="区域>>小区")
@Entity
@Table(name = "bsccommunity")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCCOMMUNITY_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCCOMMUNITY_ID_SEQ", allocationSize = 10, name = "BSCCOMMUNITY_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "name")
    private String Name;//小区名称
    @NotNull
    @Column(name = "address")
    private String Address;//小区地址
    @NotNull
    @Column(name = "areaid")
    private Integer AreaId;//片区编号 外键 BSCAREA
    @Column(name = "stationid")
    private Integer StationId;//配气站编号 外键 BSCSTATION
    @Column(name = "contact")
    private String Contact;//联系人
    @Column(name = "tel")
    private String Tel;//联系电话
    @Column(name = "mobile")
    private String mobile;//手机
    @Column(name = "email")
    private String Email;//邮箱
    @Column(name = "note")
    private String Note;//备注
    @Transient
    private String areaName;//片区名字
    @Column(name = "meterlistid")
    private Integer meterlistId;//所在表册

    public Community() {
    }

    public Community(String name, String address, Integer areaId, Integer stationId, String contact, String tel, String mobile, String email, String note, String areaName, Integer meterlistId) {
        Name = name;
        Address = address;
        AreaId = areaId;
        StationId = stationId;
        Contact = contact;
        Tel = tel;
        this.mobile = mobile;
        Email = email;
        Note = note;
        this.areaName = areaName;
        this.meterlistId = meterlistId;
    }

    public Integer getId() {
        return id;
    }

    public Community setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Community setName(String name) {
        Name = name;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public Community setAddress(String address) {
        Address = address;
        return this;
    }

    public Integer getAreaId() {
        return AreaId;
    }

    public Community setAreaId(Integer areaId) {
        AreaId = areaId;
        return this;
    }

    public Integer getStationId() {
        return StationId;
    }

    public Community setStationId(Integer stationId) {
        StationId = stationId;
        return this;
    }

    public String getContact() {
        return Contact;
    }

    public Community setContact(String contact) {
        Contact = contact;
        return this;
    }

    public String getTel() {
        return Tel;
    }

    public Community setTel(String tel) {
        Tel = tel;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Community setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return Email;
    }

    public Community setEmail(String email) {
        Email = email;
        return this;
    }

    public String getNote() {
        return Note;
    }

    public Community setNote(String note) {
        Note = note;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public Community setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public Integer getMeterlistId() {
        return meterlistId;
    }

    public Community setMeterlistId(Integer meterlistId) {
        this.meterlistId = meterlistId;
        return this;
    }
}
