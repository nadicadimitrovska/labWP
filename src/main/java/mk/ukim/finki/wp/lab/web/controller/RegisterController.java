package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.UserFullname;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.service.AuthService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        //return "register";
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

//    @PostMapping
//    public String register(@RequestParam String username, @RequestParam  String password, @RequestParam  String repeatedPassword, @RequestParam  String name, @RequestParam  String surname, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dateOfBirth){
//        try {
//            UserFullname userFullname=new UserFullname();
//            userFullname.setName(name);
//            userFullname.setSurname(surname);
//            //this.authService.register(username,password,repeatedPassword,  userFullname,dateOfBirth);
//            return "redirect:/login";
//        }catch (PasswordsDoNotMatchException | InvalidArgumentException exception){
//            return "redirect:/register?error=" + exception.getMessage();
//        }
//    }
    @PostMapping
    public String register(@RequestParam String username, @RequestParam  String password, @RequestParam  String repeatedPassword, @RequestParam  String name, @RequestParam  String surname, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dateOfBirth, @RequestParam Role role) {
        try {
            UserFullname userFullname = new UserFullname();
            userFullname.setName(name);
            userFullname.setSurname(surname);
            this.userService.register(username, password, repeatedPassword, userFullname, dateOfBirth, role);

            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentException exception) {
            return "redirect:/register?error=" + exception.getMessage();


        }
    }
}