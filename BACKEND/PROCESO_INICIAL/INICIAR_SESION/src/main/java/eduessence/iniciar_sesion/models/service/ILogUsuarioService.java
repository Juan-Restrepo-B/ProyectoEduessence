package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.dto.LogUsuarioDTO;
import eduessence.iniciar_sesion.models.entity.LogUsuario;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface ILogUsuarioService {
    LogUsuario save(LogUsuarioDTO logUsuario);
    List<LogUsuario> findAll();
}