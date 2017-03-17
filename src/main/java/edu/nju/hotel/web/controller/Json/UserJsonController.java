package edu.nju.hotel.web.controller.Json;

import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Controller
@RequestMapping("/users/json")
public class UserJsonController {
    @Autowired
    UserService userService;

    @Autowired
    HotelService hotelService;

    @PostMapping("/charge")
    public @ResponseBody
    Object charge(@RequestParam("money")String money,HttpSession session) {
        int id= (int) session.getAttribute("userid");
        String result=userService.chargeCard(id,parseInt(money));
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",result);
        return modelMap;
    }

    @GetMapping("/logOff")
    public @ResponseBody
    Object logOff(HttpServletRequest request,HttpSession session) {
        int id= (int) session.getAttribute("userid");
        String result=userService.logOff(id);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",result);

        return modelMap;
    }

    @PostMapping("/update")
    public @ResponseBody Object edit(@RequestBody UserVO user, HttpSession session){
        user.setId((Integer) session.getAttribute("userid"));
        String result = userService.updateUser(user);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",user);
        return modelMap;
    }

    @PostMapping("/exchangePoints")
    public @ResponseBody Object exchangePoints(@RequestParam("points") int points,
                                                 HttpSession session){

        int userid= (int) session.getAttribute("userid");
        userService.exchangePoints(userid,points);
        ModelMap modelMap=new ModelMap();
        return modelMap;
    }

    @GetMapping("/hotel/getDetail")
    public @ResponseBody Object getDetail(@RequestParam("id") int id){

        ModelMap rooms=hotelService.getRoom(id);
        ModelMap model=new ModelMap();

        model.addAttribute("rooms",rooms);
        return model;
    }

    @GetMapping("/hotel/getSpareRoom")
    public @ResponseBody Object getSpareRoom(@RequestParam("id") int id,
                                             @RequestParam("start") String start,
                                             @RequestParam("end") String end){

        ModelMap rooms=hotelService.getSpareRoom(id,start,end);
        ModelMap model=new ModelMap();

        model.addAttribute("rooms",rooms);
        return model;
    }

    @GetMapping("/getUserInfo")
    public @ResponseBody Object getUserInfo(HttpSession session){
        int userid= (int) session.getAttribute("userid");
        UserVO userVO=userService.getUserById(userid);
        return userVO;
    }
}
