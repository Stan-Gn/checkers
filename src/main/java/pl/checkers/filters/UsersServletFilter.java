package pl.checkers.filters;


import pl.checkers.websocket.UsersMakingSelection;
import pl.checkers.websocket.GameRoomsHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UsersServletFilter extends HttpFilter {
    String username;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        username = req.getParameter("username");
        username = usernameValidation(username, req, res);
        req.getSession().setAttribute("username", username);
        chain.doFilter(req, res);
    }

    private String usernameValidation(String username, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UsersMakingSelection usersMakingSelection = UsersMakingSelection.getInstance();
        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("errorUsername", "You didn't enter username");
            forwardToLoginPage(req, res);
        } else if (usersMakingSelection.isUsernameExistInMap(username)) {
            req.setAttribute("errorUsername", "The username already exists");
            forwardToLoginPage(req, res);
        } else if (usernamePlayersAlreadyExist(username)) {
            req.setAttribute("errorUsername", "The username already exists");
            forwardToLoginPage(req, res);
        } else {
            username = username.trim();
            usersMakingSelection.addUsername(username, req.getSession());
        }
        return username;
    }

    private void forwardToLoginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
    }

    private boolean usernamePlayersAlreadyExist(String username) {
        username = username.trim();
        GameRoomsHandler gameRooms = GameRoomsHandler.getInstance();
        return gameRooms.isUsernameAlreadyExist(username);
    }
}
