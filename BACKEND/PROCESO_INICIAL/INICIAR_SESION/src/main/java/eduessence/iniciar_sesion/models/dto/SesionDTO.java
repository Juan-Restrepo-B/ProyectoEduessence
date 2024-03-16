package eduessence.iniciar_sesion.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SesionDTO {
    public Long idUserS;
    public String usuarioS;
    public String ipSession;
    public String estadoS;
}
