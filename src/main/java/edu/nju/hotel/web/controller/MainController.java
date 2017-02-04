package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dzkan on 2016/3/8.
 */
@Controller
public class MainController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "main/index";
    }



}
