package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public  static List<Balloon>balloons=new ArrayList<>();
    public static List<Order>orders=new ArrayList<>();
    public static List<Manufacturer>manufacturers=new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        Manufacturer manufacturer1=new Manufacturer("Magija GTC", "MKD","Centar");
//        Manufacturer manufacturer2=new Manufacturer("Magija Jumbo", "MKD","Aerodrom");
//        manufacturers.add(manufacturer1);
//        manufacturers.add(manufacturer2);
//        balloons.add(new Balloon("Red","From Latex material",manufacturer1));
//        balloons.add(new Balloon("White","From Vinyl material",manufacturer2));
//        balloons.add(new Balloon("Blue","From Vinyl material",manufacturer2));
//        balloons.add(new Balloon("Green","From Vinyl material",manufacturer1));
//        balloons.add(new Balloon("Yellow","From Vinyl material",manufacturer2));
//
//
//        manufacturers.add(new Manufacturer("Magija GTC", "MKD","Centar"));
//        manufacturers.add(new Manufacturer("Magija Jumbo", "MKD","Aerodrom"));
//        manufacturers.add(new Manufacturer("Magija East-Gate", "MKD","Gazi Baba"));
//        manufacturers.add(new Manufacturer("Magija Ramstore", "MKD","Centar"));
//        manufacturers.add(new Manufacturer("Magija Capitol", "MKD","Aerodrom"));
//
//        orders=new ArrayList<>();
//    }
}
