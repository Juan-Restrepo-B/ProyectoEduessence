package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.mappers.ILogUsuarioMapper;
import eduessence.iniciar_sesion.models.dao.ILogUsuarioDao;
import eduessence.iniciar_sesion.models.dto.LogUsuarioDTO;
import eduessence.iniciar_sesion.models.entity.LogUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LogUsuarioServiceImpl implements ILogUsuarioService{
    private final ILogUsuarioDao logUsuarioDao;
    @Override
    public LogUsuario save(LogUsuarioDTO logUsuario) {
        LogUsuario logEntity = ILogUsuarioMapper.INSTANCIA.dtoPersistenciaToEntity(logUsuario);
        return logUsuarioDao.save(logEntity);
    }
    @Override
    public List<LogUsuario> findAll() {
        return logUsuarioDao.findAll();
    }
}