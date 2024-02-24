package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.LogUsuarioDTO;
import eduessence.registro_cliente.models.entity.LogUsuario;

import java.util.List;

public interface ILogUsuarioService {

     LogUsuario save(LogUsuarioDTO logUsuario);

    List<LogUsuario> findAll();
}
