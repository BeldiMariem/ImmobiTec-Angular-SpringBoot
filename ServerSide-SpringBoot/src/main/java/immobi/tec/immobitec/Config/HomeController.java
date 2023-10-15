package immobi.tec.immobitec.Config;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Message;
import immobi.tec.immobitec.entities.Session;
import immobi.tec.immobitec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class HomeController {
    private static final String WS_ENDPOINT = "/socket";
    private static final String MESSAGES_TOPIC = "/chat/messages";

    @Autowired
    private WebSocketSessionServiceImpl webSocketSessionService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/home")
    public String home(Model model) {
        AppUser user = userRepository.findById(Session.getId_user()).orElse(null);
        //Session session = new Session();
        model.addAttribute("user", user);
        return "home";
    }

    @MessageMapping(WS_ENDPOINT)
    @SendTo(MESSAGES_TOPIC)
    public Message message(Message message) {
        return message;
    }

}
