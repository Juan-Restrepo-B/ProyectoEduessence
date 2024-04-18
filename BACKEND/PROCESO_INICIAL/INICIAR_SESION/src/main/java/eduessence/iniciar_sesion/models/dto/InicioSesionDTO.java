package eduessence.iniciar_sesion.models.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InicioSesionDTO {
    public Long idUser;
    public String username;
    public String password;
    public Long idRol;
    public String idTipoCliente;
    public String idstate;
    public String nameRol;

    public InicioSesionDTO(String username, String password, String  rol, String tipCliente, String estadoUsuario) {
        this.username = username;
        this.password = password;
        this.nameRol = rol;
        this.idTipoCliente = tipCliente;
        this.idstate = estadoUsuario;
    }
}
