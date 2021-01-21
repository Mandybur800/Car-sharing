<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver</title>
</head>
<body>
    <h1> Please, put information about your driver</h1>
    <h3>${message}</h3>
    <form method="post" action="${pageContext.request.contextPath}/drivers/create">
        <p>Please, enter name of driver</p>
        <input type="text" name="name" required>
        <p>Please, enter driving license</p>
        <input type="number" name="license" required>
        <p>Please, enter your login</p>
        <input type="text" name="login" value="${log}" required>
        <p>Please, enter your password</p>
        <input type="password" name="pwd" required>
        <p>Please, enter your password again</p>
        <input type="password" name="pwd-repeat" required>
        <p><button type="submit">register</button></p>
    </form>
</body>
</html>
