package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.LoginRequest;
import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.security.AuthResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInicioSesionService {
    ResponseEntity<AuthResponse> iniciarSesion(InicioSesionDTO registroRequest, SesionDTO sesionRequest,
                                         LoginRequest loginRequest);
    boolean validarPassword(String passwordAlmacenada, String passwordIngresada);
    ResponseEntity<String> validacionError(InicioSesionDTO registroRequest,SesionDTO sesionRequest);
    List<InicioSesionDTO> consultaUsuario(String nombreUsuario);
    List<InicioSesionDTO> consultaPassword(String passwordValidada);
    List<InicioSesionDTO> consultaEstado(String estado, String nombreUsuario);
    List<SesionDTO> consultaSesion(String estadoSesion, String nombreUsuario);
    void actualizarSesion(String nombreUsuario, String estadoSesion, String ipSesion);
    ResponseEntity<AuthResponse> login(LoginRequest loginRequest);
}
