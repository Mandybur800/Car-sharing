<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver</title>
</head>
<body>
<h1> Please, put information about your driver</h1>
<form method="post" action="${pageContext.request.contextPath}/driver/create">
    Please, enter name of driver<input type="text" name="name" required>
    Please, enter driving license<input type="number" name="license" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
