<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Drivers</title>
</head>
<body>
<h1>All Drivers</h1>
<h4><a href="${pageContext.request.contextPath}/">back to title</a></h4>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>license number</th>
    </tr>
    <c:forEach var="driver" items="${drivers}">
        <tr>
            <td>
                <c:out value="${driver.id}"/>
            </td>
            <td>
                <c:out value="${driver.name}"/>
            </td>
            <td>
                <c:out value="${driver.licenceNumber}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/driver/delete?id=${driver.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
