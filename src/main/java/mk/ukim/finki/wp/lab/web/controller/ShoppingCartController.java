//package mk.ukim.finki.wp.lab.web.controller;
//
//
//import mk.ukim.finki.wp.lab.model.ShoppingCart;
//import mk.ukim.finki.wp.lab.model.User;
//import mk.ukim.finki.wp.lab.service.ShoppingCartService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("/shopping-cart")
//public class ShoppingCartController {
//
//    private final ShoppingCartService shoppingCartService;
//
//    public ShoppingCartController(ShoppingCartService shoppingCartService) {
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    @GetMapping
//    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){
//        if (error!=null && !error.isEmpty()){
//            model.addAttribute("hasError",true);
//            model.addAttribute("error",error);
//        }
//        User user= (User) req.getSession().getAttribute("user");
//        ShoppingCart shoppingCart=this.shoppingCartService.getActiveShoppingCart(user.getUsername());
//        model.addAttribute("products",this.shoppingCartService.listAllOrdersInShoppingCart(shoppingCart.getId()));
//        return "shopping-cart";
//    }
//
//    @PostMapping("/add-product/{id}")
//    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req){
//        try {
//            User user= (User) req.getSession().getAttribute("user");
//            ShoppingCart shoppingCart=this.shoppingCartService.addOrderToShoppingCart(user.getUsername(),id);
//            return "redirect:/shopping-cart";
//        }catch (RuntimeException exception){
//            return "redirect:/shopping-cart?error=" + exception.getMessage();
//        }
//
//    }
//
//}
