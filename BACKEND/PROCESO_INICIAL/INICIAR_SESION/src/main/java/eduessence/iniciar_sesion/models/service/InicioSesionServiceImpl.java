package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.dao.IInicioSesionDao;
import eduessence.iniciar_sesion.models.dao.ISesionDao;
import eduessence.iniciar_sesion.models.dto.*;
import eduessence.iniciar_sesion.models.exception.RequestException;
import eduessence.iniciar_sesion.models.security.AuthResponse;
import eduessence.iniciar_sesion.models.security.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InicioSesionServiceImpl implements IInicioSesionService {
    private final ILogUsuarioService logUsuarioService;
    private final ILogNegocioService logNegocioService;
    private final IInicioSesionDao inicioSesionDao;
    private final PasswordEncoder passwordEncode;
    private final HttpServletRequest request;
    private final ISesionDao sesionDao;
    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> iniciarSesion(InicioSesionDTO registroRequest, SesionDTO sesionRequest,
                                                LoginRequest loginRequest) {
        //validacionError(registroRequest, sesionRequest);

        LogUsuarioDTO logUsuario = new LogUsuarioDTO("inicioExistoso", "Usuario " +
                registroRequest.getUsername() + " ha iniciado Sesion Correctamente.");
        logUsuarioService.save(logUsuario);
        LogNegocioDTO logNegocio = new LogNegocioDTO("CU0000", "UPDATE",
                "Usuario " + registroRequest.getUsername() + " ha iniciado sesion exitosamente."
                ,"EXITOSO");
        logNegocioService.save(logNegocio);

        actualizarSesion(registroRequest.getUsername(), "ACTIVO", request.getRemoteAddr());

        login(loginRequest);


        return login(loginRequest);
    }
    @Override
    public boolean validarPassword(String passwordAlmacenada, String passwordIngresada) {
        return passwordEncode.matches(passwordIngresada, passwordAlmacenada);
    }
    @Override
    public List<InicioSesionDTO> consultaPassword(String passwordValidada) {
        return inicioSesionDao.consultaPassword(passwordValidada);
    }
    @Override
    public List<InicioSesionDTO> consultaUsuario(String nombreUsuario) {
        return inicioSesionDao.consultaUsuario(nombreUsuario);
    }
    @Override
    public List<InicioSesionDTO> consultaEstado(String estado, String nombreUsuario) {
        return inicioSesionDao.consultaEstado(nombreUsuario);
    }
    @Override
    public List<SesionDTO> consultaSesion(String estadoSesion, String nombreUsuario) {
        return sesionDao.consultaSesion(nombreUsuario);
    }
    public void actualizarSesion(String nombreUsuario, String estadoSesion, String ipSesion){
        sesionDao.actualizarSesion(nombreUsuario, estadoSesion, ipSesion);
    }
    @Override
    public ResponseEntity<String> validacionError(InicioSesionDTO registroRequest, SesionDTO sesionRequest) {
            /*if (registroRequest.getUsername() == null || registroRequest.getUsername().isEmpty()) {
                throw new RequestException("P-408", "El Usuario es obligatorio.");
            }*/
            if (registroRequest.getPassword() == null || registroRequest.getPassword().isEmpty()) {
                throw new RequestException("P-409", "La Password es obligatoria.");
            }
            List<InicioSesionDTO> usuarios = consultaUsuario(registroRequest.getUsername());
            /*if (usuarios.isEmpty()) {
                throw new RequestException("P-410", "El usuario " + registroRequest.getUsername() +
                        " no se encuentra registrado.");
            }*/
            String passwordAlmacenada = usuarios.get(0).getPassword();
            String passwordIngresada = registroRequest.getPassword();
            if (!validarPassword(passwordAlmacenada, passwordIngresada)) {

                LogUsuarioDTO logUsuario = new LogUsuarioDTO("inicioFallido",
                        "La contraseña no coincide.");
                logUsuarioService.save(logUsuario);
                LogNegocioDTO logNegocio = new LogNegocioDTO("CU0000", registroRequest.getUsername(),
                        "La contraseña no coincide","FALLIDO");
                logNegocioService.save(logNegocio);

                throw new RequestException("P-411", "La contraseña no coincide, al tercer intento la cuenta se bloquea.");
            }
            List<InicioSesionDTO> estado = consultaEstado(registroRequest.getIdstate(), registroRequest.getUsername());
            if (!estado.isEmpty() && "BLOQUEADO".equals(estado.get(0).getIdstate())) {
                throw new RequestException("P-414", "El  usuario " + registroRequest.getUsername() +
                        " se encuentra en estado BLOQUEADA, ");
            } else if (!estado.isEmpty() && "ELIMINADO".equals(estado.get(0).getIdstate())) {
                throw new RequestException("P-415", "El usuario " + registroRequest.getUsername() +
                        " fue Eliminado.");
            } else if (estado.isEmpty() || !"ACTIVO".equals(estado.get(0).getIdstate())) {
                throw new RequestException("P-412", "El Usuario no se encuentra en estado ACTIVO.");
            }
            List<SesionDTO> estadoSesion = consultaSesion(sesionRequest.getEstadoS(), registroRequest.getUsername());
            if (!estadoSesion.isEmpty() && "ACTIVO".equals(estadoSesion.get(0).getEstadoS())) {
                throw new RequestException("P-413", "El usuario " + registroRequest.getUsername() +
                        " cuenta con otra sesión ACTIVA.");
            }

        return ResponseEntity.ok("Inicio de Sesion Exitoso");
    }
    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest){
        return ResponseEntity.ok(authService.loggin(loginRequest));
    }
}