package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.repository.UserRepository;
import edu.nju.hotel.logic.service.ontimeTask.TaskService;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/2/5.
 */
@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron="0 0/1 *  * * ? ")   //每1 min执行一次
    @Override
    public void job1() {

        Date date=getYearBefore();
        userRepository.pause(date,100);
        userRepository.stop(date);

        System.out.println("进入测试");
    }

    private Date getYearBefore(){
        Date date=new Date();
        date.setYear(date.getYear());
        return date;
    }
}
