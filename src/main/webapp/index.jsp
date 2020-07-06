<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="index/indexStyle.css">
    <title>Checkers</title>
</head>
<body>

<div class="container">
    <h1>Checkers</h1>
    <form method="post" action="${pageContext.request.contextPath}/users" name="usernameForm" class="usernameForm" id="usernameForm"  >
        <div class="formGroup">
            <label for="username" id="usernameLabel">Type your username</label>
            <input type="text" id="username" class="inputClass" name="username" value="">
        </div>
        <input type="button" class="formButton" value="Next" onclick="submitForm()">
        <div class="errorForm"><c:out value="${requestScope.errorUsername}"></c:out></div>
    </form>
</div>
<script src="index/actionIndex.js"></script>

</body>
</html>