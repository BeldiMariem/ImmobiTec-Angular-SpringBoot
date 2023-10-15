package immobi.tec.immobitec.Config;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Session;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebSocketSessionServiceImpl {

    private Map<String, AppUser> sessionMap = new ConcurrentHashMap<>();


    public void setUserSession(String userName, AppUser user) {
        sessionMap.put(userName, user);
    }

    public void removeSession(String userName) {
        sessionMap.remove(userName);
    }

    public boolean sessionExists(String userName) {
        return sessionMap.containsKey(userName);
    }

}