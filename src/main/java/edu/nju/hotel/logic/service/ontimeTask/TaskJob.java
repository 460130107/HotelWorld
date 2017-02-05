package edu.nju.hotel.logic.service.ontimeTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
@Component
public class TaskJob {

    @Scheduled(cron = "0 0 3 * * ?")
    public void job1() {
        System.out.println("任务进行中。。。");
    }
}
