package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.UserFullname;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface AuthService {
    User login(String username, String password);


//    User register(String username, String password, String repeatPassword,
//                  UserFullname userFullname, LocalDate dateOfBirth);
}
