<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${locale}" scope="session"/>

<html lang="${locale}">
<head>
    <title>View conferences</title>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/view_conferences"><fmt:message key="conferences.all"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>

<div class="container">
    <h2><c:out value="${conference.title}"/></h2>

    <h4><fmt:message key="description"/></h4>
    <div><c:out value="${conference.description}"/></div>
    <hr>
    <h4><fmt:message key="conferences.date"/></h4>
    <div><c:out value="${conference.date}"/></div>
    <hr>
    <h4><fmt:message key="place"/></h4>
    <div><c:out value="${conference.place}"/></div>
    <hr>
    <h4><fmt:message key="status"/></h4>
    <div><c:out value="${conference.status}"/></div>
<br>
    <div class="btn-group">
        <form action="${pageContext.request.contextPath}/admin/delete_conference" method="POST">
            <input type="hidden" name="id" value="${conference.id}">
            <button type="submit" class="btn btn-danger">
                <fmt:message key="delete"/>
            </button>
        </form>
        <form  action="${pageContext.request.contextPath}/admin/edit_conference" method="POST">
            <input type="hidden" name="id" value="${conference.id}" />
            <button type="submit" class="btn btn-primary">
                <fmt:message key="edit"/>
            </button>
        </form>
        <form  action="${pageContext.request.contextPath}/admin/add_presentation" method="POST">
            <input type="hidden" name="id" value="${conference.id}" />
            <button type="submit" class="btn btn-primary">
                <fmt:message key="presentation.add"/>
            </button>
        </form>
    </div>

</div>
<div class="container">
<h2><fmt:message key="presentation.all"/></h2>
<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="title"/></th>
            <th><fmt:message key="description"/></th>
            <th><fmt:message key="speaker.name"/></th>
            <th><fmt:message key="status"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="presentation" items="${presentations}">
        <tr>
            <td><br><c:out value="${presentation.title}"/></td>
            <td><br><c:out value="${presentation.description}"/></td>
            <td><br><c:out value="${presentation.speakerId}"/></td>
            <td><br><c:out value="${presentation.status}"/></td>
            <td>
                <form  action="${pageContext.request.contextPath}/admin/add_speaker" method="POST">
                    <input type="hidden" name="id" value="${presentation.id}" />
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="speaker.add"/>
                    </button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/delete_presentation" method="POST">
                    <input type="hidden" name="conferenceId" value="${conference.id}" />
                    <input type="hidden" name="presentationId" value="${presentation.id}">
                    <button type="submit" class="btn btn-danger">
                    <fmt:message key="delete"/>
                </button>
            </form>
            </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
</div>
</body>
</html>

