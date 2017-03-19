package edu.nju.hotel.web.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hotel.data.dao.UserDao;
import edu.nju.hotel.data.model.*;
import edu.nju.hotel.data.repository.UserRepository;
import edu.nju.hotel.data.util.ChargeResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.*;
import org.hibernate.Session;
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



    @GetMapping("/booking")
    public String booking(@ModelAttribute BookTypeVO bookTypeVO,
                          Model model,
                          HttpSession session){
        int userid= (int) session.getAttribute("userid");
        UserVO userVO=userService.getUserById(userid);
        model.addAttribute("bookType",bookTypeVO);
        model.addAttribute("user",userVO);
        return "users/booking";
    }

    @PostMapping("/submitbooking")
    public String handleBooking(@ModelAttribute BookingVO bookingVO, HttpSession session){
        int userid= (int) session.getAttribute("userid");
        bookingVO.setUserId(userid);

//        hotelService.bookHotel(bookingVO);
        return "redirect:/users/success";
    }

    @RequestMapping("/success")
    public String success(){
        return "users/success";
    }

    @RequestMapping("/bookHistory")
    public String bookHistory(Model model,HttpSession session){
        int userId= (int) session.getAttribute("userid");
        List<BookingVO> bookingList=userService.getBookingHistory(userId);
        model.addAttribute("bookingList",bookingList);
        return "users/bookHistory";
    }

    @GetMapping("/checkinHistory")
    public String checkinHistory(HttpSession session,Model model){
        int userId= (int) session.getAttribute("userid");
        List<CheckinVO> checkinVOS=userService.getCheckinHistory(userId);
        model.addAttribute("checkinList",checkinVOS);
        return "users/checkinHistory";
    }

    @PostMapping("/cancelBooking")
    public @ResponseBody String cancelBooking(@RequestParam("id") int id){
        userService.cancelBooking(id);
        ModelMap map=new ModelMap();
        return "success";
    }

    @GetMapping("/account")
    public String account(Model model,HttpSession session) {
        int uid= (int) session.getAttribute("userid");
        UserVO user=userService.getUserById(uid);
        model.addAttribute("userInfo",user);
        return "users/account";
    }

    @GetMapping("/summary")
    public String getSummary(HttpSession session,Model model){
        int uid= (int) session.getAttribute("userid");
        int bookSum=userService.getBookSumByTimeByUser(uid);
        int checkinSum=userService.getCheckinSumByTimeByUser(uid);
        int consumption=userService.getConsumptionByTimeByUser(uid);
        model.addAttribute("bookSum",bookSum);
        model.addAttribute("checkinSum",checkinSum);
        model.addAttribute("consumption",consumption);
        return "users/summary";
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
