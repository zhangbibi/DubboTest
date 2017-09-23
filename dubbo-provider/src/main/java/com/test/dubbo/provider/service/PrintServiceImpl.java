package com.test.dubbo.provider.service;

import com.test.dubbo.api.PrintService;

/**
 * Created by zhangyaping on 17/9/23.
 */
public class PrintServiceImpl implements PrintService {

    public String print() {
        System.out.println("Hello dubbo..");
        return "Hello ....";
    }
}
