package mk.ukim.finki.wp.lab.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class BalloonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //String color= (String) request.getSession().getAttribute("color");
        String color= (String) request.getSession().getAttribute("color");
        //String submit=request.getParameter("submit");


        String path=request.getServletPath();

        if ( !("").equals(path) && color == null ) {
            response.sendRedirect("/");
        } else {
            //if (color==null)
            filterChain.doFilter(servletRequest, servletResponse);

        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
