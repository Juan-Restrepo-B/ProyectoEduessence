package eduessence.iniciar_sesion.models.entity;

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
@Table(name = "es_state")
public class Estados {

    @Id
    @Column(name = "name_state")
    public String estado;

}
