<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>Book edit</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/view_users"><fmt:message key="user.all"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/addbook"><fmt:message key="book.add"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
<h2><fmt:message key="books.all"/></h2>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="title"/></th>
            <th><fmt:message key="author"/></th>
            <th><fmt:message key="publisher"/></th>
            <th><fmt:message key="publish.date"/></th>
            <th><fmt:message key="quantity"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
        <tr>
            <td><br><c:out value="${book.title}"/></td>
            <td><br><c:out value="${book.author}"/></td>
            <td><br><c:out value="${book.publisher}"/></td>
            <td><br><c:out value="${book.publishDate}"/></td>
            <td><br><c:out value="${book.quantity}"/></td>

            <td>
                <form  action="${pageContext.request.contextPath}/admin/edit_book" method="POST">
                    <input type="hidden" name="id" value="${book.id}" />
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="edit"/>
                    </button>
                </form>
            </td>
            <td><form action="${pageContext.request.contextPath}/admin/delete_book" method="POST">
            <input type="hidden" name="id" value="${book.id}">
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
        <td><a href="/admin/view_books?page=${currentPage - 1}"><fmt:message key="previous"/></a></td>
    </c:if>
    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <td>${i}</td>
            </c:when>
            <c:otherwise>
                <td><a href="/admin/view_books?page=${i}">${i}</a></td>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/admin/view_books?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
    </c:if>
    <br/>
    <br/>
</div>
</div>
</body>
</html>

