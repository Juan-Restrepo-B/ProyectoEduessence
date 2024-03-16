package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.entity.LogUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogUsuarioDao extends JpaRepository<LogUsuario, Long> {

}
