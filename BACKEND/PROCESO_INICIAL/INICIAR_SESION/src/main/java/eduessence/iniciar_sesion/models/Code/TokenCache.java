package eduessence.iniciar_sesion.models.Code;

import org.springframework.stereotype.Component;
import org.springframework.cache.CacheManager;

import java.util.concurrent.TimeUnit;

@Component
public class TokenCache {

    private final CacheManager cacheManager;
    private final TokenService tokenService;

    public TokenCache(CacheManager cacheManager, TokenService tokenService) {
        this.cacheManager = cacheManager;
        this.tokenService = tokenService;
    }

    public String createToken(String user) {
        String token = tokenService.generateToken();
        TokenInfo tokenInfo = new TokenInfo(System.currentTimeMillis(), user);
        cacheManager.getCache("tokens").put(token, tokenInfo);
        return token;
    }

    public void validateToken(String token, String user) throws TokenExpiredException {
        TokenInfo tokenInfo = cacheManager.getCache("tokens").get(token, TokenInfo.class);
        if (tokenInfo == null) {
            throw new TokenExpiredException("Token no encontrado o inválido.");
        }

        long minutesElapsed = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - tokenInfo.getTimestamp());
        if (minutesElapsed >= 10) {
            cacheManager.getCache("tokens").evict(token);
            throw new TokenExpiredException("El tiempo del token ha expirado.");
        } 1

        if (!tokenInfo.getUsername().equals(user)) {
            throw new TokenExpiredException("Token no válido para este usuario.");
        }

        cacheManager.getCache("tokens").evict(token);
    }

}