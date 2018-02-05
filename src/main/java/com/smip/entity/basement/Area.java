package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ApiModel(value="区域设置",description="区域>>小区")
@Entity
@Table(name = "bscarea")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCAREA_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCAREA_ID_SEQ", allocationSize = 1, name = "BSCAREA_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;// id
    @NotNull
    @Column(name = "name")
    private String name;// 片区名字
    @Column(name = "districtid")
    private Integer districtId;// 区域编号 ，暂废
    @Column(name = "districtname")
    private String districtname; //同上
    @Column(name = "note")
    private String note;// 备注

    public Area() {
    }

    public Area(String name, Integer districtId, String districtname, String note) {
        this.name = name;
        this.districtId = districtId;
        this.districtname = districtname;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public Area setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Area setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public Area setDistrictId(Integer districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getDistrictname() {
        return districtname;
    }

    public Area setDistrictname(String districtname) {
        this.districtname = districtname;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Area setNote(String note) {
        this.note = note;
        return this;
    }
}
