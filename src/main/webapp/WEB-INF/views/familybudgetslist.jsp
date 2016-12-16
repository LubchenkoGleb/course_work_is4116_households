<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/table.css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show family budget</title>
</head>
<body>
<c:if test="${exception != null}">
    <p class="error">${exception}</p>
</c:if>

<table border=1>
    <thead>
    <tr>
        <th>budget id</th>
        <th>amount_of_money</th>
        <th>date_of_operation</th>
        <th>family_member_id</th>
        <th>operation_id</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${familybudgets}" var="familybudget">
        <tr>
            <td><c:out value="${familybudget.id}" /></td>
            <td><c:out value="${familybudget.amountOfMoney}" /></td>
            <td><c:out value="${familybudget.dateOfOperation}" /></td>
            <td><c:out value="${familybudget.familyMemberId}" /></td>
            <td><c:out value="${familybudget.operationId}" /></td>

            <td><a href="/familybudget/preUpdate?id=<c:out value="${familybudget.id}"/>">Update</a></td>
            <td><a href="/familybudget/delete?id=<c:out value="${familybudget.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/familybudget/preInsert">Add load type</a></p>
<p><a href="/static/index.html"> Назад</a></p>
</body>
</html>