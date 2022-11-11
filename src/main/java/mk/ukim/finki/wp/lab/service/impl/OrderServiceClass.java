package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.exceptions.OrderNotFoundException;
import mk.ukim.finki.wp.lab.repository.OrderRepository;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceClass implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceClass(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String color, String size, String clientName, String address) {
        if (clientName==null || address==null || clientName.isEmpty() || address.isEmpty()){
            throw new OrderNotFoundException();
        }
        Order order=new Order(color,size,clientName,address);
        return this.orderRepository.newOrder(order);
        //return orderRepository.save(color, size, clientName, address);
    }
    @Override
    public List<Order> listAll() {

        return orderRepository.findAllOrders();
    }
//    @Override
//    public Optional<Order> placeOrder(String balloonColor, String size, String clientName, String address) {
//        Optional<Order> order=this.orderRepository.placeOrder(balloonColor,size,clientName,address);
//        return (Optional<Order>) Optional.of(order);
//    }


}
