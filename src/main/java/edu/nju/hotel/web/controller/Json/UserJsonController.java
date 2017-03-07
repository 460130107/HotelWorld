package edu.nju.hotel.web.controller.Json;

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

    @PostMapping("/charge")
    public @ResponseBody
    Object charge(@RequestParam("money")String money,HttpSession session) {
        int id= (int) session.getAttribute("user");
        String result=userService.chargeCard(id,parseInt(money));
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",result);

        return modelMap;
    }

    @GetMapping("/logOff")
    public @ResponseBody
    Object logOff(HttpServletRequest request,HttpSession session) {
        int id= (int) session.getAttribute("user");
        String result=userService.logOff(id);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",result);

        return modelMap;
    }

    @PostMapping("/update")
    public @ResponseBody Object edit(@RequestBody UserVO user, HttpSession session){
        user.setId((Integer) session.getAttribute("user"));
        String result = userService.updateUser(user);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",user);
        return modelMap;
    }
}
