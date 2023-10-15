package immobi.tec.immobitec.Config;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Session;
import immobi.tec.immobitec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

public class PresenceEventListener {

    private final WebSocketSessionServiceImpl webSocketSessionService;

    @Autowired
    private UserRepository userRepository;

    public PresenceEventListener(WebSocketSessionServiceImpl webSocketSessionService) {
        this.webSocketSessionService = webSocketSessionService;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
       // Principal principal = event.getUser();
        //StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        //String userName = accessor.getFirstNativeHeader("username");
       // Session session = new Session();

        AppUser user = userRepository.findById(Session.getId_user()).orElse(null);
        webSocketSessionService.setUserSession(user.getName(), user);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        AppUser user = userRepository.findById(Session.getId_user()).orElse(null);
        webSocketSessionService.removeSession(user.getName());
    }
}
