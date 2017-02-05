package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.data.model.User;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.UserVO;
import edu.nju.hotel.logic.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by dzkan on 2016/3/8.
 */
@Controller
public class MainController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        LoginInfo loginInfo=new LoginInfo();
        model.addAttribute("user",loginInfo);
        return "main/index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginInfo loginInfo, Model model, HttpSession session){
        VerifyResult result = VerifyResult.NOTEXIST;
        String type=loginInfo.getType();
        switch (type){
            case "user":result=userService.verifyLogin(loginInfo.getId(),loginInfo.getPsw());
            case "hotel":result=userService.verifyLogin(loginInfo.getId(),loginInfo.getPsw());
            case "manager":result=userService.verifyLogin(loginInfo.getId(),loginInfo.getPsw());
        }
        System.out.println(result);
        if(result==VerifyResult.SUCCESS){
            session.setAttribute(type,loginInfo.getId());
            return "redirect:/"+type+"s/index";
        }
        if(result==VerifyResult.INCORRECT){
            loginInfo.setError("密码错误");
        }
        else{
            loginInfo.setError("账号不存在");
        }
        model.addAttribute(type,loginInfo);
        return "/main/index";

    }

    @RequestMapping("/addHotel")
    public String addHotel() {
        return "hotels/register";
    }

    @RequestMapping("/addUser")
    public String addUser() {
        return "users/register";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user,HttpSession session) {
        UserVO userVO=userService.addUser(user);
        session.setAttribute("user",userVO.getId());
        return "redirect:/users/index";
    }

    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute Hotel hotel, HttpSession session) {
//        UserVO userVO=userService.addUser(hotel);
//        session.setAttribute("user",userVO.getId());
        return "redirect:/users/index";
    }

    @PostMapping("/add")
    public String handleAdd() {
        return "redirect:/users/hotelList";
    }


}
