package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String address;

    public Manufacturer(String name, String country, String address) {
        //this.id=(long)(Math.random()*1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Manufacturer() {

    }
}
