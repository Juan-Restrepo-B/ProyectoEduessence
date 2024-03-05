package eduessence.registro_cliente.models.dao;

import eduessence.registro_cliente.models.entity.LogNegocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogNegocioDao extends JpaRepository<LogNegocio, Long> {

}
