<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="checkers/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Changa:wght@300&display=swap" rel="stylesheet">
    <script src="checkers/action.js"></script>
    <meta charset="UTF-8">
    <title>Checkers</title>
</head>
<body onload="createUser('${sessionScope.username}', '${sessionScope.partnerUsername}')">
<div id="mainDiv">
    <div id="leftDiv">
        <div class="chessboard" id="chessboard">
            <!-- 1st -->
            <div class="white" id="00"></div>
            <div class="black" id="01"></div>
            <div class="white" id="02"></div>
            <div class="black" id="03"></div>
            <div class="white" id="04"></div>
            <div class="black" id="05"></div>
            <div class="white" id="06"></div>
            <div class="black" id="07"></div>
            <!-- 2nd -->
            <div class="black" id="10"></div>
            <div class="white" id="11"></div>
            <div class="black" id="12"></div>
            <div class="white" id="13"></div>
            <div class="black" id="14"></div>
            <div class="white" id="15"></div>
            <div class="black" id="16"></div>
            <div class="white" id="17"></div>
            <!-- 3th -->
            <div class="white" id="20"></div>
            <div class="black" id="21"></div>
            <div class="white" id="22"></div>
            <div class="black" id="23"></div>
            <div class="white" id="24"></div>
            <div class="black" id="25"></div>
            <div class="white" id="26"></div>
            <div class="black" id="27"></div>
            <!-- 4st -->
            <div class="black" id="30"></div>
            <div class="white" id="31"></div>
            <div class="black" id="32"></div>
            <div class="white" id="33"></div>
            <div class="black" id="34"></div>
            <div class="white" id="35"></div>
            <div class="black" id="36"></div>
            <div class="white" id="37"></div>
            <!-- 5th -->
            <div class="white" id="40"></div>
            <div class="black" id="41"></div>
            <div class="white" id="42"></div>
            <div class="black" id="43"></div>
            <div class="white" id="44"></div>
            <div class="black" id="45"></div>
            <div class="white" id="46"></div>
            <div class="black" id="47"></div>
            <!-- 6th -->
            <div class="black" id="50"></div>
            <div class="white" id="51"></div>
            <div class="black" id="52"></div>
            <div class="white" id="53"></div>
            <div class="black" id="54"></div>
            <div class="white" id="55"></div>
            <div class="black" id="56"></div>
            <div class="white" id="57"></div>
            <!-- 7th -->
            <div class="white" id="60"></div>
            <div class="black" id="61"></div>
            <div class="white" id="62"></div>
            <div class="black" id="63"></div>
            <div class="white" id="64"></div>
            <div class="black" id="65"></div>
            <div class="white" id="66"></div>
            <div class="black" id="67"></div>
            <!-- 8th -->
            <div class="black" id="70"></div>
            <div class="white" id="71"></div>
            <div class="black" id="72"></div>
            <div class="white" id="73"></div>
            <div class="black" id="74"></div>
            <div class="white" id="75"></div>
            <div class="black" id="76"></div>
            <div class="white" id="77"></div>

        </div>
    </div>
    <div id="rightDiv">
        <span id="spanHeader"> Information </br></span>
        <span id="info"> </span>
    </div>

</div>


</body>
</html>
