package edu.nju.hotel.controller;

import edu.nju.hotel.model.Hotel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @RequestMapping("/index")
    public String list(Model model) {
//        审批开店信息列表
        return "managers/index";
    }

    @RequestMapping("/approve")
    public @ResponseBody String approve(Model model) {
//        审批开店信息
        return "approve";
    }

    @RequestMapping("/pay")
    public String payList(Model model) {
//        等待结算列表结算
        return "/managers/payList";
    }

    @PostMapping("/pay")
    public @ResponseBody String payHotel(Model model) {
//        给酒店结算结算
        return "pay";
    }

    @RequestMapping("/users")
    public String userSummary(Model model) {
        //所有会员消费情况
        return "managers/users";
    }

    @RequestMapping("/hotels")
    public String hotelSummary(Model model) {
        //所有酒店入住情况
        return "managers/hotels";
    }

    @RequestMapping("/finance")
    public String financeSummary(Model model) {
        //所有财务统计
        return "managers/finance";
    }
}
