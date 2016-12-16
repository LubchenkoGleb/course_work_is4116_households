<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/table.css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All FamilyBudgets</title>
</head>
<body>
<c:if test="${exception != null}">
    <p class="error">${exception}</p>
</c:if>

<table border=1>
    <thead>
    <tr>
        <th>family member id</th>
        <th>name</th>
        <th>status</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${familymembers}" var="familymember">
        <tr>
            <td><c:out value="${familymember.id}" /></td>
            <td><c:out value="${familymember.name}" /></td>
            <td><c:out value="${familymember.status}" /></td>

            <td><a href="/familymember/preUpdate?id=<c:out value="${familymember.id}"/>">Update</a></td>
            <td><a href="/familymember/delete?id=<c:out value="${familymember.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/familymember/preInsert">Add FamilyBudget</a></p>

<p><a href="/static/index.html" > Назад</a></p>
</body>
</html>