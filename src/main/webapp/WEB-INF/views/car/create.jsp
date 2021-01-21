<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add cars</title>
</head>
<body>
<h1> Please, put information about your car</h1>
    <form method="post" action="${pageContext.request.contextPath}/cars/create">
        Please, enter model
        <input type="text" name="model" required>
        Please, enter manufacturer ID
        <input type="number" name="manufacturer" required>
        <button type="submit">Create</button>
    </form>
</body>
</html>
