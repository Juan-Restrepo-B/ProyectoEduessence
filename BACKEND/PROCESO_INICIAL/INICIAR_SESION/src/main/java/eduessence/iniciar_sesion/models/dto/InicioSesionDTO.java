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
public class InicioSesionDTO implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.nameRol != null && !this.nameRol.isEmpty()) {
            return List.of(new SimpleGrantedAuthority(this.nameRol));
        } else {
            return List.of();
        }
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
