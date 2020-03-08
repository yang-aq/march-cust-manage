package com.pccw.cust.manage.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pccw.cust.manage.provider.entity.Account;
import com.pccw.cust.manage.provider.feign.AccountFeign;
import com.pccw.cust.manage.provider.service.AccountService;
import com.pccw.march.core.base.utils.CommonUtil;
import com.pccw.march.core.base.utils.page.PageData;
import com.pccw.march.core.base.utils.page.PageUtil;
import com.pccw.march.core.base.utils.response.ResponseBean;
import com.pccw.march.core.base.utils.response.ResponseUtil;
import com.pccw.march.core.mybatis.utils.sql.mybatis.QueryCondition;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseBean createOne(@RequestBody Account  account) {
		accountService.createOne(account);
		return responseUtil.success();
	}
	/**
	 * 批量新增（通过实体列表）
	 * @param accountList
	 * @return
	 */
	@Override
	public ResponseBean createMany(@RequestBody List<Account> accountList) {
		accountService.createMany(accountList);
		return responseUtil.success();
	}
	/**
	 * 删除一条
	 * @param id
	 * @return
	 */
	@Override
	public ResponseBean removeOneByPrimaryKey(@PathVariable String id) {
		accountService.removeOneByPrimaryKey(id);
		return responseUtil.success();
	}
	/**
	 * 批量删除（通过主键id列表）
	 * @param ids
	 * @return
	 */
	@Override
	public ResponseBean removeManyByPrimaryKeys(@RequestBody List<String>  ids) {
		accountService.removeManyByPrimaryKeys(ids);
		return responseUtil.success();
	}
	/**
	 * 根据条件删除（实体类封装and条件）
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean removeByCondition( Account  account) {
//		List<Account> list=accountService.queryByCondition(account);
//		if(CollectionUtil.isEmpty(list)){
//			return responseUtil.error("Account.AccountController.removeManyByCondition.001");
//		}
		accountService.removeByCondition(account);
		return responseUtil.success();
	}
	/**
	 * 修改一条
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean modifyOne(@RequestBody Account  account) {
		accountService.modifyOne(account);
		return responseUtil.success();
	}
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@Override
	public ResponseBean<Account> queryByPrimaryKey(@PathVariable String id) {
		Account ret= accountService.queryByPrimaryKey(id);
		return responseUtil.success(ret);
	}
	/**
	 * 条件查询
	 * @param account
	 * @return
	 */
	@Override
	public ResponseBean<List<Account>> queryByCondition(Account  account) {
		CommonUtil.emptyStrToNull(account);
		List<Account> retList= accountService.queryByCondition(account);
		return responseUtil.success(retList);
	}
	/**
	 * 查询所有
	 * @param
	 * @return
	 */
	@Override
	public ResponseBean<List<Account>> queryAll() {
		List<Account> retList= accountService.queryAll();
		return responseUtil.success(retList);
	}
	/**
	 * 分页条件查询 （pageSize为0时，代表条件查询所有，不做分页）
	 * @param
	 * @return
	 */
	@Override
	public ResponseBean<PageData<Account>> queryByConditionWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, Account  account) {
		CommonUtil.emptyStrToNull(account);
		PageInfo<Account> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
				accountService.queryByCondition(account));

		return responseUtil.success(PageUtil.wrapPageData(pageInfo));

	}
	/**
	 * 分页查询所有 （pageSize为0时，代表查询所有，不做分页）
	 * @param
	 * @return
	 */
	@Override
	public ResponseBean<PageData<Account>> queryAllWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
		PageInfo<Account> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
				accountService.queryAll());

		return responseUtil.success(PageUtil.wrapPageData(pageInfo));

	}

	/**
	 * 高级查询（模糊查询、字段排序等高级功能)
	 * @return
	 */
	@Override
	public ResponseBean<List<Account>> advancedQuery(@RequestBody QueryCondition queryCondition) {
		List<Account> retList = accountService.advancedQuery(queryCondition);
		return responseUtil.success(retList);

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
	public ResponseBean<PageData<Account>> advancedQueryWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestBody QueryCondition queryCondition) {
		PageInfo<Account> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
				accountService.advancedQuery(queryCondition));
		return responseUtil.success(PageUtil.wrapPageData(pageInfo));

	}
}





