package eduessence.iniciar_sesion.models.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LogNegocioDTO {
    public String idCaseUse;
    public String acccionNegocio;
    public String descripcionNeogico;
    public String estadoAccionLog;
}