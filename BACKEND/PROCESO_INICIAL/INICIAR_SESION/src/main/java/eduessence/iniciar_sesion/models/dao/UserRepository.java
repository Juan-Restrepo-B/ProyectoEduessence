package eduessence.iniciar_sesion.models.dao;

import eduessence.iniciar_sesion.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
