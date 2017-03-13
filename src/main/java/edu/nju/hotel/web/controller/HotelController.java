package edu.nju.hotel.web.controller;

import edu.nju.hotel.data.model.Hotel;
import edu.nju.hotel.logic.service.HotelService;
import edu.nju.hotel.logic.vo.HotelVO;
import edu.nju.hotel.logic.vo.PlanVO;
import edu.nju.hotel.logic.vo.RoomTypeVO;
import edu.nju.hotel.logic.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxiaofan on 2017/1/26.
 */
@Controller
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("hotel");
        return "redirect:/";
    }

    @GetMapping("/account")
    public String account(Model model,HttpSession session) {
        int id= (int) session.getAttribute("hotelid");
        HotelVO h=hotelService.getHotelById(id);
        model.addAttribute("hotelInfo",h);
        return "hotels/account";
    }

    @RequestMapping("/index")
    public String bookList(Model model) {
        return "hotels/bookList";
    }

    @GetMapping("/checkout")
    public String checkoutList(Model model) {
        return "hotels/checkout";
    }

    @GetMapping("/checkin")
    public String checkinPage(){
        return "hotels/checkin";
    }

    @PostMapping("/checkin")
    public @ResponseBody String checkin(HttpServletRequest request){
        return "success";
    }

    @RequestMapping("/checkout")
    public @ResponseBody String checkout(){
        return "success";
    }

    @GetMapping("/plan")
    public String plan(Model model,HttpSession session) {
        int hotelid=(Integer) session.getAttribute("hotelid");
        List<RoomTypeVO> roomTypeList=hotelService.getRoomType(hotelid);
        List<PlanVO> planList=hotelService.getPlan(hotelid);
        model.addAttribute("roomTypes",roomTypeList);
        model.addAttribute("planList",planList);

        return "hotels/plan";
    }

    @GetMapping("/getRoom")
    public @ResponseBody Object getRoom(HttpSession session) {
        ModelMap model=new ModelMap();
        int hotelid=(Integer) session.getAttribute("hotelid");

        ModelMap roomList=hotelService.getRoom(hotelid);

        model.addAttribute("roomList",roomList);

        return roomList;

    }


    @RequestMapping("/history")
    public String history(Model model) {
        //所有历史记录
        return "hotels/history";
    }
    @RequestMapping("/summary")
    public String summary(Model model) {
        //所有历史记录
        return "hotels/summary";
    }
}
