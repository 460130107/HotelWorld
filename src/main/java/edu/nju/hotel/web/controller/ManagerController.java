package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.CheckinVO;
import edu.nju.hotel.logic.vo.HotelBillVO;
import edu.nju.hotel.logic.vo.HotelUpdateVO;
import edu.nju.hotel.logic.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

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

    @GetMapping("bills")
    public String getHotelBill(Model model){
        List<CheckinVO> billList=hotelService.getUnpayedBills();
        model.addAttribute("checkinList",billList);
        return "/managers/hotelBill";
    }

    @PostMapping("/payUp")
    public @ResponseBody Object payHotel(@RequestParam("id") int checkinId,Model model) {
//        给客栈结算
        HotelVO hotel= hotelService.payHotel(checkinId);
        ModelMap result=new ModelMap();
        if (hotel==null){
            result.addAttribute("error","银行卡不存在");
        }
        else {
            result.addAttribute("hotel",hotel);
        }
        return result;
    }

    @RequestMapping("/users")
    public String userSummary(Model model) {
        //所有会员预订消费情况
        int bookNum=userService.getBookSumByTime();
        int money=userService.getConsumeSum();

        model.addAttribute("bookNum",bookNum);
        model.addAttribute("consumption",money);
        return "managers/users";
    }

    @RequestMapping("/hotels")
    public String hotelSummary(Model model) {
        //所有客栈入住情况
        return "managers/hotels";
    }

    @GetMapping("/getHotelStat")
    public @ResponseBody Object getHotelStat(){

        ArrayList<ArrayList> list=hotelService.getHotelStats();
        return list;
    }

    @GetMapping("/finance")
    public String financeSummary(Model model) {
        //所有财务统计
        return "managers/finance";
    }

    @GetMapping("/getFinanc")
    public @ResponseBody Object getFinanc(){
        ArrayList<ArrayList> lists=hotelService.getEarningEachDay();
        return lists;
    }

}
