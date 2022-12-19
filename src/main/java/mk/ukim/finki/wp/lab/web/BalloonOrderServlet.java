package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "BalloonOrder", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    //    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, BalloonService balloonService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.orderService = orderService;
//        this.balloonService=balloonService;
//    }
    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context= new WebContext(req,resp, req.getServletContext());
        this.springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName=  req.getParameter("clientName");
        String color= (String) req.getSession().getAttribute("color");
        String size= (String) req.getSession().getAttribute("size");
        String clientAddress =req.getParameter("clientAddress");
        //String username = (String) req.getSession().getAttribute("username");
        User user = (User) req.getSession().getAttribute("user");
        String dateCreated = (String) req.getSession().getAttribute("dateCreated");
        //Order order=new Order(color,size,clientName,clientAddress);
        //Order order=orderService.placeOrder(color,size,clientName,clientAddress);//lab2

        Order order=orderService.placeOrder(color,size,user.getUsername(), LocalDateTime.parse(dateCreated));
        req.getSession().setAttribute("order",order);
        req.getSession().setAttribute("ipAddress",req.getRemoteAddr());
        req.getSession().setAttribute("clientAgent",req.getHeader("User-Agent"));
        resp.sendRedirect("/ConfirmationInfo");
//        try {
//            order=orderService.placeOrder(color,name,adress);
//        }catch (InvalidParameterException ex){

//            context.setVariable("hasError",true);
//            context.setVariable("error",ex.getMessage());

       // }

    }
}
