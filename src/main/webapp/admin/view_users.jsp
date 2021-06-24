<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Admin Cabinet</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/cabinet"><fmt:message key="cabinet"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/add_conference"><fmt:message key="conference.add"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/view_conferences"><fmt:message key="conferences.all"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
<h2><fmt:message key="user.all"/></h2>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="conferences.count"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
            <tr>
            <td><br><c:out value="${user.name}"/></td>
            <td><br><c:out value="${user.surname}"/></td>
            <td><br><c:out value="${user.email}"/></td>
            <td><br><c:if test="${user.role eq 'ROLE_USER'}">
                <fmt:message key="user"/>
            </c:if>
                <c:if test="${user.role eq 'ROLE_SPEAKER'}">
                    <fmt:message key="speaker"/>
                </c:if></td>
<%--                <c:out value="${user.order.getId() == 0 ? '' : user.order.id}" />--%>
            </td>
                <td>
                    <form  action="${pageContext.request.contextPath}/admin/edit_user" method="POST">
                        <input type="hidden" name="id" value="${user.id}" />
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="edit"/>
                        </button>
                    </form>
                </td>
                <td><form action="${pageContext.request.contextPath}/admin/delete_user" method="POST">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" class="btn btn-danger">
                        <fmt:message key="delete"/>
                    </button>

                </form>
                </td>
        </c:forEach>
        </tr>
        </tbody>
    </table>
    <c:if test="${currentPage != 1}">
        <td><a href="/admin/view_users?page=${currentPage - 1}"><fmt:message key="previous"/></a></td>
    </c:if>
    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <td>${i}</td>
            </c:when>
            <c:otherwise>
                <td><a href="/admin/view_users?page=${i}">${i}</a></td>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/admin/view_users?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
    </c:if>
</div>
</div>
</body>
</html>

