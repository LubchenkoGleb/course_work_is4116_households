<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/forms.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new user</title>
</head>
<body>
<%String operation = (String)request.getAttribute("operation");%>

<form method="POST" action='/familybudget/${operation}'>

    family bydget id : <input type="text" name="id"
                        value="<c:out value="${familybudget.id}" />" readonly="readonly"/> (You Can't Change this)<br />

    amountOfMoney : <input type="text" name="amountOfMoney"
                   value="<c:out value="${familybudget.amountOfMoney}" />" /> <br />

    dateOfOperation : <input type="text" name="dateOfOperation"
                             value="<c:out value="${familybudget.dateOfOperation}" />" /> (format yyyy-mm-dd)<br />

    familyMemberId : <input type="text" name="familyMemberId"
                   value="<c:out value="${familybudget.familyMemberId}" />" /> <br />

    operationId : <input type="text" name="operationId"
                   value="<c:out value="${familybudget.operationId}" />" /> <br />

    <input  type="submit" value="Submit" />
</form>
<br>
<form method="Get" action='/static/index.html'>
    <input  type="submit" value=" назад" />
</form>
</body>
</html>