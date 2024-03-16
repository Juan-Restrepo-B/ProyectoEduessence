package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.entity.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISesionDao extends JpaRepository<Sesiones, Long> {
}