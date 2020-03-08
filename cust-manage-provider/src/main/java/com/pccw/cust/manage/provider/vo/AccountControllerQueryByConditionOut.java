/**
 * 文件名： AccountControllerQueryByConditionOut.java
 * 版权： Copyright 2020-2025 PCCW All Rights Reserved.
 * 描述： 人力资源系统
 */
package com.pccw.cust.manage.provider.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * AccountControllerQueryByConditionOut vo输出类
 *
 *AccountController的QueryByPrimaryKey方法的返回结果vo
 * @author chander
 * @date 2020-02-12 12:28:38
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="条件查询时的返回结果")
public class AccountControllerQueryByConditionOut {
    /**
     * 主键
     */
    @ApiModelProperty( "主键")
    private String id;
    /**
     * 创建时间
     */
    @ApiModelProperty( "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty( "更新时间")
    private Date updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty( "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @ApiModelProperty( "更新人")
    private String updateBy;

    /**
     * 账号名
     */
    @ApiModelProperty( "账号名")
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty( "性别")
    private String gender;
    /**
     * 年龄
     */
    @ApiModelProperty( "年龄")
    private Integer age;
}
