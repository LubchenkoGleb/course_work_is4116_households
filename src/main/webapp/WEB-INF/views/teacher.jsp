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

    <form method="POST" action='/teacher/${operation}'>

        Teacher id : <input type="text" name="id"
                           value="<c:out value="${teacher.id}"/>"
                            readonly="readonly"/> (You Can't Change this)<br />
        Position : <input type="text" name="position"
                           value="<c:out value="${teacher.position}" />" /> <br />
        employmentType : <input type="text" name="employmentType"
                          value="<c:out value="${teacher.employmentType}" />" /> <br />
        fullName : <input type="text" name="fullName"
                          value="<c:out value="${teacher.fullName}" />" /> <br />

        <input  type="submit" value="Submit" />
    </form>
</body>
</html>