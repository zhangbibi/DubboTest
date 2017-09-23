package com.test.dubbo.provider.service;

import com.test.dubbo.api.ProviderService;

/**
 * Created by zhangyaping on 17/9/23.
 */
public class ProviderServiceImpl implements ProviderService{

    public String provider() {
        return "Test dubbo:provider";
    }

    public String provider2() {
        return "Test dubbo:provider2";
    }

}
