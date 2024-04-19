package eduessence.iniciar_sesion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
@Data
@Builder
@Table(name = "tr_user")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    public Long idUser;

    @Column(name = "name_state")
    public String idstate;

    @Column(name = "idperson")
    public Long idPersona;

    @Column(name = "idrole")
    public Long idRol;

    @Column(name = "name_customer")
    public String idTipoCliente;

    @Column(name = "name_user", unique = true)
    public String username;

    @Column(name = "user_password")
    public String password;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idperson", insertable = false, updatable = false)
    private Persona persona;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrole", insertable = false, updatable = false)
    private Rol rol;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_customer", insertable = false, updatable = false)
    private TipoEmpleado tipoCliente;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_state", insertable = false, updatable = false)
    private Estados estadoUsuario;

    public Usuario(String username, String password, Long idPerson, String idTipoCliente, Long idrol, String idstate) {
        this.username = username;
        this.password = password;
        this.idPersona = idPerson;
        this.idRol = idrol;
        this.idTipoCliente = idTipoCliente;
        this.idstate = idstate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.idRol != null) {
            String roleName = convertRoleIdToRoleName(this.idRol);
            return List.of(new SimpleGrantedAuthority(roleName));
        } else {
            return List.of();
        }
    }

    private String convertRoleIdToRoleName(Long idRol) {
        switch(idRol.toString()) {
            case "1": return "ROLE_ADMIN";
            case "2": return "ROLE_USER";
            default: return "ROLE_GUEST";
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
