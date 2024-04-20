package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO;
import eduessence.iniciar_sesion.models.dto.SendEmailDTO;
import eduessence.iniciar_sesion.models.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISendMailDao extends JpaRepository<Persona, Long> {
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.SendEmailDTO(u.username, p.email, " +
            " p.nuip, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido) FROM Persona p " +
            "JOIN  Usuario u ON p.idPerson = u.idPersona " +
            " WHERE (u.username = :username OR p.nuip = :nuip)")
    List<SendEmailDTO> consultaEmail(@Param("username") String username, @Param("nuip") String nuip);
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.SendEmailDTO(u.username, p.email, " +
            " p.nuip, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido) FROM Persona p " +
            "JOIN  Usuario u ON p.idPerson = u.idPersona " +
            " WHERE (u.username = :username OR p.nuip = :nuip)")
    List<SendEmailDTO> consultaPersona(@Param("username") String username, @Param("nuip") String nuip);
}