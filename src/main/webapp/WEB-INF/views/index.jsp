<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px" style="background-color:#1de1e1">
    <tr>
        <th>Work with manufacturer</th>
        <th>work with driver</th>
        <th>Work with car</th>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/manufacturer/create">create</a></td>
        <td><a href="${pageContext.request.contextPath}/driver/create">create</a></td>
        <td><a href="${pageContext.request.contextPath}/car/create">create</a></td>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/manufacturer/all">
            get all manufacturers
        </a></td>
        <td><a href="${pageContext.request.contextPath}/driver/all">get all drivers</a></td>
        <td><a href="${pageContext.request.contextPath}/car/all">get all cars</a></td>
        <td><a href="${pageContext.request.contextPath}/car/drivers/add">
            add driver to car
        </a></td>
    </tr>
</table>
</body>
</html>
