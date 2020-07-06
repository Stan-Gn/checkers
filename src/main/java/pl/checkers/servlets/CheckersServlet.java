package pl.checkers.servlets;

import pl.checkers.websocket.UsersMakingSelection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CheckersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeUserFromUsersMakingSelection(req);
        setUsernameAndPartnerUsernameAsSessionAttribute(req);
        req.getRequestDispatcher("/checkers.jsp").forward(req, resp);

    }

    private void setUsernameAndPartnerUsernameAsSessionAttribute(HttpServletRequest req) {
        req.getSession().setAttribute("partnerUsername",req.getParameter("partnerUsername"));
        req.getSession().setAttribute("username",req.getParameter("username"));
    }

    private void removeUserFromUsersMakingSelection(HttpServletRequest req) {
        UsersMakingSelection playersMakingSelection = UsersMakingSelection.getInstance();
        playersMakingSelection.removeUsername(req.getSession());
    }

}
