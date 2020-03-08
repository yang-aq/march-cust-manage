/**
 * 文件名： AccountControllerModifyOneIn.java
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

/**
 *
 * AccountControllerModifyOneIn vo输入类
 *
 *AccountController的ModifyOne方法的输入参数vo
 * @author chander
 * @date 2020-02-12 12:28:38
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="修改一条时的输入参数")
public class AccountControllerModifyOneIn {
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
