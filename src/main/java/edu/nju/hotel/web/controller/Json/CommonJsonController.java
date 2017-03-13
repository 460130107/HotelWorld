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

}
