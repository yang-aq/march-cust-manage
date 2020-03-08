package com.pccw.cust.manage.provider.entity;

import com.pccw.march.core.mybatis.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* 账号
*
* @author superAdmin
* @date 2020-03-07 23:19:03
*/

@Table(name = "cust_manage_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="账号")
public class Account implements Serializable {
    /**
     * 主键
     */
    @Id
    @UUID
    @Column(name = "id")
    @ApiModelProperty( "主键")
    private String id;

    /**
     * 创建时间
     */
    @CreateDate
    @Column(name = "create_time")
    @ApiModelProperty( "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @UpdateDate
    @Column(name = "update_time")
    @ApiModelProperty( "更新时间")
    private Date updateTime;

    /**
     * 创建人
     */
    @CreateBy
    @Column(name = "create_by")
    @ApiModelProperty( "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @UpdateBy
    @Column(name = "update_by")
    @ApiModelProperty( "更新人")
    private String updateBy;

    /**
     * 账号名
     */
    @Column(name = "name")
    @ApiModelProperty( "账号名")
    private String name;
    /**
     * 性别
     */
    @Column(name = "gender")
    @ApiModelProperty( "性别")
    private String gender;
    /**
     * 年龄
     */
    @Column(name = "age")
    @ApiModelProperty( "年龄")
    private Integer age;
    /**
     * 电话号码
     */
    @Column(name = "phone")
    @ApiModelProperty( "电话号码")
    private String phone;

}