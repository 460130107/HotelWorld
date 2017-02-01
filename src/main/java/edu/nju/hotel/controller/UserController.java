package edu.nju.hotel.controller;

import edu.nju.hotel.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @RequestMapping("/add")
    public String addUser() {
        return "users/register";
    }

    @PostMapping("/add")
    public String handleAdd() {
        return "redirect:/users/hotelList";
    }

    @RequestMapping("/activate")
    public String activate() {
        return "users/activate";
    }

    @RequestMapping("/hotelList")
    public String hotelList(){
        return "users/hotelList";
    }

    @RequestMapping("/hotel/{id}")
    public String hotel(@PathVariable int id){
        return "users/hotel";
    }

    @PostMapping("/booking")
    public String booking(HttpServletRequest request){
        String start= (String) request.getParameter("start");
        return "users/booking";
    }

    @PostMapping("/submitbooking")
    public String handleBooking(){
        return "redirect:/users/success";
    }

    @RequestMapping("/success")
    public String success(){
        return "users/success";
    }

    @RequestMapping("/history")
    public String bookHistory(){
        return "users/bookHistory";
    }

    @PostMapping("/edit")
    public String edit(){
        return "redirect:/users/account";
    }

    @RequestMapping("/account")
    public String show(Model model) {
        User u=new User();
        u.setNickname("ss");
        u.setFirstName("zjou");
        u.setLastName("xioa");
        u.setId(12);
        model.addAttribute("userInfo",u);
        return "users/info";
    }

    @RequestMapping("/json")
    public @ResponseBody Object json() {
        List<User> list = new ArrayList<User>();
        User u=new User();
        u.setNickname("ss");
        u.setFirstName("zjou");
        u.setLastName("xioa");
        list.add(u);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("score","ss");
        modelMap.addAttribute("list",list);
        return modelMap;
    }
}
