package com.pccw.cust.manage.provider.feign;

import com.pccw.cust.manage.provider.entity.Account;
import com.pccw.cust.manage.provider.constant.CustManageProviderConstant;
import com.pccw.march.core.base.utils.page.PageData;
import com.pccw.march.core.base.utils.response.ResponseBean;
import com.pccw.march.core.mybatis.utils.sql.mybatis.QueryCondition;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 账号
 * controller
 *
 * @author superAdmin
 * @date 2020-03-07 23:19:03
 *
*/
@FeignClient(value= CustManageProviderConstant.SERVICE_NAME)
@RequestMapping("/accounts")

public interface AccountFeign {


	/**
	 * 新增一条
	 * @param account
	 * @return
	 */
	@PostMapping
	@ApiOperation("新增一条")
	ResponseBean createOne(@RequestBody Account  account);
	/**
	 * 批量新增（通过实体列表）
	 * @param accountList
	 * @return
	 */
	@PostMapping("/many")
	@ApiOperation("批量新增（通过实体列表）")
	ResponseBean createMany(@RequestBody List<Account> accountList);
	/**
	 * 删除一条
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation("删除一条")
	ResponseBean removeOneByPrimaryKey(@PathVariable String id);
	/**
	 * 批量删除（通过主键id列表）
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/ids")
	@ApiOperation("批量删除（通过主键id列表）")
	ResponseBean removeManyByPrimaryKeys(@RequestBody List<String>  ids);
	/**
	 * 根据条件删除（实体类封装and条件）
	 * @param account
	 * @return
	 */
	@DeleteMapping("/condition")
	@ApiOperation("根据条件删除")
	ResponseBean removeByCondition( Account  account);
	/**
	 * 修改一条
	 * @param account
	 * @return
	 */
	@PutMapping
	@ApiOperation("修改一条")
	ResponseBean modifyOne(@RequestBody Account  account);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation("根据主键查询")
	ResponseBean<Account> queryByPrimaryKey(@PathVariable String id);
	/**
	 * 条件查询
	 * @param account
	 * @return
	 */
	@GetMapping("/condition")
	@ApiOperation("条件查询")
	ResponseBean<List<Account>> queryByCondition(Account  account);
	/**
	 * 查询所有
	 * @param
	 * @return
	 */
	@GetMapping
	@ApiOperation("查询所有")
	ResponseBean<List<Account>> queryAll();
	/**
	 * 分页条件查询 （pageSize为0时，代表条件查询所有，不做分页）
	 * @param
	 * @return
	 */
	@GetMapping("/condition/page")
	@ApiOperation("分页条件查询")
	ResponseBean<PageData<Account>> queryByConditionWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, Account  account);
	/**
	 * 分页查询所有 （pageSize为0时，代表查询所有，不做分页）
	 * @param
	 * @return
	 */
	@GetMapping("/page")
	@ApiOperation("分页查询所有")
	ResponseBean<PageData<Account>> queryAllWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

	/**
	 * 高级查询（模糊查询、字段排序等高级功能)
	 * @return
	 */
	@ApiOperation(value = "高级查询（模糊查询、字段排序等高级功能)",
	notes = "传参说明：\n" +
			"{\n" +
			"　　\"and\": [{\"criteria\": \"\",\"data\": [],\"fieldName\": \"\"}],\n" +
			"　　\"or\": [{\"criteria\": \"\",\"data\": [],\"fieldName\": \"\"}],\n" +
			"　　\"orderBy\": [{\"criteria\": \"\",\"data\": [],\"fieldName\": \"\"}]\n" +
			"}\n" +
			"and里面传入要作为and条件查询的字段\n" +
			"or里面传入要作为or条件查询的字段\n" +
			"oderBy里面传入要作为排序的字段\n" +
			"and ，or和orderBy可以组合使用，如果都不传，则无搜索条件，默认查询所有\n" +
			"　　　\n" +
			"criteria：目前支持IsNull，IsNotNull，EqualTo，NotEqualTo，GreaterThan，GreaterThanOrEqualTo，LessThan，\n" +
			"\t\t\tLessThanOrEqualTo，In, NotIn, Between, NotBetween, Like, NotLike, Condition, AllEqualTo\n" +
			"data:  对应每个entiy\n" +
			"fieldName: java entity里面的属性名\n" +
			"　　　\n" +
			"以Code entity为例子\n" +
			" public class Code {\n" +
			"　　　private String codeId;\n" +
			"　　　private Date createTime;\n" +
			"　　　private Date updateTime;\n" +
			"　　　private String createBy;\n" +
			"　　　private String updateBy;\n" +
			"　　　private String packageName;\n" +
			"　　　private String className;\n" +
			"　　　private String tableName;\n" +
			"　　　private String moduleDesc;\n" +
			"　　　private String codeType;\n" +
			"　　　private String relateCodeId;\n" +
			"}\n" +
			"　　　\n" +
			"查询以codeId=\"111111\"且tableName like \"%account%\"或者packageName=\"com.pccw\",并且以createTime升序，createBy降序，className升序的方式排序\n" +
			"　　　{\n" +
			"　　　　\"and\": [{\"criteria\": \"EqualTo\",\"data\": [\"111111\"],\"fieldName\": \"codeId\"},{\"criteria\": \"Like\",\"data\": [\"account\"],\"fieldName\": \"tableName\"}],\n" +
			"　　　　\"or\": [{\"criteria\": \"EqualTo\",\"data\": [\"com.pccw\"],\"fieldName\": \"packageName\"}],\n" +
			"　　　　\"orderBy\": [{\"criteria\": \"Asc\",\"fieldName\": \"createTime\"},{\"criteria\": \"Desc\",\"fieldName\": \"createBy\"},{\"criteria\": \"Asc\",\"fieldName\": \"className\"}]\n" +
			"　　　}")

	@PostMapping("/advancedQuery")
	ResponseBean<List<Account>> advancedQuery(@RequestBody QueryCondition queryCondition);

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
	@ApiOperation("高级分页查询（模糊查询、字段排序等高级功能)，使用参考高级查询")
	@PostMapping("/advancedQuery/page")
	ResponseBean<PageData<Account>> advancedQueryWithPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,@RequestBody QueryCondition queryCondition);





}





