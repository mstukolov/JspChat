<%--
  User: Maxim
  Date: 07.12.2016
  Time: 17:11
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>Добро пожаловать в чат</h1>
  <form method="POST" action="LoginServlet">
    <table>
    <tr>
      <td><label>Представьтесь: </label></td>
      <td><input type="text" name="userName"></td>
    </tr>
    <tr>
      <td><label>Выберите цвет текста: </label></td>
      <td>
        <select name="color" style="width: 100%">
          <option style="color: black">Черный</option>
          <option style="color: red">Красный</option>
          <option style="color: blue">Синий</option>
          <option style="color: green">Зеленый</option>
        </select>
      </td>
    </tr>
     <tr>
       <td></td><td><input type="submit" value="Войти"></td>
     </tr>
    </table>
  </form>
  </body>
</html>
