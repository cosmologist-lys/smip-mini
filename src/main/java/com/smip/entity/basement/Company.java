package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kepler@gmail.com on 2018/1/31.
 */
@ApiModel(value="居民对象",description="居民")
@Entity
@Table(name = "bsccompany")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCCOMPANY_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCCOMPANY_ID_SEQ", allocationSize = 1, name = "BSCCOMPANY_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;// 主键
    @NotNull
    @Column(name = "name")
    private String name;// 公司名称
    @Column(name = "address")
    private String address;// 公司地址
    @Column(name = "contact")
    private String charger;// 负责人
    @Column(name = "tel")
    private String tel;// 联系电话
    @Column(name = "mobile")
    private String mobile;// 手机
    @Column(name = "email")
    private String email;// Email
    @Column(name = "resseq")
    private Integer resseq;
    @Transient
    private String resseqName;
    @Column(name = "cardseq")
    private Integer cardseq;
    @Transient
    private String cardseqName;
    @Column(name = "meterseq")
    private Integer meterseq;
    @Transient
    private String meterseqName;

    public Company() {
    }

    public Company(String name, String address, String charger, String tel, String mobile, String email, Integer resseq, String resseqName, Integer cardseq, String cardseqName, Integer meterseq, String meterseqName) {
        this.name = name;
        this.address = address;
        this.charger = charger;
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
        this.resseq = resseq;
        this.resseqName = resseqName;
        this.cardseq = cardseq;
        this.cardseqName = cardseqName;
        this.meterseq = meterseq;
        this.meterseqName = meterseqName;
    }

    public Integer getId() {
        return id;
    }

    public Company setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Company setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCharger() {
        return charger;
    }

    public Company setCharger(String charger) {
        this.charger = charger;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public Company setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Company setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Company setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getResseq() {
        return resseq;
    }

    public void setResseq(Integer resseq) {
        this.resseq = resseq;
    }

    public String getResseqName() {
        return resseqName;
    }

    public void setResseqName(String resseqName) {
        this.resseqName = resseqName;
    }

    public Integer getCardseq() {
        return cardseq;
    }

    public void setCardseq(Integer cardseq) {
        this.cardseq = cardseq;
    }

    public String getCardseqName() {
        return cardseqName;
    }

    public void setCardseqName(String cardseqName) {
        this.cardseqName = cardseqName;
    }

    public Integer getMeterseq() {
        return meterseq;
    }

    public void setMeterseq(Integer meterseq) {
        this.meterseq = meterseq;
    }

    public String getMeterseqName() {
        return meterseqName;
    }

    public void setMeterseqName(String meterseqName) {
        this.meterseqName = meterseqName;
    }
}
