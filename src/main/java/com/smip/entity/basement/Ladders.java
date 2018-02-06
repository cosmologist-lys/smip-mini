/*
package com.smip.entity.basement;


import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value="阶梯收费",description="阶梯收费参数设置")
@Entity
@Table(name = "bscdiffexpense")
public class Ladders {
    */
/*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BSCDIFFEXPENSE_ID_SEQ" )
    @SequenceGenerator(sequenceName = "BSCDIFFEXPENSE_ID_SEQ", allocationSize = 1, name = "BSCDIFFEXPENSE_ID_SEQ")
    @Column(name = "id",nullable = false)
    private Integer id;// id
    @NotNull
    @Column(name = "name")*//*

    private String name;// 阶梯费用名称
    private Integer type;// 阶梯类型
    private Integer Method;// 阶梯类型
    private Double expense;// 基本费
    private Double rate0;// 费率0
    private Double step1;// 阶梯量1
    private Double expense1;// 收费量1
    private Double rate1;// 费率1
    private Double step2;// 阶梯量2
    private Double expense2;// 收费量2
    private Double rate2;// 费率2
    private Double step3;// 阶梯量3
    private Double expense3;// 收费量3
    private Double rate3;// 费率3
    private Integer merge;//并入账单
    private Date activeDate;// 生效时间
}
*/
