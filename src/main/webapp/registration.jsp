<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="resources"/>

<html lang="${locale}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <div>
        <nav class="navbar navbar-expand bg-light" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><fmt:message key="login"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
    <h2><fmt:message key="greetings"/></h2>

    <form action="/registration" method="POST">
        <div class="form-group">
            <p><fmt:message key="name"/></p>
            <input type="text" placeholder="<fmt:message key="name"/>" name="name" />

        </div>
        <div class="form-group">
            <p><fmt:message key="surname"/></p>
            <input type="text" placeholder="<fmt:message key="surname"/>"  name="surname" />

        </div>
        <div class="form-group">
            <p><fmt:message key="email"/> </p>
            <input type="email" placeholder="<fmt:message key="email"/>" name="email" />

        </div>
        <div class="form-group">
            <p><fmt:message key="password"/> </p>
            <input type="password" placeholder="<fmt:message key="password"/>" name="password" >

        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="registration"/> </button>
    </form>
</div>
</body>
</html>

