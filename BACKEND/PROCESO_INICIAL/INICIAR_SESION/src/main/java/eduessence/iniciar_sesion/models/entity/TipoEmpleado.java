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
@Table(name = "re_customer/type")
public class TipoEmpleado {
    @Id
    @Column(name = "name_customer")
    public String nametipoClioente;
}
