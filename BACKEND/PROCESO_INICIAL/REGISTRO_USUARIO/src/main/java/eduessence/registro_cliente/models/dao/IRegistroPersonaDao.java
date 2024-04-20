package eduessence.registro_cliente.models.dao;

import eduessence.registro_cliente.models.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistroPersonaDao extends JpaRepository<Persona, Long> {
    boolean existsByEmail(String email);
}