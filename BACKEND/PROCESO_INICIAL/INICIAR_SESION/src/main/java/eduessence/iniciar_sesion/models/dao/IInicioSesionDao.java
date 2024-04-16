package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInicioSesionDao extends JpaRepository<Usuario, Long> {
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.InicioSesionDTO(u.nombreUsuario, u.password, " +
            " te.nametipoClioente , r.nameRol, e.estado ) FROM Usuario u JOIN TipoEmpleado te" +
            " ON te.nametipoClioente = u.idTipoCliente LEFT JOIN Rol r " +
            " ON r.idRole = u.idRol JOIN Estados e ON e.estado = u.idstate" +
            " WHERE u.nombreUsuario = :nombreUsuario ")
    List<InicioSesionDTO> consultaUsuario(@Param("nombreUsuario") String nombreUsuario);
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.InicioSesionDTO(u.nombreUsuario, u.password, " +
            " te.nametipoClioente , r.nameRol, e.estado ) FROM Usuario u JOIN TipoEmpleado te" +
            " ON te.nametipoClioente = u.idTipoCliente LEFT JOIN Rol r " +
            " ON r.idRole = u.idRol JOIN Estados e ON e.estado = u.idstate" +
            " WHERE u.password = :password ")
    List<InicioSesionDTO> consultaPassword(@Param("password") String password);
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.InicioSesionDTO(u.nombreUsuario, u.password, " +
            " te.nametipoClioente , r.nameRol, e.estado ) FROM Usuario u JOIN TipoEmpleado te" +
            " ON te.nametipoClioente = u.idTipoCliente LEFT JOIN Rol r " +
            " ON r.idRole = u.idRol JOIN Estados e ON e.estado = u.idstate" +
            " WHERE u.nombreUsuario = :nombreUsuario")
    List<InicioSesionDTO> consultaEstado(@Param("nombreUsuario") String nombreUsuario);
}