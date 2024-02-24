package eduessence.registro_cliente.models.dao;

import eduessence.registro_cliente.models.dto.RegistroUsuarioDTO;
import eduessence.registro_cliente.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRegistroUsuarioDao extends JpaRepository<Usuario, Long> {
    @Query("SELECT NEW eduessence.registro_cliente.models.dto.RegistroUsuarioDTO(u.nombreUsuario) FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    List<RegistroUsuarioDTO> consultaUsuario(@Param("nombreUsuario") String nombreUsuario);
}
