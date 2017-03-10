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
}
