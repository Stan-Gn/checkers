package pl.checkers.servlets;

import pl.checkers.websocket.GameRoomsHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setListOfWaitingPlayersAndListInactivePlayersAsSessionAttribute(req);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    private void setListOfWaitingPlayersAndListInactivePlayersAsSessionAttribute(HttpServletRequest req) {
        GameRoomsHandler gameRooms = GameRoomsHandler.getInstance();
        req.getSession().setAttribute("waitingPlayers", gameRooms.getListOfUsernameWaitingPlayers());
        req.getSession().setAttribute("inactivePlayers",gameRooms.getUsernameListInactivePlayers());
    }
}
