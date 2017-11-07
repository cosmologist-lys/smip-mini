package com.smip.entity.account;

import org.hibernate.criterion.Example;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Bscresident implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCRESIDENT_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCRESIDENT_ID_SEQ", allocationSize = 1, name = "BSCRESIDENT_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;// 主键id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCRESIDENT_CODE_SEQ" )
    @SequenceGenerator(sequenceName = "BSCRESIDENT_CODE_SEQ", allocationSize = 1, name = "BSCRESIDENT_CODE_SEQ")
    private String code;// 居民编号
    private String name;// 姓名
    @Column(name="pinyin")
    private String pinYin;// 拼音
    @Column(name="idno")
    private String idNo;// 身份证
    private String company;// 工作单位
    private String tel;// 电话
    private String mobile;// 移动电话
    private String email;// 邮件
    private String note;// 备注
    @Column(name="contractno")
    private String contractNo;//合同编号
    @Column(name="contractdate")
    @Temporal(TemporalType.DATE)
    private Date contractDate;//合同签订日期
    @Column(name = "instdate")
    @Temporal(TemporalType.DATE)
    private Date instDate;//开户日期
    @Transient
    private String testInfo;

    public String getTestInfo() {
        return testInfo;
    }

    public void setTestInfo(String testInfo) {
        this.testInfo = testInfo;
    }

    public Bscresident(String code, String name, String pinYin, String idNo, String company, String tel, String mobile, String email, String note, String contractNo, Date contractDate, Date instDate) {
        this.code = code;
        this.name = name;
        this.pinYin = pinYin;
        this.idNo = idNo;
        this.company = company;
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
        this.note = note;
        this.contractNo = contractNo;
        this.contractDate = contractDate;
        this.instDate = instDate;
    }

    public Bscresident() {
    }

    @Override
    public String toString() {
        return "Bscresident{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", idNo='" + idNo + '\'' +
                ", company='" + company + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractDate=" + contractDate +
                ", instDate=" + instDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getInstDate() {
        return instDate;
    }

    public void setInstDate(Date instDate) {
        this.instDate = instDate;
    }
}
