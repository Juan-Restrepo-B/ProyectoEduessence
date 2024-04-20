package eduessence.registro_cliente.models.dao;

import eduessence.registro_cliente.models.dto.RolDTO;
import eduessence.registro_cliente.models.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRolDao extends JpaRepository<Rol, Long>  {
    @Query("SELECT NEW eduessence.registro_cliente.models.dto.RolDTO(r.idRole, r.nameRol)" +
            " FROM Rol r WHERE r.idRole = :rol")
    List<RolDTO> consultaRol(@Param("rol") String rol);
}
