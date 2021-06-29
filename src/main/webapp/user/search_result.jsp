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
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/view_conferences"><fmt:message key="conferences.all"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
    <h2><fmt:message key="conferences.all"/></h2>
    <div>
        <form action="${pageContext.request.contextPath}/user/take_place" method="POST">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="title"/></th>
                    <th><fmt:message key="description"/></th>
                    <th><fmt:message key="conferences.date"/></th>
                    <th><fmt:message key="status"/></th>
                    <th><fmt:message key="place"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="conference" items="${conferenceList}">
                <tr>
                    <td><br><c:out value="${conference.title}"/></td>
                    <td><br><c:out value="${conference.description}"/></td>
                    <td><br><c:out value="${conference.date}"/></td>
                    <td><br><c:out value="${conference.status}"/></td>
                    <td><br><c:out value="${conference.place}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/user/view_conference" method="POST">
                            <input type="hidden" name="conferenceId" value="${conference.id}"/>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="view"/>
                            </button>
                        </form>
                    </td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>

