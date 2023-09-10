package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
//@RequestMapping("/balloons")
@RequestMapping(value ={"/","/balloons"})
//localhost:9091/balloons
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Balloon>balloons=this.balloonService.listAll();
        model.addAttribute("balloons", balloons);
        model.addAttribute("bodyContent","listBalloons");
        return "master-template";
//        return "listBalloons";
    }

    @PostMapping("/save")
    public String saveBalloonColor(HttpServletRequest req,Model model){
        String color=req.getParameter("color");
        req.getSession().setAttribute("color",color);
//        return "redirect:/selectBalloon";
        model.addAttribute("bodyContent"," selectBalloonSize");
        return "master-template";
        //return " selectBalloonSize";

    }

    //@PostMapping("/add-form")
    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model){
        //List<Balloon>balloons=this.balloonService.listAll();
        List<Manufacturer>manufacturers=this.manufacturerService.findAll();
        //model.addAttribute("balloons",balloons);
        model.addAttribute("manufacturers",manufacturers);
        return "add-balloon";
    }



    @GetMapping("/edit-form/{id}")
    public String editBalloonPage(@PathVariable Long id, Model model){
        if (this.balloonService.findById(id).isPresent()){
            Balloon balloon=this.balloonService.findById(id).get();

            List<Manufacturer> manufacturers=this.manufacturerService.findAll();
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("balloon",balloon);
            return "add-balloon";

        }
        return "redirect:/balloons?error=ProductNotFound";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
        return "redirect:/balloons";

    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,@RequestParam String color, @RequestParam String desc, @RequestParam Long manufacturer){
        //this.balloonService.save(color,desc,manufacturer);
        if (id!=null){
            this.balloonService.edit(id, color, desc, manufacturer);
        }else {
            this.balloonService.save(color,desc,manufacturer);
        }

        return "redirect:/balloons";
    }
//    @GetMapping("/orders")
//    public String order(Model model){
////        List<Order>orders=this.orderService.listAll();
////        model.addAttribute("orders",orders);
////        return "userOrders";
//        model.addAttribute("bodyContent","userOrders");
////        return "redirect:/userOrders";
//        return "master-template";
//    }

    @PostMapping("/save/size")
    public String saveSize(HttpServletRequest request,Model model){
        String size=request.getParameter("size");
        request.getSession().setAttribute("size",size);
        model.addAttribute("bodyContent","deliveryInfo");
        return "master-template";
//        return "deliveryInfo";
    }

}


