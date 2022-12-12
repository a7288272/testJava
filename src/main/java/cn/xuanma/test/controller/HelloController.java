package cn.xuanma.test.controller;

import cn.xuanma.test.utils.OkHttp3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

import static jodd.util.ThreadUtil.sleep;


/**
 * @Author:wangshu'an
 * @date:2022/9/3 11:37
 * @Description:
 */

@RestController
@Slf4j
@RefreshScope
public class HelloController {
    private final String baiduUrl = "http://www.baidu.com";

    @Value(value="${hello.hello}")
    private String hello;

    @Value(value="${spring.cloud.sentinel.datasource.ds1.nacos.server-addr}")
    private String sentinel;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    @Qualifier("searchAsync")
    ExecutorService executor;


    @RequestMapping("/test")
    public String sayHello(){
        log.error("error");
        log.warn("warn");
        log.info("inf");
        log.debug("debug");
        log.trace("trace");


        return "index7";
    }

    @RequestMapping("/hello")
    public String get(){
//        return hello;
//        return sentinel;
        executor.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                System.out.println(Thread.activeCount());
                sleep(30*1000);
            }
        });
        return "hello";
    }


    @RequestMapping("/okHttp")
    public Object okHttp(){
        return OkHttp3Util.syncGet(baiduUrl);
    }


}
