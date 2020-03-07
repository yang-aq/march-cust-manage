package com.pccw.cust.manage.provider.constant;

public class CustManageProviderConstant {


    /**
     * 本系统的微服务名(feign client 类中用到）
     */
    public static final String SERVICE_NAME = "cust-manage";

    /**
     * 本系统顶级包(启动类扫描）
     */
    public static final String ROOT_PACKAGE = "com.pccw.cust.manage";

    /**
     * 本系统feign客户端扫描路径（供外部使用）
     */
    public static final String FEIGN_CLIENT = "com.pccw.cust.manage.provider.feign";
}
