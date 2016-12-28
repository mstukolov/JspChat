<%@ page import="java.util.Map" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="ru.maks.chat.*" %>
<%--
  User: Maxim
  Date: 07.12.2016
--%>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Chat Page</title>
    <style type="text/css">
    .div-chat-window {
      height: 400px; /* высота нашего блока */
      width: 400px; /* ширина нашего блока */
      background: #fff; /* цвет фона, белый */
      border: 1px solid #C1C1C1; /* размер и цвет границы блока */
      overflow-x: scroll; /* прокрутка по горизонтали */
      overflow-y: scroll; /* прокрутка по вертикали */
    }
    .div-users-window {
      height: 400px;
      width: 400px;
    }
  </style>
</head>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script>

    $(document).ready(function(){
      $("#refresh").click(
              function(){
                $("#chat").load("chat.jsp #chat");
              }
      )

      setInterval(function(){
                $("#chat").load("chat.jsp #chat");
                $("#users").load("chat.jsp #users");
              }, 1000
      );

    })
</script>

<body>
  <h1>Это главная страница чата</h1>
  <%
    Object userName = request.getSession().getValue("userName");
  %>
  <table>
    <tr>
      <td><label>Hello,</label></td>
      <td><p><%=userName%></p></td>
      <td>
        <form method="GET" action="LoginServlet">
          <p><input type="submit" value="Выход"></p>
        </form>
      </td>
    </tr>
  </table>

  <form method="POST" action="ChatServlet">
    <table>
      <tr>
        <td>
          <div class="div-chat-window" id="chat">
            <%
              ListIterator itr = ManageChat.getInstance().getMessages();
              while (itr.hasNext()){
                  Message msg = (Message) itr.next(); %>
              <span style="color: <%=msg.getColor()%>;">
                                  <%=msg.getTimeStamp()%>
                                  <%=msg.getPersonName()%>:
                                  <%=msg.getMessage()%>
              </span></br>
            <%
              }
            %>
          </div>
        </td>
        <td>
          <div class="div-users-window" id="users">
            <span style="font-style: italic"><h3>Сейчас в чате:</h3></span>

            <% for (Map.Entry<String, Person> entry : ManageChat.getInstance().getActiveUsers().entrySet()) { %>
                <span style="color: <%=entry.getValue().getColor()%>"><%= entry.getKey() %></span><br>
            <%
              }
            %>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td><label>Написать: </label></td>
        <td><input type="text" name="messageText"></td>
        <td><input type="submit" value="Отправить"></td>
      </tr>
    </table>
  </form>

</body>
</html>
