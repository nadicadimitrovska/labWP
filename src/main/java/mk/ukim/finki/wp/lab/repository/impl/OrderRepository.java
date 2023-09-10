package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    public List<Order>findAllOrders(){
        return DataHolder.orders;
    }

//    public Order save(String color,String size, String clientName, String address) {
//
//        Order orders = new Order(color,size,clientName,address);
//        DataHolder.orders.removeIf(i -> i.getOrderId().equals(orders.getOrderId()));
//        DataHolder.orders.add(orders);
//        return orders;
//    }
    public Order newOrder(Order order){
        DataHolder.orders.add(order);
        return order;
    }

//    public Order placeOrder(String balloonColor, String size, String clientName, String address){
//        return Optional.ofNullable(DataHolder.orders.stream().filter(i -> i.getBalloonColor().equals(balloonColor) && i.getBalloonSize().equals(size) && i.getClientName().equals(clientName) && i.getClientAddress().equals(address)).findFirst().orElse(null));
//        Order order=new Order(balloonColor,size,clientName,address);
//        DataHolder.orders.add(order);
//        return Optional.of(order);
//    }
}
