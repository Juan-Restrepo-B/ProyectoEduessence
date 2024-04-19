package eduessence.iniciar_sesion.models.Code;

import lombok.Getter;
@Getter
public class TokenInfo {
    private long timestamp;
    private String user;

    public TokenInfo(long timestamp, String user) {
        this.timestamp = timestamp;
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return user;
    }
}
