package com.pccw.cust.manage.provider.service;

import com.pccw.cust.manage.provider.entity.Account;
import com.pccw.march.core.mybatis.utils.sql.mybatis.QueryCondition;

import java.util.List;

/**
 * 账号
 * AccountService
 *
 * @author superAdmin
 * @date 2020-03-07 23:19:03
 *
 */

public interface AccountService {
	/**
	 * 新增一条
	 * @param account
	 * @return
	 */
	int createOne(Account account);
	/**
	 * 批量新增（通过实体列表）
	 * @param accountList
	 * @return
	 */
	int	createMany( List<Account> accountList);
	/**
	 * 删除一条
	 * @param id
	 * @return
	 */
	int removeOneByPrimaryKey(String id);
	/**
	 * 批量删除（通过主键id列表）
	 * @param ids
	 * @return
	 */
	int removeManyByPrimaryKeys(List<String> ids);
	/**
	 * 根据条件删除（实体类封装and条件）
	 * @param  account
	 * @return
	 */
    int removeByCondition(Account account);
	/**
	 * 修改一条
	 * @param account
	 * @return
	 */
    int modifyOne(Account  account);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
    Account queryByPrimaryKey( String id);
	/**
	 * 条件查询
	 * @param account
	 * @return
	 */
    List<Account> queryByCondition(Account  account);
	/**
	 * 查询所有
	 * @param
	 * @return
	 */
    List<Account> queryAll();
	/**
	 * 高级查询
	 * @param qc
	 * @return
	 */
    List<Account> advancedQuery(QueryCondition qc);
	
}
