package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.entity.LogNegocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogNegocioDao extends JpaRepository<LogNegocio, Long> {

}
