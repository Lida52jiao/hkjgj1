package com.yj.bj.task;

import com.yj.bj.service.CountT1Service;
import com.yj.bj.service.TransactionalService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/***
 * 
 * Quartz设置项目全局的定时任务
 * 
 * @Component注解的意义        泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。一般公共的方法我会用上这个注解
 * 
 * 
 * @author WQ
 *@Scheduled(fixedRate = 5000)//每5秒执行一次
 */
@Component
public class QuartzDemo {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuartzDemo.class);
    @Autowired
    private TransactionalService transactionalService;
    @Autowired
    private CountT1Service countT1Service;

    @Scheduled(cron = "0 30 2 ? * *")
    //@Scheduled(cron = "0 */1 * * * ?")
    public void t1() throws Exception {
        logger.info("T1报表");
        transactionalService.t1();
    }
}