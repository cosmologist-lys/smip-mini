package com.smip.entity.basement;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 编码规则设置
 * 卡片、居民、表的编码前缀和长度等设置
 */

@ApiModel(value="规则设置",description="卡片、居民、表的编码前缀和长度等设置")
@Entity
@Table(name = "bscseq")
public class Encodesets {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCSEQ_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCSEQ_ID_SEQ", allocationSize = 1, name = "BSCSEQ_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;//主键id
    @NotNull
    @Column(name = "name")
    private String name; //SEQ名称
    @NotNull
    @Column(name = "type")
    private Integer type;//SEQ类型
    @NotNull
    @Column(name = "prefix")
    private String prefix;//前缀
    @NotNull
    @Column(name = "seqlen")
    private Integer seqlen;//长度
    @Column(name = "seq")
    private Integer curlength;//SEQ

    public Encodesets() {
    }

    public Encodesets(String name, Integer type, String prefix, Integer seqlen, Integer curlength) {
        this.name = name;
        this.type = type;
        this.prefix = prefix;
        this.seqlen = seqlen;
        this.curlength = curlength;
    }

    @Override
    public String toString() {
        return "Encodesets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", prefix='" + prefix + '\'' +
                ", seqlen=" + seqlen +
                ", curlength=" + curlength +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Encodesets setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Encodesets setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Encodesets setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Encodesets setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Integer getSeqlen() {
        return seqlen;
    }

    public Encodesets setSeqlen(Integer seqlen) {
        this.seqlen = seqlen;
        return this;
    }

    public Integer getCurlength() {
        return curlength;
    }

    public Encodesets setCurlength(Integer curlength) {
        this.curlength = curlength;
        return this;
    }
}
