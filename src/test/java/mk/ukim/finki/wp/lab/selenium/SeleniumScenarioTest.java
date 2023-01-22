package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.OrderService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.Assert;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

//    public WebDriver driver=new HtmlUnitDriver();

    @Autowired
    BalloonService balloonService;

    @Autowired
    OrderService orderService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    UserService userService;

    private HtmlUnitDriver driver;

    private static Balloon b1; //za dop
    private static Balloon b2;//za dop
    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {

            m1 = (Manufacturer) manufacturerService.save("m1","m1","m1").get();
            m2 = (Manufacturer) manufacturerService.save("m2","m2","m2").get();

            b1=balloonService.save("b1","b1",1L).get();
            b1=balloonService.save("b1","b1",2L).get();


            regularUser = userService.register(user,user,user,new UserFullname(), LocalDate.now(), Role.ROLE_USER);
            adminUser= userService.register(admin,admin,admin,new UserFullname(), LocalDate.now(), Role.ROLE_ADMIN);
            dataInitialized=true;



        }
    }

    @Test
    public void testScenario() throws Exception{
        BalloonPage balloonPage=BalloonPage.to(this.driver);
        balloonPage.assertElemts(2,0,0,0,0);//0 kaj cart

        LoginPage loginPage=LoginPage.openLogin(this.driver);
        balloonPage=LoginPage.doLogin(this.driver,loginPage,adminUser.getUsername(),adminUser.getPassword());

        balloonPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        balloonPage.assertElemts(2, 2, 2, 1, 1);//
//
//        balloonPage = AddOrEditBalloon.addBalloon(this.driver, "test", "V",   m2.getName());
//        balloonPage.assertElemts(1, 1, 1, 1, 1);
//
//        balloonPage = AddOrEditBalloon.addBalloon(this.driver, "test1", "L", m2.getName());
//        balloonPage.assertElemts(2, 2, 2, 1, 1);
//
//        balloonPage.getDeleteButtons().get(1).click();
//        balloonPage.assertElemts(1, 1, 1, 1, 1);
//
//        balloonPage = AddOrEditBalloon.editBalloon(this.driver, balloonPage.getEditButtons().get(0), "test1", "L",   m2.getName());
//        balloonPage.assertElemts(1, 1, 1, 1, 1);
//
//        loginPage = LoginPage.logout(this.driver);
//        balloonPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
//        balloonPage.assertElemts(0, 0, 0, 0, 0);// 0 kaj cart
//
//        balloonPage.getCartButtons().get(0).click();
//        Assert.assertEquals("url do not match", "http://localhost:9998/orders", this.driver.getCurrentUrl());

        OrderCartPage cartPage=OrderCartPage.init(this.driver);
        cartPage.assertElemts(0);

        balloonPage=BalloonPage.to(this.driver);
        balloonPage.assertElemts(2,2,2,1,1);



    }

}
