package com.pccw.cust.manage.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pccw.cust.manage.provider.entity.Account;
import com.pccw.cust.manage.provider.feign.AccountFeign;
import com.pccw.cust.manage.provider.service.AccountService;
import com.pccw.cust.manage.provider.vo.*;
import com.pccw.march.core.base.utils.CommonUtil;
import com.pccw.march.core.base.utils.page.PageData;
import com.pccw.march.core.base.utils.page.PageUtil;
import com.pccw.march.core.base.utils.response.ResponseBean;
import com.pccw.march.core.base.utils.response.ResponseUtil;
import com.pccw.march.core.mybatis.utils.sql.mybatis.QueryCondition;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 账号
 * controller
 *
 * @author superAdmin
 * @date 2020-03-07 23:19:03
 *
*/
@RestController
@Slf4j
@Api(tags={"账号模块"})
public class AccountController implements AccountFeign {
	@Autowired
	private AccountService accountService;

	@Autowired
	ResponseUtil responseUtil;


	/**
	 * 新增一条
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean createOne(AccountControllerCreateOneIn accountInVo) {
		//vo属性拷贝准换entity
		Account account =CommonUtil.toDeepBean(accountInVo, Account.class);
		accountService.createOne(account);
		return responseUtil.success();
	}
	/**
	 * 批量新增（通过实体列表）
	 * @param accountList
	 * @return
	 */
	@Override
	public ResponseBean createMany(List<AccountControllerCreateManyIn> accountListInVo) {
		List<Account> accountList = CommonUtil.toBeanList(accountListInVo, Account.class);
		accountService.createMany(accountList);
		return responseUtil.success();
	}
	/**
	 * 删除一条
	 * @param id
	 * @return
	 */
	@Override
	public ResponseBean removeOneByPrimaryKey(String id) {
		accountService.removeOneByPrimaryKey(id);
		return responseUtil.success();
	}
	/**
	 * 批量删除（通过主键id列表）
	 * @param ids
	 * @return
	 */
	@Override
	public ResponseBean removeManyByPrimaryKeys(List<String>  ids) {
		accountService.removeManyByPrimaryKeys(ids);
		return responseUtil.success();
	}
	/**
	 * 根据条件删除（实体类封装and条件）
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean removeByCondition( AccountControllerRemoveByConditionIn accountInVo) {

//		List<Account> list=accountService.queryByCondition(account);
//		if(CollectionUtil.isEmpty(list)){
//			return responseUtil.error("Account.AccountController.removeManyByCondition.001");
//		}
		Account account = CommonUtil.toDeepBean(accountInVo, Account.class);
		accountService.removeByCondition(account);
		return responseUtil.success();
	}
	/**
	 * 修改一条
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean modifyOne(String id, AccountControllerModifyOneIn accountInVo) {
		Account account = CommonUtil.toDeepBean(accountInVo, Account.class);
		account.setId(id);
		accountService.modifyOne(account);
		return responseUtil.success();
	}
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@Override
	public ResponseBean<AccountControllerQueryByPrimaryKeyOut> queryByPrimaryKey(String id) {
		Account account= accountService.queryByPrimaryKey(id);
		return responseUtil.success(CommonUtil.toDeepBean(account, AccountControllerQueryByPrimaryKeyOut.class));
	}
	/**
	 * 条件查询
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean<List<AccountControllerQueryByConditionOut>> queryByCondition(@SpringQueryMap AccountControllerQueryByConditionIn accountInVo) {
		//配合前端，将“”空字符串转换为null
		CommonUtil.emptyStrToNull(accountInVo);
		Account account = CommonUtil.toDeepBean(accountInVo,Account.class);
		List<Account> retList= accountService.queryByCondition(account);
		return responseUtil.success(CommonUtil.toBeanList(retList,AccountControllerQueryByConditionOut.class));
	}
	/**
	 * 查询所有
	 * @param
	 * @return
	 */
	@Override
	public ResponseBean<List<AccountControllerQueryAllOut>> queryAll() {
		List<Account> retList= accountService.queryAll();
		return responseUtil.success(CommonUtil.toBeanList(retList, AccountControllerQueryAllOut.class));
	}
	/**
	 * 分页条件查询 （pageSize为0时，代表条件查询所有，不做分页）
	 * @param
	 * @return
	 */
	@Override
	public ResponseBean<PageData<AccountControllerQueryByConditionWithPageOut>> queryByConditionWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, AccountControllerQueryByConditionWithPageIn  accountInVo) {
		//配合前端，将“”空字符串转换为null
		CommonUtil.emptyStrToNull(accountInVo);
		Account account = CommonUtil.toDeepBean(accountInVo,Account.class);
		PageInfo<Account> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
				accountService.queryByCondition(account));

		return responseUtil.success(PageUtil.wrapPageData(pageInfo,new AccountControllerQueryByConditionWithPageOut()));

	}
	/**
	 * 分页查询所有 （pageSize为0时，代表查询所有，不做分页）
	 * @param
	 * @return
	 */
	@Override
	public ResponseBean<PageData<AccountControllerQueryAllWithPageOut>> queryAllWithPage(int pageNum, int pageSize) {
		PageInfo<Account> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
				accountService.queryAll());

		return responseUtil.success(PageUtil.wrapPageData(pageInfo,new AccountControllerQueryAllWithPageOut()));

	}

	/**
	 * 高级查询（模糊查询、字段排序等高级功能)
	 * @return
	 */
	@Override
	public ResponseBean<List<AccountControllerAdvancedQueryOut>> advancedQuery(QueryCondition queryCondition) {
		List<Account> retList = accountService.advancedQuery(queryCondition);
		//entity属性拷贝转换vo
		return responseUtil.success(CommonUtil.toBeanList(retList,AccountControllerAdvancedQueryOut.class));

	}

	/**
	 * 高级分页查询（模糊查询、字段排序等高级功能)
	 * @param queryCondition
	 * 传参说明：
	 * {
	 *   "and": [{"criteria": "","data": [],"fieldName": ""}],
	 *   "or": [{"criteria": "","data": [],"fieldName": ""}],
	 *   "orderBy": [{"criteria": "","data": [],"fieldName": ""}]
	 * }
	 * and里面传入要作为and条件查询的字段
	 * or里面传入要作为or条件查询的字段
	 * oderBy里面传入要作为排序的字段
	 * and ，or和orderBy可以组合使用，如果都不传，则无搜索条件，默认查询所有
	 *
	 *  criteria：目前支持IsNull，IsNotNull，EqualTo，NotEqualTo，GreaterThan，GreaterThanOrEqualTo，LessThan，
	 * 			LessThanOrEqualTo，In, NotIn, Between, NotBetween, Like, NotLike, Condition, AllEqualTo
	 * data:  对应每个entiy
	 * fieldName: java entity里面的属性名
	 *
	 * 以Code entity为例子
	 *  public class Code {
	 *     private String codeId;
	 *     private Date createTime;
	 *     private Date updateTime;
	 *     private String createBy;
	 *     private String updateBy;
	 *     private String packageName;
	 *     private String className;
	 *     private String tableName;
	 *     private String moduleDesc;
	 *     private String codeType;
	 *     private String relateCodeId;
	 * }
	 *查询以codeId="111111"且tableName like "%account%"或者packageName="com.pccw",并且以createTime升序，createBy降序，className升序的方式排序
	 * {
	 *   "and": [{"criteria": "EqualTo","data": ["111111"],"fieldName": "codeId"},{"criteria": "Like","data": ["account"],"fieldName": "tableName"}],
	 *   "or": [{"criteria": "EqualTo","data": ["com.pccw"],"fieldName": "packageName"}],
	 *   "orderBy": [{"criteria": "Asc","fieldName": "createTime"},{"criteria": "Desc","fieldName": "createBy"},{"criteria": "Asc","fieldName": "className"}]
	 * }
	 *
	 * @return
	 */
	@Override
	public ResponseBean<PageData<AccountControllerAdvancedQueryWithPageOut>> advancedQueryWithPage(int pageNum, int pageSize, QueryCondition queryCondition) {
		PageInfo<Account> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
				accountService.advancedQuery(queryCondition));
		return responseUtil.success(PageUtil.wrapPageData(pageInfo,new AccountControllerAdvancedQueryWithPageOut()));

	}
}





