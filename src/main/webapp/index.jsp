<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Index</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}?locale=en">
                        <fmt:message key="button.english"/>
                                                <img src="<c:url value="image/english.jpg"/>" width="30" height="20">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?locale=uk_UA">
                        <fmt:message key="button.ukrainian"/>
                                                <img src="<c:url value="image/ukraine.jpg"/>" width="30" alt="">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><fmt:message key="login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registration.jsp"><fmt:message key="registration"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<main role="main" class="inner cover" align="center">
    <h1 class="cover-heading"><fmt:message key="welcome"/></h1>
    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Risus commodo viverra maecenas accumsan lacus. Vel turpis nunc eget lorem dolor. Vitae sapien pellentesque habitant morbi tristique senectus et netus. Nullam vehicula ipsum a arcu cursus vitae congue.
    </p>
</main>
</body>
</html>


