package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.mappers.IlogNegocioMapper;
import eduessence.registro_cliente.models.dao.ILogNegocioDao;
import eduessence.registro_cliente.models.dto.LogNegocioDTO;
import eduessence.registro_cliente.models.entity.LogNegocio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LogNegocioServiceImpl implements ILogNegocioService{
    private final ILogNegocioDao logNegocioDao;

    @Override
    public LogNegocio save(LogNegocioDTO logNegocio) {
        LogNegocio logEntityN = IlogNegocioMapper.INSTANCIA.dtoPersistenciaToEntity(logNegocio);
        return logNegocioDao.save(logEntityN);
    }

    @Override
    public List<LogNegocio> findAll() {
        return logNegocioDao.findAll();
    }
}
