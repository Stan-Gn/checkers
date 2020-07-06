package pl.checkers.websocket;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UsersMakingSelection {
    private final Map<HttpSession, String> map = new HashMap<>();
    private static UsersMakingSelection INSTANCE;

    private UsersMakingSelection() {
    }

    public static UsersMakingSelection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersMakingSelection();
        }
        return INSTANCE;
    }

    public void addUsername(String name, HttpSession session) {
        synchronized (map) {
            map.put(session, name);
        }
    }

    public void removeUsername(HttpSession session) {

        synchronized (map) {
            if (map.containsKey(session))
                map.remove(session);
        }
    }

    public boolean isUsernameExistInMap(String name) {
        for (Map.Entry<HttpSession, String> entry : map.entrySet()) {
            if (entry.getValue().equals(name))
                return true;
        }
        return false;
    }

}
