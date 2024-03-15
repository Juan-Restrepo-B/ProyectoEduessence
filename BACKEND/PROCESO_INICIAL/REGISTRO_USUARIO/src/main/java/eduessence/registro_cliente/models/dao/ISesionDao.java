package eduessence.registro_cliente.models.dao;

import eduessence.registro_cliente.models.entity.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISesionDao extends JpaRepository<Sesiones, Long> {
}
