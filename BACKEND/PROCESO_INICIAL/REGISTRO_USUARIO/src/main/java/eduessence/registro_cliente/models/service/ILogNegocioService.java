package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.LogNegocioDTO;
import eduessence.registro_cliente.models.entity.LogNegocio;
import jakarta.transaction.Transactional;

import java.util.List;
@Transactional
public interface ILogNegocioService {
    LogNegocio save(LogNegocioDTO logNegocio);

    List<LogNegocio> findAll();
}
