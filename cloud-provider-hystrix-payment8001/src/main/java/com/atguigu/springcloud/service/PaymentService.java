package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "     paymentInfo_OK,id:    " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")  //3秒钟以内就是正常的业务逻辑
    })
    public String paymentInfo_TimeOut(Integer id) {
        int age = 10 / 0;
/*        try {
            TimeUnit.MILLISECONDS.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "线程池：" + Thread.currentThread().getName() + "    id:    " + id + "\t" + "O(∩_∩)O哈哈~" + "    耗时(秒):";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "     8001系统繁忙或者运行报错，请稍后再试,id:    " + id + "\t" + "o(╥﹏╥)o";
    }
}
