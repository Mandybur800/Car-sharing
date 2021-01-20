<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver</title>
</head>
<body>
<h1> Please, put next information:</h1>
<form method="post" action="${pageContext.request.contextPath}/car/addDriver">
    car id: <input type="number" name="car_id" required>
    driver id: <input type="number" name="driver_id" required>
    <button type="submit">add</button>
</form>
</body>
</html>
