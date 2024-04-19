package eduessence.iniciar_sesion.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecuperarClaveDTO {
    public String username;
    public String password;
    public String nuip;
    public String idstate;

    private String password2;
    private String code;

    public RecuperarClaveDTO(String username, String password, String nuip, String idstate) {
        this.username = username;
        this.password = password;
        this.nuip = nuip;
        this.idstate = idstate;
    }

    @Override
    public String toString() {
        return "RecuperarClaveDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
