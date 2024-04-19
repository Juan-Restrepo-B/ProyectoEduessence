package eduessence.iniciar_sesion.models.Code;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
@Service
public class TokenService {

    private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int TOKEN_LENGTH = 6;

    public String generateToken() {
        Random random = new SecureRandom();
        char[] result = new char[TOKEN_LENGTH];
        for (int i = 0; i < result.length; i++) {
            result[i] = CHARSET_AZ_09[random.nextInt(CHARSET_AZ_09.length)];
        }
        return new String(result);
    }
}