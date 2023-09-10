package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.UserFullname;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryDB;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceClass implements UserService {

    private final UserRepositoryDB userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceClass(UserRepositoryDB userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
//        if (username==null || username.isEmpty() || password==null || password.isEmpty()){
//            throw new InvalidArgumentException();
//        }
//        if (!password.equals(repeatPassword)){
//            throw new PasswordsDoNotMatchException();
//        }
//        if (this.userRepository.findByUsername(username).isPresent())
//            throw new UsernameAlreadyExistsException(username);
//
//        User user=new User(username,passwordEncoder.encode(password), name, surname,role);
//        return userRepository.save(user);
//
//    }

    @Override
        public User register(String username, String password, String repeatPassword, UserFullname userFullname, LocalDate dateOfBirth, Role role) {
            if (username==null || username.isEmpty() || password==null || password.isEmpty()){
//                throw new InvalidArgumentException();
                throw new InvalidUsernameOrPasswordException();
            }
            if (!password.equals(repeatPassword)){
                throw new PasswordsDoNotMatchException();
            }



            if (this.userRepository.findByUsername(username).isPresent()
                    || !this.userRepository.findByUsername(username).isEmpty())
                throw new UsernameAlreadyExistsException(username);

//            User user=new User(username,name, surname, password,dateOfBirth);
            User user=new User(username,userFullname, passwordEncoder.encode(password),dateOfBirth,role);
            return userRepository.save(user);

        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}