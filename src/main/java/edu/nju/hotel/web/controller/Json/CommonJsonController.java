package edu.nju.hotel.web.controller.Json;

import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Controller
@RequestMapping("/json")
public class CommonJsonController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/hotel/getEmptyRoom")
    public @ResponseBody Object getEmptyRoom(@RequestParam("id") int id,
                                             @RequestParam("start") String start,
                                             @RequestParam("end") String end,
                                             HttpSession session){

        if (id==-1){
            id= (int) session.getAttribute("hotelid");
        }
        ModelMap rooms=hotelService.getEmptyRoom(id,start,end);
        ModelMap model=new ModelMap();

        model.addAttribute("rooms",rooms);
        return model;
    }

}
