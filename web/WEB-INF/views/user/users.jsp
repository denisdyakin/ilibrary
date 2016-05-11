<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <script type="text/javascript">
        <%@ include file="/js/jquery-2.2.3.min.js" %>
    </script>
    <script type="text/javascript">
        <%@ include  file="/js/user.js" %>
    </script>
    <style>
        <%@ include file='/css/style.css' %>
    </style>
    <title>Web Library | Users</title>
</head>
<body>

    <div class="headercontent" align="center">

        <a href="${pageContext.request.contextPath}/book" class="buttonhead">Книги</a>
        <a href="${pageContext.request.contextPath}/user" class="buttonhead">Пользователи</a>
        <div class="userinfo"></div>

    </div>

    <div class="maincontent" align="center">

        <button class="buttonhead" id="adduser" >Добавить пользователя</button>

        <div id="modal_form_add">
            <p>Логин<br>
                <input type="text" id="nameAddVal" name="name" value="" size="40">
            </p>
            <p>Пароль<br>
                <input type="text" id="passwordAddVal" name="password" value="" size="40">
            </p>
            <p id="error"></p>
            <button class="buttonhead" id="modal_close">Отмена</button>
            <button class="buttonhead" id="modal_add_user">Сохранить</button>
        </div>

        <div id="overlay"></div>

        <table id="users" align="center" border="1">
            <tbody>
            <tr><th>Логин</th><th>Удалить</th></tr>
            </tbody>
        </table>
    </div>

</body>
</html>
