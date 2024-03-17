package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.dao.IInicioSesionDao;
import eduessence.iniciar_sesion.models.dao.ISesionDao;
import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.LogNegocioDTO;
import eduessence.iniciar_sesion.models.dto.LogUsuarioDTO;
import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.exception.RequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;
    private final ISesionDao sesionDao;

    @Override
    public ResponseEntity<String> iniciarSesion(InicioSesionDTO registroRequest, SesionDTO sesionRequest) {
        validacionError(registroRequest, sesionRequest);

        LogUsuarioDTO logUsuario = new LogUsuarioDTO("inicioExistoso", "Usuario " +
                registroRequest.getNombreUsuario() + " ha iniciado Sesion Correctamente.");
        logUsuarioService.save(logUsuario);
        LogNegocioDTO logNegocio = new LogNegocioDTO("CU0000", "UPDATE",
                "Usuario " + registroRequest.getNombreUsuario() + " ha iniciado sesion exitosamente."
                ,"EXITOSO");
        logNegocioService.save(logNegocio);

        actualizarSesion(registroRequest.getNombreUsuario(), "ACTIVO", request.getRemoteAddr());

        return ResponseEntity.ok("Inicio de Sesion Exitoso");
    }


    @Override
    public boolean validarPassword(String passwordAlmacenada, String passwordIngresada) {
        return passwordEncoder.matches(passwordIngresada, passwordAlmacenada);
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
            if (registroRequest.getNombreUsuario() == null || registroRequest.getNombreUsuario().isEmpty()) {
                throw new RequestException("P-408", "El Usuario es obligatorio.");
            }
            if (registroRequest.getPassword() == null || registroRequest.getPassword().isEmpty()) {
                throw new RequestException("P-409", "La Password es obligatoria.");
            }
            List<InicioSesionDTO> usuarios = consultaUsuario(registroRequest.getNombreUsuario());
            if (usuarios.isEmpty()) {
                throw new RequestException("P-410", "El usuario " + registroRequest.getNombreUsuario() +
                        " no se encuentra registrado.");
            }
            String passwordAlmacenada = usuarios.get(0).getPassword();
            String passwordIngresada = registroRequest.getPassword();
            if (!validarPassword(passwordAlmacenada, passwordIngresada)) {

                LogUsuarioDTO logUsuario = new LogUsuarioDTO("inicioFallido",
                        "La contraseña no coincide.");
                logUsuarioService.save(logUsuario);
                LogNegocioDTO logNegocio = new LogNegocioDTO("CU0000", registroRequest.getNombreUsuario(),
                        "La contraseña no coincide","FALLIDO");
                logNegocioService.save(logNegocio);

                throw new RequestException("P-411", "La contraseña no coincide, al tercer intento la cuenta se bloquea.");
            }
            List<InicioSesionDTO> estado = consultaEstado(registroRequest.getEstado(), registroRequest.getNombreUsuario());
            if (!estado.isEmpty() && "BLOQUEADO".equals(estado.get(0).getEstado())) {
                throw new RequestException("P-414", "El  usuario " + registroRequest.getNombreUsuario() +
                        " se encuentra en estado BLOQUEADA, ");
            } else if (!estado.isEmpty() && "ELIMINADO".equals(estado.get(0).getEstado())) {
                throw new RequestException("P-415", "El usuario " + registroRequest.getNombreUsuario() +
                        " fue Eliminado.");
            } else if (estado.isEmpty() || !"ACTIVO".equals(estado.get(0).getEstado())) {
                throw new RequestException("P-412", "El Usuario no se encuentra en estado ACTIVO.");
            }
            List<SesionDTO> estadoSesion = consultaSesion(sesionRequest.getEstadoS(), registroRequest.getNombreUsuario());
            if (!estadoSesion.isEmpty() && "ACTIVO".equals(estadoSesion.get(0).getEstadoS())) {
                throw new RequestException("P-413", "El usuario " + registroRequest.getNombreUsuario() +
                        " cuenta con otra sesión ACTIVA.");
            }

        return ResponseEntity.ok("Inicio de Sesion Exitoso");
    }
}