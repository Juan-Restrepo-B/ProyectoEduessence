package eduessence.iniciar_sesion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name = "traza_logusers")
public class LogUsuario {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlogusers")
    public Long logIdUsuario;

    @Column(name = "action")
    public String acccionUsuario;

    @Column(name = "description")
    public String descripcionUsuario;

    @Column(name = "create_at")
    public LocalDate fechaCreacionUsuario;

    @PrePersist
    private void prePersist(){
        this.fechaCreacionUsuario = LocalDate.now();
    }
}