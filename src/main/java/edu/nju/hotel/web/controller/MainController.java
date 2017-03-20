package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.ManagerService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.UserVO;
import edu.nju.hotel.logic.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dzkan on 2016/3/8.
 */
@Controller
public class MainController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserService userService;

    @Autowired
    ManagerService managerService;

    @Autowired
    HotelService hotelService;

    @GetMapping(value = "/")
    public String index(Model model) {
        LoginInfo loginInfo=new LoginInfo();
        model.addAttribute("user",loginInfo);
        return "main/index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        LoginInfo loginInfo=new LoginInfo();
        model.addAttribute("user",loginInfo);
        return "main/index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginInfo loginInfo, Model model, HttpSession session, HttpServletResponse response){
        VerifyResult result = VerifyResult.NOTEXIST;
        String type=loginInfo.getType();
        Cookie cookie=null;
        switch (type){
            case "user":result=userService.verifyLogin(loginInfo.getId(),loginInfo.getPsw());break;
            case "hotel":result=hotelService.verifyLogin(loginInfo.getId(),loginInfo.getPsw());break;
            case "manager":result=managerService.verifyLogin(loginInfo.getName(),loginInfo.getPsw());break;
        }
        System.out.println(result);
        if(result==VerifyResult.SUCCESS){
            cookie = new Cookie("user",loginInfo.getId()+"" );
            cookie.setMaxAge(1000);
            response.addCookie(cookie);
            session.setAttribute(type+"id",loginInfo.getId());
            return "redirect:/"+type+"s/index";
        }
        else if(result==VerifyResult.LOGOFF){
            loginInfo.setErrorMsg("账户已注销");
        }
        else if(result==VerifyResult.INCORRECT){
            loginInfo.setErrorMsg("密码错误");
        }
        else{
            loginInfo.setErrorMsg("账号不存在");
        }
        model.addAttribute(type,loginInfo);
        return "/main/index";

    }

    @GetMapping("/addHotel")
    public String addHotel() {
        return "hotels/register";
    }

    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute Hotel hotel, HttpSession session,Model model) {
        if(testHotel(hotel)){
            HotelVO hotelVO=hotelService.addHotel(hotel);
            session.setAttribute("hotelid",hotelVO.getId());
            model.addAttribute("id",hotelVO.getId());
        }

        return "/hotels/registerSuccess";
    }

    private boolean testHotel(Hotel hotel){
        if(hotel.getName()==null)
            return false;
        if (hotel.getPsw()==null)
            return false;
        return true;
    }

    @GetMapping("/addUser")
    public String addUser() {
        return "users/register";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, HttpSession session, Model model) {
        if(checkUser(user)){
            UserVO userVO=userService.addUser(user);
            session.setAttribute("userid",userVO.getId());
            model.addAttribute("id",userVO.getId());
            return "users/registerSuccess";
        }
        else {
            return "redirect:/addUser";
        }

    }

    private boolean checkUser(User user) {
        if (user.getName()==null){
            return false;
        }
        return true;
    }


}
