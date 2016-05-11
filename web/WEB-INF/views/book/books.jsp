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
        <%@ include  file="/js/book.js" %>
    </script>
    <script>
        <%@ include file="/js/tsort.js"%>
    </script>
    <style>
        <%@ include file='/css/style.css' %>
    </style>
    <title>Web Library | Books</title>
</head>

<body>

<div class="headercontent" align="center" >

    <a href="/book/books" class="buttonhead">Книги</a>
    <a href="/user/users" class="buttonhead">Пользователи</a>

    <div class="userinfo" >

        Вы авторизовались как: <p id="username">${name}</p>
        <%--<c:url var="logoutUrl" value="j_spring_security_logout"/>--%>
        <%--<form action="${logoutUrl}" method="post">--%>
            <%--<input type="submit" value="Log out" />--%>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        <%--</form>--%>
        <%--<c:forEach items="${roles}">--%>
            <%--<c:out value="${item}"/> <br>--%>
        <%--</c:forEach>--%>
    </div>

</div>

<div class="maincontent" align="center">

    <button class="buttonhead" id="addbook" >Добавить книгу</button>

    <div id="modal_form_add">
        <p>Isn<br>
            <input type="text" id="isnAddVal" name="isn" value="" size="40">
        </p>
        <p>Автор<br>
            <input type="text" id="authorAddVal" name="author" value="" size="40">
        </p>
        <p>Название<br>
            <input type="text" id="nameAddVal" name="name" value="" size="40">
        </p>
        <p id="error"></p>
        <button class="buttonhead" id="modal_close">Отмена</button>
        <button class="buttonhead" id="modal_add_book">Добавить книгу</button>
    </div>

    <div id="overlay"></div>

    <table class="sort" id="books" align="center" >
        <thead><tr><th>isn</th><th class="thsortcolor" id="authorth" data-type="string">Автор</th><th class="thnormalcolor" id="nameth" data-type="string">Название</th><th>Кем взята</th><th>Удалить</th></tr></thead>

        <tbody>
        </tbody>
    </table>
    <button class="buttonhead" id="loadmore" >Показать еще</button>
</div>

</body>

</html>


