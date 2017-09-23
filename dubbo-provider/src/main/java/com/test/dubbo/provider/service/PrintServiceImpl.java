package com.test.dubbo.provider.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.test.dubbo.api.PrintService;

/**
 * Created by zhangyaping on 17/9/23.
 */
public class PrintServiceImpl implements PrintService {

    public String print() {

        boolean isProviderSide = RpcContext.getContext().isProviderSide();

        if (isProviderSide){
            System.out.println("我是服务..");
        }

        return "Hello ....";

    }
}
