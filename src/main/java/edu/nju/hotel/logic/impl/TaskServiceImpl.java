package edu.nju.hotel.logic.impl;

import edu.nju.hotel.logic.service.ontimeTask.TaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
@Component
public class TaskServiceImpl implements TaskService {
    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    @Override
    public void job1() {
        System.out.println("进入测试");
    }
}
