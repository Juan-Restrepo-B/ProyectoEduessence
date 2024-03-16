package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.SesionDTO;
import eduessence.registro_cliente.models.entity.Sesiones;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface ISesionService {
    Sesiones save(SesionDTO sesion);
    List<Sesiones> findAll();
}