<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new user</title>
</head>
<body>
<%String operation = (String)request.getAttribute("operation");%>

<form method="POST" action='/loadtype/${operation}'>

    Subject id : <input type="text" name="id"
                        value="<c:out value="${loadtype.id}" />" <%--readonly="readonly"--%>/> (You Can't Change this)<br />
     title : <input type="text" name="title"
                   value="<c:out value="${loadtype.title}" />" /> <br />

    <input  type="submit" value="Submit" />
</form>
</body>
</html>