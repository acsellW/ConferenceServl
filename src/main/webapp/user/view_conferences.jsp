<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>User Cabinet</title>
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
                    <form  method="POST" action="/logout">
                        <button class="btn btn-primary" type="submit"><fmt:message key="logout"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/cabinet.jsp"><fmt:message key="cabinet"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<br/>
<div class="container">

    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/user/find" method="POST">
        <input class="form-control mr-sm-2" type="search" name="text" placeholder="<fmt:message key="title.search"/>" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search"/></button>
    </form>
</div>
<br/>

<div class="container">
<h2><fmt:message key="conferences.all"/></h2>
<div>
    <form action="${pageContext.request.contextPath}/user/take_place" method="POST">
        <table class="table table-striped">
        <thead>
        <tr>
            <th><a href="/user/view_conferences?page=${currentPage}&sort=title&sortDir=${sortDir eq 'asc' ? 'desc' : 'asc'}"><fmt:message key="title"/></a></th>
            <th><a href="/user/view_conferences?page=${currentPage}&sort=date&sortDir=${sortDir eq 'asc' ? 'desc' : 'asc'}"><fmt:message key="conferences.date"/></a></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="conference" items="${conferences}">
        <tr>
            <td><br><c:out value="${conference.title}"/></td>
            <td><br><c:out value="${conference.date}"/></td>
            <input type="hidden" name="id" value="${conference.id}">
            <td>
                <button type="submit" class="btn btn-primary"><fmt:message key="take.place"/></button>
            </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
        <c:if test="${currentPage != 1}">
            <td><a href="/user/view_conferences?page=${currentPage - 1}&sort=${sort}&sortDir=${sortDir}"><fmt:message key="previous"/></a></td>
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="view_conferences?page=${i}&sort=${sort}&sortDir=${sortDir}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="/user/view_conferences?page=${currentPage + 1}&sort=${sort}&sortDir=${sortDir}"><fmt:message key="next"/></a></td>
        </c:if>
    <br/>
        <br/>

    </form>
</div>
</div>
</body>
</html>

