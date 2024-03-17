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
    public String nameRol;
    public String nametipoClioente;
    public String estado;

    public InicioSesionDTO(String nombreUsuario, String password, String  rol, String tipCliente, String estadoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nameRol = rol;
        this.nametipoClioente = tipCliente;
        this.estado = estadoUsuario;
    }
}
