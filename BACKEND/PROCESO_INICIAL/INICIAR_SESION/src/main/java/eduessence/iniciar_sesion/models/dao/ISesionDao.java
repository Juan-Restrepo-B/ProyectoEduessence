package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.entity.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISesionDao extends JpaRepository<Sesiones, Long> {
    @Query("SELECT NEW eduessence.iniciar_sesion.models.dto.SesionDTO(s.estadoS) FROM Sesiones s" +
            " WHERE s.usuarioS = :nombreUsuario ")
    List<SesionDTO> consultaSesion(@Param("nombreUsuario") String nombreUsuario);
    @Modifying
    @Query(" UPDATE Sesiones s SET s.ipSession = :ipSesion, s.estadoS = :estadoSesion " +
            " WHERE s.usuarioS = :nombreUsuario ")
    void actualizarSesion(@Param("nombreUsuario") String nombreUsuario, @Param("estadoSesion") String estadoSesion,
                          @Param("ipSesion") String ipSesion);
}