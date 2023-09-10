package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.UserFullname;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.OrderService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;




@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {


    private MockMvc mockMvc;

    @Autowired
    BalloonService balloonService;

    @Autowired
    OrderService orderService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    UserService userService;

    private  static Manufacturer m1;//od tuka
    private static boolean dataInitialized=false;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData(){
        if(!dataInitialized){
//            manufacturerService.findAll();
            m1=manufacturerService.save("m1","m1","m1").get();
            manufacturerService.save("m2","m2","m2");

            String user="user";
            String admin="admin";

            userService.register(user,user,user,new UserFullname(), LocalDate.now(), Role.ROLE_USER);
            userService.register(admin,admin,admin,new UserFullname(), LocalDate.now(), Role.ROLE_ADMIN);
            dataInitialized=true;
        }
    }
    @Test
    void contextLoads() {
    }

    //go testirame BalloonController

    @Test
    public void testGetBalloonPage() throws Exception {
        MockHttpServletRequestBuilder balloonRequest= MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(balloonRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent","listBalloons"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }
}
