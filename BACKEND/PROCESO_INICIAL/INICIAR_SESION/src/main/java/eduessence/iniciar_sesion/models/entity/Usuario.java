package eduessence.iniciar_sesion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
@Table(name = "tr_user")
public class Usuario {
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
}
