package edu.nju.hotel.web.controller.Json;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.service.UserService;
import edu.nju.hotel.logic.vo.HotelUpdateVO;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Controller
@RequestMapping("/hotels/json")
public class HotelJsonController {
    @Autowired
    HotelService hotelService;

    @Autowired
    UserService userService;

    @PostMapping("/update")
    public @ResponseBody Object updateHotel(@RequestBody HotelUpdateVO hotelUpdate, HttpSession session){
        hotelUpdate.setHotelId((Integer) session.getAttribute("hotelid"));
        HotelUpdateVO update = hotelService.updateHotel(hotelUpdate);
        ModelMap result=new ModelMap();
        result.addAttribute("result",update);
        return result;
    }

    @GetMapping("/getUpdateList")
    public @ResponseBody Object getUpdate(HttpSession session){
        int hotelid=(Integer) session.getAttribute("hotelid");

        List<HotelUpdateVO> hotelUpdateList=hotelService.getHotelUpdates(hotelid);

        ModelMap result=new ModelMap();
        result.addAttribute("updateList",hotelUpdateList);
        return result;

    }

    @PostMapping("/addRoomType")
    public @ResponseBody Object addRoomType(@RequestParam("roomType") String roomTypeName, HttpSession session){
        int hotelid=(Integer) session.getAttribute("hotelid");
        hotelService.addRoomType(roomTypeName,hotelid);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result","success");
        return modelMap;
    }

    @PostMapping("/addRoom")
    public @ResponseBody Object addRoom(@RequestParam("roomTypeId") int typeid,@RequestParam("name") String roomName, HttpSession session){

        hotelService.addRoom(typeid,roomName);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result","success");
        return modelMap;
    }

    @PostMapping("/addPlan")
    public @ResponseBody Object addRoom(@RequestParam("start") String start,
                                        @RequestParam("end") String end,
                                        @RequestParam("roomTypeId") int typeid,
                                        @RequestParam("price") int price,
                                        HttpSession session){
        int hotelid=(Integer) session.getAttribute("hotelid");
        hotelService.addPlan(hotelid,start,end,typeid,price);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result","success");
        return modelMap;
    }

    @GetMapping("/getUserById")
    public @ResponseBody Object getUserById(@RequestParam("id") int userId){
        UserVO userVO=userService.getUserById(userId);
        ModelMap modelMap=new ModelMap();
        if (userVO==null){
            modelMap.addAttribute("error","用户不存在");
        }

        modelMap.addAttribute("user",userVO);

        return modelMap;
    }
}
