package com.smip.entity.account;

import com.smip.entity.QueryEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@ApiModel(value="居民对象",description="居民")
@Entity
public class Bscresident extends QueryEntity implements Serializable{
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
                ", testInfo='" + testInfo + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Bscresident setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Bscresident setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bscresident setName(String name) {
        this.name = name;
        return this;
    }

    public String getPinYin() {
        return pinYin;
    }

    public Bscresident setPinYin(String pinYin) {
        this.pinYin = pinYin;
        return this;
    }

    public String getIdNo() {
        return idNo;
    }

    public Bscresident setIdNo(String idNo) {
        this.idNo = idNo;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Bscresident setCompany(String company) {
        this.company = company;return this;
    }

    public String getTel() {
        return tel;
    }

    public Bscresident setTel(String tel) {
        this.tel = tel;return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Bscresident setMobile(String mobile) {
        this.mobile = mobile;return this;
    }

    public String getEmail() {
        return email;
    }

    public Bscresident setEmail(String email) {
        this.email = email;return this;
    }

    public String getNote() {
        return note;
    }

    public Bscresident setNote(String note) {
        this.note = note;return this;
    }

    public String getContractNo() {
        return contractNo;
    }

    public Bscresident setContractNo(String contractNo) {
        this.contractNo = contractNo;return this;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public Bscresident setContractDate(Date contractDate) {
        this.contractDate = contractDate;return this;
    }

    public Date getInstDate() {
        return instDate;
    }

    public Bscresident setInstDate(Date instDate) {
        this.instDate = instDate;return this;
    }
}
