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
@Table(name = "re_customer/type")
public class TipoEmpleado {
    @Id
    @Column(name = "name_customer")
    public String nametipoClioente;
}
