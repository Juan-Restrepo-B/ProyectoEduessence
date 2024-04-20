package eduessence.iniciar_sesion.models.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendEmailDTO {
    public String username;
    public String email;
    public String nuip;
    public String primerNombre;
    public String segundoNombre;
    public String primerApellido;
    public String segundoApellido;

    @Override
    public String toString() {
        return "SendEmailDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
