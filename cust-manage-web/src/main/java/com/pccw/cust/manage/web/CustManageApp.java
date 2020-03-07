package com.pccw.cust.manage.web;

import com.pccw.march.core.base.constant.MarchConstant;
import com.pccw.cust.manage.provider.constant.CustManageProviderConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
* 启动类
*
* @author chander
* @date	 2019-09-06 10:23:52
*/

@SpringBootApplication(scanBasePackages ={CustManageProviderConstant.ROOT_PACKAGE,MarchConstant.COMPONET_SCAN})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {MarchConstant.FEIGN_PLATFORM})
public class CustManageApp {

    public static void main(String[] args) {
        SpringApplication.run(CustManageApp.class, args);
    }

}
