<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/table.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All familyMembers</title>
</head>
<body>
    <c:if test="${exception != null}">
        <p class="error">${exception}</p>
    </c:if>

    <table border=1>
        <thead>
        <tr>
            <th>familyMember id</th>
            <th>fullName</th>
            <th>position</th>
            <th>employment type</th>
            <th colspan=2>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${financialoperations}" var="financialoperation">
            <tr>
                <td><c:out value="${financialoperation.id}" /></td>
                <td><c:out value="${financialoperation.title}" /></td>
                <td><c:out value="${financialoperation.operationType}" /></td>

                <td><a href="/financialoperation/preUpdate?id=<c:out value="${financialoperation.id}"/>">Update</a></td>
                <td><a href="/financialoperation/delete?id=<c:out value="${financialoperation.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p><a class="third" href="/financialoperation/preInsert">Add familyMember</a></p>
    <p><a class="third" href="/static/index.html"> Назад</a></p>
</body>
</html>