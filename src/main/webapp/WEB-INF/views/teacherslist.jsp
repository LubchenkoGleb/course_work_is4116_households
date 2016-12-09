<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/table.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All teachers</title>
</head>
<body>
    <c:if test="${exception != null}">
        <p>${exception}</p>
    </c:if>

    <table border=1>
        <thead>
        <tr>
            <th>teacher id</th>
            <th>fullName</th>
            <th>position</th>
            <th>employment type</th>
            <th colspan=2>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher">
            <tr>
                <td><c:out value="${teacher.id}" /></td>
                <td><c:out value="${teacher.fullName}" /></td>
                <td><c:out value="${teacher.position}" /></td>
                <td><c:out value="${teacher.employmentType}" /></td>

                <td><a href="/teacher/preUpdate?id=<c:out value="${teacher.id}"/>">Update</a></td>
                <td><a href="/teacher/delete?id=<c:out value="${teacher.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p><a href="/teacher/preInsert">Add teacher</a></p>
</body>
</html>