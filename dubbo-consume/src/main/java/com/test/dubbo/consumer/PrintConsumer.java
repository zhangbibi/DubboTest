package com.test.dubbo.consumer;

import com.test.dubbo.api.CallbackListener;
import com.test.dubbo.api.CallbackService;
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


        CallbackService callbackService = (CallbackService) context.getBean("callbackService");

        callbackService.addListener("http://10.20.160.198/wiki/display/dubbo/foo.bar", new CallbackListener(){
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });

    }
}
