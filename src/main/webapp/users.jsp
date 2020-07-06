<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Users</title>
    <script src="users/usersAction.js"></script>
    <link rel="stylesheet" type="text/css" href="users/usersStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Indie+Flower&family=Poiret+One&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@700&display=swap" rel="stylesheet">
    <script src="users/usersAction.js"></script>
</head>

<body>
<div id="wrapper">
    <form id="form" method="post" action="${pageContext.request.contextPath}/checkers">
        <div class="divContainer">
            <div id="divHeader">
                <h1>Checkers</h1>
                <p>Join any of the players, or create your own room.</p>
            </div>
            <div id="newRoom" class="button" onclick="submit()">Create new room</div>
        </div>

        <c:if test="${sessionScope.waitingPlayers.size()==0}">
            <div id="emptyList">
                There is no active players on the list
                <hr>
            </div>
        </c:if>

        <div class="playerList">
            <c:forEach items="${sessionScope.waitingPlayers}" var="user">
                <div class="player" onclick="submitWithPartnerUsername('${user}')">
                    <div class="playerImageDiv">
                        <img src="users/pngwavee.png" class="image">
                    </div>
                    <div class="username">
                        <c:out value="${user}"></c:out>
                    </div>
                </div>
            </c:forEach>
            <c:forEach items="${sessionScope.inactivePlayers}" var="user">
                <div class="player">
                    <div class="playerImageDiv">
                        <img src="users/kindpng.png" class="image">
                    </div>
                    <div class="username">
                        <c:out value="${user}"></c:out><br>
                        <span class="inactiveInfo">inactive player</span>
                    </div>
                </div>
            </c:forEach>

        </div>
        <input type="hidden" id="partnerUsername" name="partnerUsername" value="">
        <input type="hidden" id="username" name="username" value="${sessionScope.username}">
    </form>

</div>

</body>

</html>