package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.Hotel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/hotels")
public class HotelController {
    @PostMapping("/login")
    public String hotelLogin(){
        return "redirect:/hotels/index";
    }

    @RequestMapping("/add")
    public String addHotel() {
        return "hotels/register";
    }

    @PostMapping("/edit")
    public String edit(){
        return "redirect:/hotels/account";
    }

    @RequestMapping("/account")
    public String show(Model model) {
        Hotel u=new Hotel();
        model.addAttribute("hotelInfo",u);
        return "hotels/info";
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
