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
            <td><a href="${pageContext.request.contextPath}/manufacturers/create">create</a></td>
            <td><a href="${pageContext.request.contextPath}/drivers/create">create</a></td>
            <td><a href="${pageContext.request.contextPath}/cars/create">create</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/manufacturers/">
                get all manufacturers
            </a></td>
            <td><a href="${pageContext.request.contextPath}/drivers/">get all drivers</a></td>
            <td><a href="${pageContext.request.contextPath}/cars/">get all cars</a></td>
            <td><a href="${pageContext.request.contextPath}/cars/drivers/add">
                add driver to car
            </a></td>
        </tr>
    </table>
</body>
</html>
