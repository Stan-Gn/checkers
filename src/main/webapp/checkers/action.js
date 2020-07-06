let socket;
let positionFrom;
let positionTo;

function createUser(username, partnerUsername) {
    socket = new WebSocket("ws://"+document.location.host+"/actions");
    socket.onmessage = onMessage;

    const divsBlack = document.querySelectorAll('.black');
    const divsWhite = document.querySelectorAll('.white');

    divsBlack.forEach(el => el.setAttribute("onclick", "move(id)"));
    divsWhite.forEach(el => el.setAttribute("onclick", "move(id)"));

    socket.onopen = function action() {

        if (partnerUsername !== "") {
            addToPair(username, partnerUsername);
        } else {
            sendUsername(username);
        }
    }
}

function onMessage(event) {
    const data = JSON.parse(event.data);
    let span;

    switch (data.action) {
        case "activity":
            span = document.getElementById("info");
            span.textContent = data.activity;
            break;
        case "inform" :
            span = document.getElementById("info");
            span.textContent = data.information;
            break;
        case "move" :
            clearBoard();
            const Board = data.board;
            Board.forEach(printPieces);
    }

}
function printPieces(item) {
    switch (item.pawn) {
        case "PAWN_BLACK":
            document.getElementById(item.coordinate).innerHTML = "&#9823;";
            break;
        case "PAWN_WHITE":
            document.getElementById(item.coordinate).innerHTML = "&#9817;";
            break;
        case "QUEEN_BLACK" :
            document.getElementById(item.coordinate).innerHTML = "&#9818;";
            break;
        case  "QUEEN_WHITE":
            document.getElementById(item.coordinate).innerHTML = "&#9812;";
            break;
    }

}

function clearBoard() {
    const fields = document.getElementById("chessboard").childNodes;

    fields.forEach(clearFields);

    function clearFields(item) {
        item.innerHTML = "";
    }

}

function sendUsername(username) {
    const Username = {
        action: "addUsername",
        username: username,
    };
    socket.send(JSON.stringify(Username));

}


function addToPair(username, partnerUsername) {
    const Pair = {
        action: "addToRoom",
        username: username,
        partnerUsername: partnerUsername,
    };
    socket.send(JSON.stringify(Pair));
}

function sendMove() {
    const Move = {
        action: "move",
        positionFrom: positionFrom,
        positionTo: positionTo,
    };
    socket.send(JSON.stringify(Move));
    positionTo = null;
    positionFrom = null;

}

function move(id) {
    if (positionFrom == null) {
        positionFrom = id;
    } else {
        if (positionFrom === id) {
            positionFrom = null;
        } else {
            positionTo = id;
            sendMove(positionFrom, positionTo);
        }
    }
}
