package com.test.dubbo.consumer;

import com.test.dubbo.api.PrintService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangyaping on 17/9/23.
 */
public class PrintConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:META-INF/spring/dubbo-demo-consume.xml"});
        context.start();
        PrintService printService = (PrintService) context.getBean("printService"); // 获取远程服务代理
        String result = printService.print(); // 执行远程方法
        System.out.println("result = " + result);
    }
}
