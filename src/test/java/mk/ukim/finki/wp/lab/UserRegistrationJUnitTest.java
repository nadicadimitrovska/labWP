package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.UserFullname;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryDB;
import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.impl.UserServiceClass;
import org.h2.api.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationJUnitTest {

    @Mock
    private UserRepositoryDB userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;

    @Before
    public void init(){

        UserFullname userFullname=new UserFullname();
        LocalDate bday=LocalDate.now();
        MockitoAnnotations.initMocks(this);
        User user=new User("username",userFullname,"password",bday, Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");


        this.service = Mockito.spy(new UserServiceClass(this.userRepository, this.passwordEncoder));


    }

    @Test
    public void testSuccessRegister(){
        User user=this.service.register("username","password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);
        Mockito.verify(this.service).register("username","password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name do not mach", new UserFullname(), user.getUserFullname());
        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());
        Assert.assertEquals("date do not mach", LocalDate.now(), user.getDateOfBirth());

    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null,"password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, "password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password,"password",new UserFullname(),LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password,"password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password,"password",new UserFullname(),LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password,"password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword, new UserFullname(),LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword, new UserFullname(),LocalDate.now(),Role.ROLE_USER);
    }

    @Test
    public void testDuplicateUsername() {
        User user = new User("username",new UserFullname(),"password",LocalDate.now(),Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password","password",new UserFullname(),LocalDate.now(),Role.ROLE_USER);
    }



}
