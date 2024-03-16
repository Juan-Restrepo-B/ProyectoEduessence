package eduessence.iniciar_sesion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "traza_logbusiness")
public class LogNegocio {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlogbusiness")
    private Long logIdNegocio;

    @Column(name = "idcaseuse")
    public String idCaseUse;

    @Column(name = "action")
    public String acccionNegocio;

    @Column(name = "description")
    public String descripcionNeogico;

    @Column(name = "create_at")
    public LocalDate fechaCreacionNegocio;

    @Column(name = "state")
    public String estadoAccionLog;

    @PrePersist
    private void prePersist(){
        this.fechaCreacionNegocio = LocalDate.now();
    }
}
