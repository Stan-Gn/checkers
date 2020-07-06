package pl.checkers.gameLogic.player;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.websocket.CheckersWebsocketServer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends AbstractPlayer {
    protected Position from;
    protected Position to;
    private final Session session;


    public Player(String name, Session session) {
        super(name);
        this.session = session;
    }


    @Override
    public synchronized Position getPositionFrom() {
        while (from == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Position tempFrom = from;
        from = null;
        return tempFrom;
    }

    @Override
    public synchronized Position getPositionTo() {
        while (to == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Position tempTo = to;
        from = null;
        return tempTo;
    }

    public synchronized void setPositionFrom(Position from) {
        if (!super.activity)
            infoForPlayer("Not your turn");
        this.from = from;
        notifyAll();
    }

    public synchronized void setPositionTo(Position to) {
        if (super.activity) {
            this.to = to;
            notifyAll();
        }
    }

    @Override
    public void setActivity(boolean activity) {
        super.activity = activity;
    }


    @Override
    public void infoForPlayer(String message) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject messageActivity = provider.createObjectBuilder()
                .add("action", "inform")
                .add("information", message)
                .build();

        sendMessage(messageActivity);
    }

    private void sendMessage(JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            Logger.getLogger(CheckersWebsocketServer.class.getName()).log(Level.SEVERE, null, ex);
            try {
                session.close();
            } catch (IOException e) {
                Logger.getLogger(CheckersWebsocketServer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void sendBoard(Piece[][] clonedBoard) {
        JsonArray board = Json.createArrayBuilder().build();
        board = boardToJsonObject(clonedBoard, board);
        JsonObject sendBoard = Json.createObjectBuilder()
                .add("action", "move")
                .add("board", board)
                .build();

        sendMessage(sendBoard);
    }

    private JsonArray boardToJsonObject(Piece[][] clonedBoard, JsonArray board) {
        for (int i = 0; i < BoardGame.SIZE; i++) {
            for (int j = 0; j < BoardGame.SIZE; j++) {
                if (clonedBoard[i][j] != null) {
                    String pawn = clonedBoard[i][j].toString();
                    board = Json.createArrayBuilder(board)
                            .add(Json.createObjectBuilder()
                                    .add("coordinate", i + Integer.toString(j))
                                    .add("pawn", pawn)
                                    .build()
                            ).build();

                }

            }
        }
        return board;
    }



}
