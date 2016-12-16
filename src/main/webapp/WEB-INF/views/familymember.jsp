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

<form method="POST" action='/familymember/${operation}'>

    family member id : <input type="text" name="id"
                        value="<c:out value="${familymember.id}" />" <%--readonly="readonly"--%>/> (You Can't Change this)<br />
    name : <input type="text" name="name"
                      value="<c:out value="${familymember.name}" />" /> <br />
    status : <input type="text" name="status"
                            value="<c:out value="${familymember.status}" />" /> <br />

    <input  type="submit" value="Submit" />
</form>
<br>
<form method="Get" action='/static/index.html'>
    <input  type="submit" value=" назад" />
</form>
</body>
</html>