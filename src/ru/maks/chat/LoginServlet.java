package ru.maks.chat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Maxim on 07.12.2016.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String contextPath = "";
    private String userName;
    private String color;
    private ManageChat manageChat;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(){
        manageChat = new ManageChat();
    }
     /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext sc = getServletContext();
        System.out.println ("Email = " + sc.getInitParameter("email"));

        /*sc.setAttribute("manageChat", manageChat);*/
        manageChat = (ManageChat) sc.getAttribute("manageChat");

        process(request, response);
        HttpSession session = request.getSession();

        try {

                session.setAttribute("color", color);
                session.setAttribute("userName", userName);

                checkSessionTimeout(session);

                manageChat.addPerson(userName, color);
                response.sendRedirect(request.getContextPath() + "/chat.jsp");

        }catch (Exception exception)
            {
                System.out.println("Exception thrown in LoginServlet: " + exception.getMessage());
                exception.printStackTrace();
                response.sendRedirect(contextPath + "/error.jsp");
            }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

        try {
            manageChat.exitPerson(userName);
            response.sendRedirect(contextPath + "/index.jsp");
        }catch (Exception exception) {
            System.out.println("Exception thrown in LoginServlet: " + exception.getMessage());
            exception.printStackTrace();
            response.sendRedirect(contextPath + "/error.jsp");
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        contextPath = request.getContextPath();
        userName = request.getParameter("userName");
        color = request.getParameter("color");

    }

    public void checkSessionTimeout(HttpSession session){
        int timeout = 180;
        String t = getServletContext().getInitParameter("sessionTimeout");
        if (t != null)
        {
            try
            {
                timeout = Integer.parseInt(t);
                timeout = timeout * 60;
            }
            catch (NumberFormatException nfe)
            {
            }
        }
        session.setMaxInactiveInterval(timeout);
    }

    public ManageChat getManageChat() {
        return manageChat;
    }
}
