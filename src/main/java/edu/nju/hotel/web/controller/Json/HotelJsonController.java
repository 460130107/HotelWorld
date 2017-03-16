package edu.nju.hotel.web.controller.Json;

import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Controller
@RequestMapping("/hotels/json")
public class HotelJsonController {
    @Autowired
    HotelService hotelService;

    @PostMapping("/update")
    public @ResponseBody Object edit(@RequestBody HotelVO hotel, HttpSession session){
        hotel.setId((Integer) session.getAttribute("hotelid"));
        String result = hotelService.updateHotel(hotel);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("result",hotel);
        return modelMap;
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
}
