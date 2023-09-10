package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "balloon_oders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String balloonColor;
    private String balloonSize;
    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    //private String clientName;
    //private String clientAddress;


    public Order(String balloonColor, String balloonSize,User user,LocalDateTime dateCreated) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user=user;
        this.dateCreated=dateCreated;
    }

    public Order() {

    }
    public Order(User user){
        this.user=user;
    }
//    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress, Long orderId) {
//        this.balloonColor = balloonColor;
//        this.balloonSize = balloonSize;
//        this.clientName = clientName;
//        this.clientAddress = clientAddress;
//        this.orderId = orderId;
//    }

//public Order(String balloonColor, String balloonSize, Long orderId) {
//    this.balloonColor = balloonColor;
//    this.balloonSize = balloonSize;
//    this.orderId = orderId;
//}

//    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress) {
//        this.balloonColor = balloonColor;
//        this.balloonSize = balloonSize;
//        this.clientName = clientName;
//        this.clientAddress = clientAddress;
//    }


}
