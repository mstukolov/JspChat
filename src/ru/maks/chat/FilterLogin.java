package ru.maks.chat;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by Maxim on 18.12.2016.
 */
public class FilterLogin implements Filter {

    private FilterConfig config = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        if (active)
        {
            System.out.println(active);
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        config = null;
    }
}
