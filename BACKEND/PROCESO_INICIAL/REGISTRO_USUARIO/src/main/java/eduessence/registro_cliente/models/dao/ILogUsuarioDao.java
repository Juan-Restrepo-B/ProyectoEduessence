package eduessence.registro_cliente.models.dao;

import eduessence.registro_cliente.models.entity.LogUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogUsuarioDao extends JpaRepository<LogUsuario, Long> {

}
