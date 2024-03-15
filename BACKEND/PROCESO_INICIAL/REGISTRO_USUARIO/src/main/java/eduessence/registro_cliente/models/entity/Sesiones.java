package eduessence.registro_cliente.models.entity;

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
@Table(name = "ss_session")
public class Sesiones {
    @Id
    @Column(name = "iduser")
    public Long idUserS;

    @Column(name = "name_user")
    public String usuarioS;

    @Column(name = "ip_session")
    public String ipSession;

    @Column(name = "name_statesession")
    public String estadoS;


    @PrePersist
    private void prePersist(){
    }
}