package eduessence.iniciar_sesion.models.JWT;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.concurrent.ConcurrentHashMap;

public class CustomUserCache implements UserCache {
    private final ConcurrentHashMap<String, UserDetails> cache = new ConcurrentHashMap<>();

    @Override
    public UserDetails getUserFromCache(String username) {
        return cache.get(username);
    }

    @Override
    public void putUserInCache(UserDetails user) {
        cache.put(user.getUsername(), user);
    }

    @Override
    public void removeUserFromCache(String username) {
        cache.remove(username);
    }
}