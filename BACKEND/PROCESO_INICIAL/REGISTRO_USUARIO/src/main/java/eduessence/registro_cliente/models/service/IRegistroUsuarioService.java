package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.RegistroUsuarioDTO;
import eduessence.registro_cliente.models.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRegistroUsuarioService {
        Usuario save(Usuario usuario);
        List<RegistroUsuarioDTO> consultaUsuario(String nombreUsuario);
        String generarNombreEmpleado(String nombre, String nombredos, String apellido);
        String generarNombreUsuario(String nombre, String apellido, String nuip);
        ResponseEntity<String> crearUsuario(RegistroUsuarioDTO registroRequest);
        ResponseEntity<String> crearEmpleado(RegistroUsuarioDTO registroRequest);
}