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

    <form method="POST" action='/financialoperation/${operation}'>

        operation id : <input type="text" name="id"
                           value="<c:out value="${financialoperation.id}"/>"
                            readonly="readonly"/> (You Can't Change this)<br />
        title : <input type="text" name="title"
                           value="<c:out value="${financialoperation.title}" />" /> <br />
        type : <input type="text" name="operationType"
                          value="<c:out value="${financialoperation.operationType}" />" /> <br />

        <input  type="submit" value="Submit" />
    </form>
    <br>
    <form method="Get" action='/static/index.html'>
        <input  type="submit" value=" назад" />
    </form>
</body>
</html>