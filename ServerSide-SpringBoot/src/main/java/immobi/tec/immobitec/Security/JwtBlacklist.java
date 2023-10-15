package immobi.tec.immobitec.Security;


import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
    public class JwtBlacklist {

       public static Set<String> blacklist = new HashSet<>();

    public static void add(String token) {
            blacklist.add(token);
        }

    public static boolean contains(String token) {
            return blacklist.contains(token);
        }
    }

