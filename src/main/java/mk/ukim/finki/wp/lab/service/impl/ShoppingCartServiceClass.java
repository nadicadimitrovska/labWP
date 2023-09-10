//package mk.ukim.finki.wp.lab.service.impl;
//
//
//import mk.ukim.finki.wp.lab.model.Order;
//import mk.ukim.finki.wp.lab.model.ShoppingCart;
//import mk.ukim.finki.wp.lab.model.User;
//import mk.ukim.finki.wp.lab.model.exceptions.OrderAlreadyInShoppingCartException;
//import mk.ukim.finki.wp.lab.model.exceptions.OrderNotFoundException;
//import mk.ukim.finki.wp.lab.model.exceptions.ShoppingCartNotFoundException;
//import mk.ukim.finki.wp.lab.model.exceptions.UserNotFoundException;
//import mk.ukim.finki.wp.lab.repository.jpa.OrderRepositoryDB;
//import mk.ukim.finki.wp.lab.repository.jpa.ShoppingCartRepositoryDB;
//import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryDB;
//import mk.ukim.finki.wp.lab.service.OrderService;
//import mk.ukim.finki.wp.lab.service.ShoppingCartService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ShoppingCartServiceClass implements ShoppingCartService {
//    private final ShoppingCartRepositoryDB shoppingCartRepository;
//    private final UserRepositoryDB userRepository;
//    //private final OrderService orderService;
//    private final OrderRepositoryDB orderRepository;
//
//    public ShoppingCartServiceClass(ShoppingCartRepositoryDB shoppingCartRepository, UserRepositoryDB userRepository, OrderRepositoryDB orderRepository) {
//        this.shoppingCartRepository = shoppingCartRepository;
//        this.userRepository = userRepository;
//
//        this.orderRepository = orderRepository;
//    }
//
//    @Override
//    public List<Order> listAllOrdersInShoppingCart(Long cartId) {
//        if (!this.shoppingCartRepository.findById(cartId).isPresent())
//            throw new ShoppingCartNotFoundException(cartId);
//        return this.shoppingCartRepository.findById(cartId).get().getOrders();
//    }
//
//    @Override
//    public ShoppingCart getActiveShoppingCart(String username) {
//        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
//
//        return this.shoppingCartRepository.findByUser(user).orElseGet(() -> {
//            ShoppingCart cart = new ShoppingCart(user);
//            return this.shoppingCartRepository.save(cart);
//        });
//    }
//
//    @Override
//    public ShoppingCart addOrderToShoppingCart(String username, Long orderId) {
//        ShoppingCart shoppingCart=this.getActiveShoppingCart(username);
//        //Order order=this.orderService.findById(orderId).orElseThrow(()->new OrderNotFoundException(username));
////        if (shoppingCart.getOrders().stream().filter(i -> i.getOrderId().equals(orderId)).toList().size()>0)
////            throw new OrderAlreadyInShoppingCartException(orderId,username);
//        Order order=this.orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException(username));
//        shoppingCart.getOrders().add(order);
//        return this.shoppingCartRepository.save(shoppingCart);
//
//    }
//}
