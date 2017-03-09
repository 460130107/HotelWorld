package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("hotel");
        return "redirect:/";
    }

    @GetMapping("/account")
    public String account(Model model,HttpSession session) {
        int id= (int) session.getAttribute("hotel");
        HotelVO h=hotelService.getHotelById(id);
        model.addAttribute("hotelInfo",h);
        return "hotels/account";
    }

    @RequestMapping("/index")
    public String bookList(Model model) {
        return "hotels/bookList";
    }

    @GetMapping("/checkout")
    public String checkoutList(Model model) {
        return "hotels/checkout";
    }

    @GetMapping("/checkin")
    public String checkinPage(){
        return "hotels/checkin";
    }

    @PostMapping("/checkin")
    public @ResponseBody String checkin(HttpServletRequest request){
        return "success";
    }

    @RequestMapping("/checkout")
    public @ResponseBody String checkout(){
        return "success";
    }

    @RequestMapping("/plan")
    public String plan(Model model) {
        //所有计划
        return "hotels/plan";
    }

    @PostMapping("/addPlan")
    public void addPlan(Model model) {
        //数据库中添加计划
    }


    @RequestMapping("/history")
    public String history(Model model) {
        //所有历史记录
        return "hotels/history";
    }
    @RequestMapping("/summary")
    public String summary(Model model) {
        //所有历史记录
        return "hotels/summary";
    }
}
