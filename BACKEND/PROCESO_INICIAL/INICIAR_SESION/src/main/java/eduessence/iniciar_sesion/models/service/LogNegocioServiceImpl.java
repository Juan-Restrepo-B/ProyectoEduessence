package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.mappers.ILogNegocioMapper;
import eduessence.iniciar_sesion.models.dao.ILogNegocioDao;
import eduessence.iniciar_sesion.models.dto.LogNegocioDTO;
import eduessence.iniciar_sesion.models.entity.LogNegocio;
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
        LogNegocio logEntityN = ILogNegocioMapper.INSTANCIA.dtoPersistenciaToEntity(logNegocio);
        return logNegocioDao.save(logEntityN);
    }
    @Override
    public List<LogNegocio> findAll() {
        return logNegocioDao.findAll();
    }
}