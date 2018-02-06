package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ApiModel(value="卡类型设置",description="卡型号厂家单价等设置")
@Entity
@Table(name = "bsccardtype")
public class Cardtype {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCCARDTYPE_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCCARDTYPE_ID_SEQ", allocationSize = 1, name = "BSCCARDTYPE_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "type")
    private String type;//卡片型号
    @NotNull
    @Column(name = "company")
    private String company;//供应厂家
    @NotNull
    @Column(name = "price")
    private Double price;//单价
    @Column(name = "note")
    private String note;//备注

    public Cardtype() {
    }

    public Cardtype(String type, String company, Double price, String note) {
        this.type = type;
        this.company = company;
        this.price = price;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public Cardtype setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Cardtype setType(String type) {
        this.type = type;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Cardtype setCompany(String company) {
        this.company = company;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Cardtype setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Cardtype setNote(String note) {
        this.note = note;
        return this;
    }
}
