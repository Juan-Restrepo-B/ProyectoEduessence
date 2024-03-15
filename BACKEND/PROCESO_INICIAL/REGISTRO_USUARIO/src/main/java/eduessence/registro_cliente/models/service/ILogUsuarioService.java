package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.LogUsuarioDTO;
import eduessence.registro_cliente.models.entity.LogUsuario;
import jakarta.transaction.Transactional;

import java.util.List;
@Transactional
public interface ILogUsuarioService {

     LogUsuario save(LogUsuarioDTO logUsuario);

     List<LogUsuario> findAll();
}
