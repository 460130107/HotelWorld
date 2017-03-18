package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.HotelUpdate;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.vo.HotelUpdateVO;
import edu.nju.hotel.logic.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    public HotelService hotelService;

    @PostMapping("/login")
    public String mangLogin(){
        return "redirect:/managers/index";
    }

    @RequestMapping("/index")
    public String getHotelsAndUpdates(Model model) {
        List<HotelVO> hotelList=hotelService.getUnApprovedHotels();
        List<HotelUpdateVO> hotelupdateList=hotelService.getUnApprovedHotelUpdates();
        model.addAttribute("hotels",hotelList);
        model.addAttribute("updates",hotelupdateList);
        return "managers/hotelApprove";
    }

    @PostMapping("/approveUpdate")
    public @ResponseBody Object approveUpdate(@RequestParam("id") int updateId){
        hotelService.approveUpdate(updateId);
        return null;
    }

    @PostMapping("/disapproveUpdate")
    public @ResponseBody Object disapproveUpdate(@RequestParam("id") int updateId){

        hotelService.disapproveUpdate(updateId);
        return null;
    }

    @PostMapping("/approveHotel")
    public @ResponseBody Object approveHotel(@RequestParam("id") int hotelId){

        hotelService.approveHotel(hotelId);
        return null;
    }

    @PostMapping("/disapproveHotel")
    public @ResponseBody Object disapproveHotel(@RequestParam("id") int hotelId){
        hotelService.disapproveHotel(hotelId);
        return null;
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
