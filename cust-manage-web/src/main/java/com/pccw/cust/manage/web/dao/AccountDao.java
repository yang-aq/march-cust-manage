package com.pccw.cust.manage.web.dao;

import com.pccw.cust.manage.provider.entity.Account;
import com.pccw.march.core.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * 账号
 * AccountDao
 *
 * @author superAdmin
 * @date 2020-03-07 23:19:03
 *
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {


}