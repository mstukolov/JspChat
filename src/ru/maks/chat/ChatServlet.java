package ru.maks.chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

        try {
                Person person = ManageChat.getInstance().getActiveUsers().get(userName);

                Message message = new Message(
                        person.getName(),
                        messageText,
                        time,
                        person.getColor()
                );

                ManageChat.getInstance().addMessage(message);

                response.sendRedirect(contextPath + "/chat.jsp");
        }catch (Exception exception){
            System.out.println("Exception thrown in LoginServlet: " + exception.getMessage());
            exception.printStackTrace();
            response.sendRedirect(contextPath + "/error.jsp");
        }

    }

    @SuppressWarnings("deprecation")
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        contextPath = request.getContextPath();
        userName = (String) request.getSession().getValue("userName");
        messageText = request.getParameter("messageText");
        time = DateAdapter.getFormat().format(new Date());
    }
}
