DROP TABLE IF EXISTS `cust_manage_account`;
CREATE TABLE `cust_manage_account` (
    `id` varchar(30) NOT NULL COMMENT '主键',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
    `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
    `name` varchar(255) DEFAULT NULL COMMENT '账号名',
    `gender` varchar(255) DEFAULT NULL COMMENT '性别',
    `age` bigint(255) DEFAULT NULL COMMENT '年龄',
    `phone` varchar(255) DEFAULT NULL COMMENT '电话号码',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='账号';
