package com.test.dubbo.testzk.reg.test;

import com.test.dubbo.testzk.reg.base.CoordinatorRegistryCenter;
import com.test.dubbo.testzk.reg.zookeeper.ZookeeperConfiguration;
import com.test.dubbo.testzk.reg.zookeeper.ZookeeperRegistryCenter;
import java.util.List;

/**
 * Created by 80205116 on 2018/6/17.
 */
public class TestZK {

    public static void main(String[] args) {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
            new ZookeeperConfiguration("10.17.16.187:2181", "TestZK"));
        regCenter.init();

        //持久化
        regCenter.persist("/T1", "value1");
        System.out.println(regCenter.get("/T1"));
        //加入缓存路径
        regCenter.addCacheData("/T1");

        //释放酬谢缓存
//        regCenter.evictCacheData("/T1");
        List<String> cs = regCenter.getChildrenKeys("/");
        System.out.println(cs);
        //直接从zk读
        String v = regCenter.getDirectly("/T1");
        System.out.println(v);

        System.out.println(regCenter.getNumChildren("/"));

        Object obj = regCenter.getRawCache("/T1");
        System.out.println(obj);

        //临时节点
        regCenter.persistEphemeral("/T2", "value2");
        //临时顺序节点
        regCenter.persistEphemeralSequential("/T2");
        //顺序节点
        regCenter.persistSequential("/T3", "value3");
        //关闭
        regCenter.close();


    }
}
