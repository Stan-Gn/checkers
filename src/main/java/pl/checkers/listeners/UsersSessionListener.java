package pl.checkers.listeners;

import pl.checkers.websocket.UsersMakingSelection;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UsersSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(10);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        UsersMakingSelection playersMakingSelection = UsersMakingSelection.getInstance();
        playersMakingSelection.removeUsername(se.getSession());

    }
}
