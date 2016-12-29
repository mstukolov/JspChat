package ru.maks.chat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Maxim on 07.12.2016.
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contextPath = "";
    private String userName;
    private String messageText;
    private String time;

    private ManageChat manageChat;

    public void init(){

    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ServletContext sc = getServletContext();
        manageChat = (ManageChat) sc.getAttribute("manageChat");

        contextPath = request.getContextPath();
        userName = (String) request.getSession().getValue("userName");
        messageText = request.getParameter("messageText");
        time = DateAdapter.getFormat().format(new Date());
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
                Person person = manageChat.getActiveUsers().get(userName);

                Message message = new Message(
                        person.getName(),
                        messageText,
                        time,
                        person.getColor()
                );

            manageChat.addMessage(message);

                response.sendRedirect(contextPath + "/chat.jsp");
        }catch (Exception exception){
            System.out.println("Exception thrown in LoginServlet: " + exception.getMessage());
            exception.printStackTrace();
            response.sendRedirect(contextPath + "/error.jsp");
        }

    }


}
