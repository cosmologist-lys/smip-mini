package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="气价类型",description="设置气价规则等")
@Entity
@Table(name = "bscpricetype")
public class Pricetype {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCPRICETYPE_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCPRICETYPE_ID_SEQ", allocationSize = 1, name = "BSCPRICETYPE_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;// id
    @NotNull
    @Column(name = "name")
    private String name;// 气费名称
    @NotNull
    @Column(name = "price")
    private BigDecimal price;// 单价
    @Column(name = "note")
    private String note;// 备注
    private Integer step1;// 阶梯量1
    private BigDecimal price1;// 气价1
    private Integer step2;// 阶梯量2
    private BigDecimal price2;// 气价2
    private Integer step3;// 阶梯量3
    private BigDecimal price3;// 气价3
    private Integer type; //阶梯类型
    private Integer delay;//抄表延迟值
    @Column(name = "executedate")
    @Temporal(TemporalType.DATE)
    private Date executeDate;//调价日期
    @Column(name = "executecycle")
    private Integer executeCycle;//执行周期
    @Column(name = "paymonth")
    private Integer payMonth;//结算月份
    private Integer inverse;//0 递增阶梯 1 递减阶梯

    public Pricetype() {
    }

    public Pricetype(String name, BigDecimal price, String note, Integer step1, BigDecimal price1, Integer step2, BigDecimal price2, Integer step3, BigDecimal price3, Integer type, Integer delay, Date executeDate, Integer executeCycle, Integer payMonth, Integer inverse) {
        this.name = name;
        this.price = price;
        this.note = note;
        this.step1 = step1;
        this.price1 = price1;
        this.step2 = step2;
        this.price2 = price2;
        this.step3 = step3;
        this.price3 = price3;
        this.type = type;
        this.delay = delay;
        this.executeDate = executeDate;
        this.executeCycle = executeCycle;
        this.payMonth = payMonth;
        this.inverse = inverse;
    }

    public Integer getId() {
        return id;
    }

    public Pricetype setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pricetype setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Pricetype setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Pricetype setNote(String note) {
        this.note = note;
        return this;
    }

    public Integer getStep1() {
        return step1;
    }

    public Pricetype setStep1(Integer step1) {
        this.step1 = step1;
        return this;
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public Pricetype setPrice1(BigDecimal price1) {
        this.price1 = price1;
        return this;
    }

    public Integer getStep2() {
        return step2;
    }

    public Pricetype setStep2(Integer step2) {
        this.step2 = step2;
        return this;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public Pricetype setPrice2(BigDecimal price2) {
        this.price2 = price2;
        return this;
    }

    public Integer getStep3() {
        return step3;
    }

    public Pricetype setStep3(Integer step3) {
        this.step3 = step3;
        return this;
    }

    public BigDecimal getPrice3() {
        return price3;
    }

    public Pricetype setPrice3(BigDecimal price3) {
        this.price3 = price3;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Pricetype setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getDelay() {
        return delay;
    }

    public Pricetype setDelay(Integer delay) {
        this.delay = delay;
        return this;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public Pricetype setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
        return this;
    }

    public Integer getExecuteCycle() {
        return executeCycle;
    }

    public Pricetype setExecuteCycle(Integer executeCycle) {
        this.executeCycle = executeCycle;
        return this;
    }

    public Integer getPayMonth() {
        return payMonth;
    }

    public Pricetype setPayMonth(Integer payMonth) {
        this.payMonth = payMonth;
        return this;
    }

    public Integer getInverse() {
        return inverse;
    }

    public Pricetype setInverse(Integer inverse) {
        this.inverse = inverse;
        return this;
    }
}
