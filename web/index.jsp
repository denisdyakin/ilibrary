<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
  <script type="text/javascript">
    <%@ include file="js/jquery-2.2.3.min.js" %>
  </script>
  <script type="text/javascript">
    <%@ include  file="js/book.js" %>
  </script>
  <style>
    <%@ include file='css/style.css' %>
  </style>
  <title>Web Library</title>
</head>

<body>

<div class="headercontent" align="center" >

  <a href="/book/books" class="buttonhead">Книги</a>
  <a href="/user/users" class="buttonhead">Пользователи</a>

</div>

</body>

</html>