package eduessence.iniciar_sesion.models.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
@Table(name = "ss_session")
public class Sesiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession")
    public Long idSession;

    @Column(name = "iduser")
    public Long idUserS;

    @Column(name = "name_user")
    public String usuarioS;

    @Column(name = "ip_session")
    public String ipSession;

    @Column(name = "name_statesession")
    public String estadoS;

    public Sesiones(String nombreUsuario, String estadoSesion) {
        this.estadoS = estadoSesion;
        this.usuarioS = nombreUsuario;
    }
}