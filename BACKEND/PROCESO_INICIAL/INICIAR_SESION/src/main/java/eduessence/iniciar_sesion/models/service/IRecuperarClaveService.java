package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.Code.TokenCache;
import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO;
import eduessence.iniciar_sesion.models.dto.SendEmailDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRecuperarClaveService {
    ResponseEntity<String> recuperarPasswordP1(RecuperarClaveDTO registroRequest);
    ResponseEntity<String> recuperarPasswordP2(RecuperarClaveDTO registroRequest, String user);
    List<RecuperarClaveDTO> consultaUsuario(String username, String nuip);
    List<RecuperarClaveDTO> consultaUsuario2(String user);
    List<RecuperarClaveDTO> consultaEstado(String estado, String username, String nuip);
    List<RecuperarClaveDTO> consultaPassword(String passwordValidada);
    boolean validarPassword(String passwordAlmacenada, String passwordIngresada);
    String formatoRespuestaUsuario(RecuperarClaveDTO usuario);
    ResponseEntity<String> validacionError(RecuperarClaveDTO registroRequest);
    ResponseEntity<String> validacionError2(RecuperarClaveDTO registroRequest, String username);
    String generateToken(String user);
    void validateToken(String token, String user);
    void actualizarClave(String user, String password);
    String formatoRespuestaEmail(SendEmailDTO email);
    List<SendEmailDTO> consultaEmail(String username, String nuip);
    List<SendEmailDTO> consultaPersona(String username, String nuip);
    String formatoRespuestaPersona(SendEmailDTO usuario);
}
