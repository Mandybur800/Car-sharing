<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Enter to login:</h1>
    <h3>${message}</h3>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>Please, enter your login</p>
        <input type="text" name="login" value="${log}" required>
        <p>Please, enter your password</p>
        <input type="password" name="pwd" required>
        <button type="submit">login</button>
    </form>
    <h5><a href="${pageContext.request.contextPath}/drivers/create">registration</a></h5>
</body>
</html>
