package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.model.UserPause;
import edu.nju.hotel.data.repository.UserPauseRepository;
import edu.nju.hotel.data.repository.UserRepository;
import edu.nju.hotel.logic.service.ontimeTask.TaskService;
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

    @Autowired
    private UserPauseRepository userPauseRepository;

    @Scheduled(cron="0 0 2 * * ? ")   //每天2点执行
    @Override
    public void job1() {

        Date date=getYearBefore();
        List<User> users=userRepository.findAllTobePaused(date,100);
        for (User user:users){
            UserPause p=new UserPause();
            p.setUserid(user.getId());
            userPauseRepository.saveAndFlush(p);

            user.setState(2);
            userRepository.saveAndFlush(user);
        }

        List<User> usersStopped=userRepository.findAllTobeStoped(date);
        for (User u:usersStopped){
            u.setState(3);
            userRepository.saveAndFlush(u);
        }
        System.out.println(date.getHours()+":"+date.getMinutes());
    }

    private Date getYearBefore(){
        Date date=new Date();
        date.setYear(date.getYear()-1);
        return date;
    }
}
