package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.dto.LogNegocioDTO;
import eduessence.iniciar_sesion.models.entity.LogNegocio;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface ILogNegocioService {
    LogNegocio save(LogNegocioDTO logNegocio);
    List<LogNegocio> findAll();
}
