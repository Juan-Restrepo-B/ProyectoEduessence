package eduessence.iniciar_sesion.models.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name = "es_state")
public class Estados {

    @Id
    @Column(name = "name_state")
    public String estado;

}
