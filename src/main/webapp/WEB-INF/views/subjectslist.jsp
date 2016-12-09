<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/table.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All subjects</title>
</head>
<body>
<c:if test="${exception != null}">
    <p>${exception}</p>
</c:if>

<table border=1>
    <thead>
    <tr>
        <th>subject id</th>
        <th>speciality</th>
        <th>title</th>
        <th>cathedra</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subjects}" var="subject">
        <tr>
            <td><c:out value="${subject.id}" /></td>
            <td><c:out value="${subject.speciality}" /></td>
            <td><c:out value="${subject.title}" /></td>
            <td><c:out value="${subject.cathedra}" /></td>

            <td><a href="/subject/preUpdate?id=<c:out value="${subject.id}"/>">Update</a></td>
            <td><a href="/subject/delete?id=<c:out value="${subject.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/subject/preInsert">Add subject</a></p>
</body>
</html>