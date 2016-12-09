<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/table.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All loadtypes</title>
</head>
<body>
<c:if test="${exception != null}">
    <p>${exception}</p>
</c:if>

<table border=1>
    <thead>
    <tr>
        <th>load type id</th>
        <th>title</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${loadtypes}" var="loadtype">
        <tr>
            <td><c:out value="${loadtype.id}" /></td>
            <td><c:out value="${loadtype.title}" /></td>

            <td><a href="/loadtype/preUpdate?id=<c:out value="${loadtype.id}"/>">Update</a></td>
            <td><a href="/loadtype/delete?id=<c:out value="${loadtype.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/loadtype/preInsert">Add load type</a></p>
</body>
</html>