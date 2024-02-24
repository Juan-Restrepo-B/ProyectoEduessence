
package eduessence.registro_cliente.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tr_user")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    public Long idUser;

    @Column(name = "idstate")
    public Long idstate;

    @Column(name = "idperson")
    public Long idPersona;

    @Column(name = "idrole")
    public Long idRol;

    @Column(name = "idcustomer")
    public Long idTipoCliente;

    @Column(name = "name_user", unique = true)
    public String nombreUsuario;

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
    @JoinColumn(name = "idcustomer", insertable = false, updatable = false)
    private TipoEmpleado tipoCliente;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idstate", insertable = false, updatable = false)
    private Estados estadoUsuario;

    public Usuario(String nombreUsuario, String password, Long idPerson, Long idTipoCliente, Long idrol, int i1) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.idPersona = idPerson;
        this.idRol = idrol;
        this.idTipoCliente = idTipoCliente;
        this.idstate = 1L;
    }
}
