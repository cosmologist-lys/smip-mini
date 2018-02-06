package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ApiModel(value="表类型设置",description="表类型的具体设置")
@Entity
@Table(name = "bscmetertype")
public class Metertype {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCMETERTYPE_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCMETERTYPE_ID_SEQ", allocationSize = 1, name = "BSCMETERTYPE_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;// ID
    @NotNull
    @Column(name = "type")
    private String typename;// 表具型号
    @Column(name = "volume")
    private String volume;// 回转体积
    @Column(name = "qmax")
    private String qmax;// 最大流量
    @Column(name = "qmin")
    private String qmin;// 最小流量
    @Column(name = "pmax")
    private String pmax;// 最大工作压力
    @Column(name = "rmax")
    private String rmax;// 最大读数
    @Column(name = "rmin")
    private String rmin;// 最小读数
    @Column(name = "wmax")
    private Double wmax;// 报警上限
    @Column(name = "wmin")
    private Double wmin;// 报警下限
    @Column(name = "buymax")
    private Integer buyMax;// 最大购气量
    @Column(name = "note")
    private String note;// 备注
    @NotNull
    @Column(name = "model")
    private Integer model;//气表模式

    public Metertype() {
    }

    public Metertype(String typename, String volume, String qmax, String qmin, String pmax, String rmax, String rmin, Double wmax, Double wmin, Integer buyMax, String note, Integer model) {
        this.typename = typename;
        this.volume = volume;
        this.qmax = qmax;
        this.qmin = qmin;
        this.pmax = pmax;
        this.rmax = rmax;
        this.rmin = rmin;
        this.wmax = wmax;
        this.wmin = wmin;
        this.buyMax = buyMax;
        this.note = note;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public Metertype setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTypename() {
        return typename;
    }

    public Metertype setTypename(String typename) {
        this.typename = typename;
        return this;
    }

    public String getVolume() {
        return volume;
    }

    public Metertype setVolume(String volume) {
        this.volume = volume;
        return this;
    }

    public String getQmax() {
        return qmax;
    }

    public Metertype setQmax(String qmax) {
        this.qmax = qmax;
        return this;
    }

    public String getQmin() {
        return qmin;
    }

    public Metertype setQmin(String qmin) {
        this.qmin = qmin;
        return this;
    }

    public String getPmax() {
        return pmax;
    }

    public Metertype setPmax(String pmax) {
        this.pmax = pmax;
        return this;
    }

    public String getRmax() {
        return rmax;
    }

    public Metertype setRmax(String rmax) {
        this.rmax = rmax;
        return this;
    }

    public String getRmin() {
        return rmin;
    }

    public Metertype setRmin(String rmin) {
        this.rmin = rmin;
        return this;
    }

    public Double getWmax() {
        return wmax;
    }

    public Metertype setWmax(Double wmax) {
        this.wmax = wmax;
        return this;
    }

    public Double getWmin() {
        return wmin;
    }

    public Metertype setWmin(Double wmin) {
        this.wmin = wmin;
        return this;
    }

    public Integer getBuyMax() {
        return buyMax;
    }

    public Metertype setBuyMax(Integer buyMax) {
        this.buyMax = buyMax;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Metertype setNote(String note) {
        this.note = note;
        return this;
    }

    public Integer getModel() {
        return model;
    }

    public Metertype setModel(Integer model) {
        this.model = model;
        return this;
    }
}
