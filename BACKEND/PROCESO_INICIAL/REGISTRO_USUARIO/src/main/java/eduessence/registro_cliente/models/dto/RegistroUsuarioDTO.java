package eduessence.registro_cliente.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RegistroUsuarioDTO {
    public String primerNombre;
    public String segundoNombre;
    public String primerApellido;
    public String segundoApellido;
    public String nuip;
    public String email;
    public String pais;
    public String nombreUsuario;
    public String password;
    public Long idRol;
    public Long idTipoCliente;
    public Long idUser;

    public RegistroUsuarioDTO(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
