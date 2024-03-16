package eduessence.iniciar_sesion.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InicioSesionDTO {
    public Long idUser;
    public String nombreUsuario;
    public String password;
    public Long idRol;
    public Long idTipoCliente;
    public Long idstate;

    public InicioSesionDTO(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public InicioSesionDTO(String password) {
        this.password = password;
    }
}
