package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.entity.Sesiones;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface ISesionService {
    Sesiones save(SesionDTO sesion);
    List<Sesiones> findAll();
}