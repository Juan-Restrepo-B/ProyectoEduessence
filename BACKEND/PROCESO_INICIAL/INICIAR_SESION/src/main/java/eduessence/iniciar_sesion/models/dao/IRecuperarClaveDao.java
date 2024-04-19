package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO;
import eduessence.iniciar_sesion.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRecuperarClaveDao extends JpaRepository<Usuario, Long> {
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO(u.username, u.password, " +
            " p.nuip, e.estado) FROM Usuario u JOIN Persona p " +
            " ON p.idPerson = u.idPersona JOIN Estados e ON e.estado = u.idstate" +
            " WHERE (u.username = :username OR p.nuip = :nuip)")
    List<RecuperarClaveDTO> consultaUsuario(@Param("username") String username, @Param("nuip") String nuip);

    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO(u.username, u.password, " +
            " p.nuip, e.estado) FROM Usuario u JOIN Persona p" +
            " ON p.idPerson = u.idPersona JOIN Estados e ON e.estado = u.idstate" +
            " WHERE u.username = :user")
    List<RecuperarClaveDTO> consultaUsuario2(@Param("user") String user);
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO(u.username, u.password, " +
            " p.nuip, e.estado) FROM Usuario u JOIN Persona p" +
            " ON p.idPerson = u.idPersona JOIN Estados e ON e.estado = u.idstate" +
            " WHERE (u.username = :username OR p.nuip = :nuip)")
    List<RecuperarClaveDTO> consultaEstado(@Param("username") String username, @Param("nuip") String nuip);
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO(u.username, u.password, " +
            " p.nuip, e.estado) FROM Usuario u JOIN Persona p" +
            " ON p.idPerson = u.idPersona JOIN Estados e ON e.estado = u.idstate" +
            " WHERE u.password = :password ")
    List<RecuperarClaveDTO> consultaPassword(@Param("password") String password);
    @Modifying
    @Query(" UPDATE Usuario u SET u.password = :password WHERE u.username = :user ")
    void actualizarClave(@Param("password") String password, @Param("user") String user);
}