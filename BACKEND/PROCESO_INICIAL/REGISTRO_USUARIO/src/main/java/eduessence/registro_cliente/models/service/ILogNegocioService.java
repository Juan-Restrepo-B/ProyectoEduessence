package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.LogNegocioDTO;
import eduessence.registro_cliente.models.entity.LogNegocio;

import java.util.List;

public interface ILogNegocioService {
    LogNegocio save(LogNegocioDTO logNegocio);

    List<LogNegocio> findAll();
}
