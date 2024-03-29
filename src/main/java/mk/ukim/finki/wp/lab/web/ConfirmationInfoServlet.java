package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Order;
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

@WebServlet(name = "Confirm", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    private final BalloonService balloonService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("clientAgent", req.getHeader("User-Agent"));
        context.setVariable("os", req.getRemoteUser());
        context.setVariable("ipAddress", req.getRemoteAddr());
        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
        //String color = (String) req.getSession().getAttribute("color");
        String color=req.getParameter("color");
        //String color= (String) req.getSession().getAttribute("color");
        req.getSession().setAttribute("color",color);
        String size = (String) req.getSession().getAttribute("size");
        //Order order = orderService.placeOrder(color, size, clientName, clientAddress);//ova go chuvame vo sesija; ova go trgnav
        //String orders=req.getParameter("order");
       // req.getSession().setAttribute("order",order); //ova go trgnav
        //za lab3
//        String username= (String) req.getSession().getAttribute("username");
//        LocalDateTime dateCreated= (LocalDateTime) req.getSession().getAttribute("dateCreated");
//        Order order=orderService.placeOrder(color,size,username,dateCreated);
//        req.getSession().setAttribute("order",order);


    }
}
