package edu.nju.hotel.web.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.Room;
import edu.nju.hotel.data.model.RoomType;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.repository.UserRepository;
import edu.nju.hotel.data.util.ChargeResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    HotelService hotelService;

    @Autowired
    UserService userService;

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userid");
        return "redirect:/";
    }

    @RequestMapping("/index")
    public String hotelList(Model model){
        List<HotelVO> hotelList=hotelService.getHotelList();
        model.addAttribute("hotelList",hotelList);
        return "users/hotelList";
    }

    @GetMapping("/hotel/{id}")
    public String hotel(@PathVariable int id,Model model){
        HotelVO hotel=hotelService.getHotelById(id);
        model.addAttribute("hotel",hotel);
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

    @RequestMapping("/bookHistory")
    public String bookHistory(){
        return "users/bookHistory";
    }

    @RequestMapping("/checkinHistory")
    public String checkinHistory(){
        return "users/checkinHistory";
    }



    @GetMapping("/account")
    public String account(Model model,HttpSession session) {
        int uid= (int) session.getAttribute("userid");
        UserVO user=userService.getUserById(uid);
        model.addAttribute("userInfo",user);
        return "users/account";
    }

    @RequestMapping("/json")
    public @ResponseBody Object json() {
        List<User> list = new ArrayList<User>();
        User u=new User();
        list.add(u);
//        List<User> l2=userDao.findAll();
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("score","ss");
        modelMap.addAttribute("list",list);
//        modelMap.addAttribute("l2",l2);
        return modelMap;
    }
}
