package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public  static List<Balloon>balloons=new ArrayList<>(10);
    public static List<Order>orders=new ArrayList<>();

    @PostConstruct
    public void init(){
        this.balloons.add(new Balloon("Red","From Latex material"));
        this.balloons.add(new Balloon("White","From Vinyl material"));
    }
}
