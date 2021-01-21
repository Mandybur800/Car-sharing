<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add manufacturer</title>
</head>
<body>
<h1> Please, put information about your manufacturer</h1>
    <form method="post" action="${pageContext.request.contextPath}/manufacturers/create">
        Please, enter name of manufacturer
        <input type="text" name="name" required>
        Please, enter country of manufacturer
        <input type="text" name="country" required>
        <button type="submit">Create</button>
    </form>
</body>
</html>
