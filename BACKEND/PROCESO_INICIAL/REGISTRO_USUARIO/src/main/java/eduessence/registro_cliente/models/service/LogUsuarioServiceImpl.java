package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.mappers.ILogUsuarioMapper;
import eduessence.registro_cliente.models.dao.ILogUsuarioDao;
import eduessence.registro_cliente.models.dto.LogUsuarioDTO;
import eduessence.registro_cliente.models.entity.LogUsuario;
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