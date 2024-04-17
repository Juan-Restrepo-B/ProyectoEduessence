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
@Table(name = "re_role")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole")
    public Long idRole;

    @Column(name = "name_role")
    public String nameRol;
}
