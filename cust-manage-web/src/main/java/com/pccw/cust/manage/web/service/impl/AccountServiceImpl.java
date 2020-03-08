package com.pccw.cust.manage.web.service.impl;

import com.pccw.cust.manage.provider.service.AccountService;
import com.pccw.cust.manage.provider.entity.Account;
import com.pccw.cust.manage.web.dao.AccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.pccw.march.core.mybatis.utils.sql.mybatis.QueryCondition;
import com.pccw.march.core.mybatis.utils.sql.mybatis.MybatisSqlUtil;
import cn.hutool.core.collection.CollectionUtil;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 账号
 * AccountServiceImpl
 *
 * @author superAdmin
 * @date 2020-03-07 23:19:03
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Override
    public int createOne(Account account){
        return accountDao.insertSelective(account);
    }

    @Override
    public int	createMany( List<Account> accountList){
        if(CollectionUtil.isEmpty(accountList)){
            return   0;
        }
        int i=0;
        for (Account tmp : accountList) {
            i +=createOne(tmp);
        }
        return i;
    }

    @Override
    public int removeOneByPrimaryKey(String id){
        return accountDao.deleteByPrimaryKey(id);
    }

    @Override
    public int	removeManyByPrimaryKeys(List<String> ids){
        if(CollectionUtil.isEmpty(ids)){
            return   0;
        }
        int i=0;
        for (String id : ids) {
            i +=removeOneByPrimaryKey(id);
        }
        return i;
    }

    @Override
    public int	removeByCondition(Account account){
        return accountDao.delete(account);
    }

    @Override
    public int	modifyOne(Account  account){
        return accountDao.updateByPrimaryKeySelective(account);
    }

    @Override
    public Account queryByPrimaryKey( String id){
        return accountDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Account> queryByCondition(Account  account){
        return accountDao.select(account);
    }

    @Override
    public List<Account> queryAll(){
        return accountDao.selectAll();
    }

    @Override
    public List<Account> advancedQuery(QueryCondition qc) {
        Example example = MybatisSqlUtil.toExample(qc, Account.class);
        return accountDao.selectByExample(example);
    }
}
