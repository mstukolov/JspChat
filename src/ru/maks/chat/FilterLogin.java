package ru.maks.chat;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

/**
 * Created by Maxim on 18.12.2016.
 */
@WebFilter("/FilterLogin")
public class FilterLogin implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        ManageChat manageChat = new ManageChat();
        ServletContext sc = filterConfig.getServletContext();
        sc.setAttribute("manageChat", manageChat);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("Вход в фильтр");

        String act = config.getInitParameter("active");

        String value = "Simple Filter";
        servletRequest.setAttribute("info", value);
        servletRequest.setAttribute("attr1", act);

        chain.doFilter(servletRequest, servletResponse);
        System.out.println("Окончание фильтрa");
    }

    @Override
    public void destroy() {
        System.out.println("Уничтожение фильтрa");
    }
}
