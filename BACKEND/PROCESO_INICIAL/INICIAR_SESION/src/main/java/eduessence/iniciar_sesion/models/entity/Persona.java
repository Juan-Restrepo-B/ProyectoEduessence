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
@Table(name = "tr_person")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idperson")
    public Long idPerson;

    @Column(name = "person_nuip")
    public String nuip;

    @Column(name = "person_name1")
    public String primerNombre;

    @Column(name = "person_name2")
    public String segundoNombre;

    @Column(name = "person_lastname1")
    public String primerApellido;

    @Column(name = "person_lastname2")
    public String segundoApellido;

    @Column(name = "person_email")
    public String email;

    @Column(name = "person_phone")
    public String telefono;

    @Column(name = "person_pais")
    public String pais;

    public Persona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String nuip, String email, String pais) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nuip = nuip;
        this.email = email;
        this.pais = pais;
    }
}
