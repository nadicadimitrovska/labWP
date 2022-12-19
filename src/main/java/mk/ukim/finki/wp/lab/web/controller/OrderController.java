package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.OrderService;
//import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    //private final ShoppingCartService shoppingCartService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrderPage(@RequestParam(required = false) String error, Model model,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                  LocalDateTime dateFrom,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                  LocalDateTime dateTo) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Order> orders=null;
        if (dateFrom!=null || dateTo!=null){
            orders=this.orderService.findAllOrdersByDateBetween(dateFrom,dateTo);
        }else {
            orders = this.orderService.listAll();
        }
        model.addAttribute("orders", orders);
        return "userOrders";
    }

    //    @PostMapping("/usersOnly")
//    public String getAllOrders(HttpServletRequest req,Model model){
//        String username= (String) req.getSession().getAttribute("username");
//        String color= (String) req.getSession().getAttribute("color");
//        String size= (String) req.getSession().getAttribute("size");
//        LocalDateTime dateCreated= (LocalDateTime) req.getSession().getAttribute("dateCreated");
//        model.addAttribute("username",username);
//        model.addAttribute("listofOrders",orderService.findByUsername(username));
//        List<Order>orders= (List<Order>) this.orderService.placeOrder(color,size,username,dateCreated);
//        model.addAttribute("orders",orders);
//        return "userOrders";
//    }
    @PostMapping("/placeorder")
    public String placeOrder(@RequestParam
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                             LocalDateTime dateCreated,
                             HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("color");
        String description = (String) request.getSession().getAttribute("size");

        User user = (User) request.getSession().getAttribute("user");
//        Order order = this.orderService.placeOrder(name, description, user.getUsername(), dateCreated);
        Order order = this.orderService.placeOrder(name, description, user.getUsername(), dateCreated);
        //this.shoppingCartService.addOrderToShoppingCart(user.getUsername(), order.getOrderId());
        return "redirect:/ConfirmationInfo";

    }
}
