package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.controllers.SecurityConfig;
import eduessence.registro_cliente.models.SendMail.Authenticate;
import eduessence.registro_cliente.models.dao.IRegistroUsuarioDao;
import eduessence.registro_cliente.models.dao.IRolDao;
import eduessence.registro_cliente.models.dto.*;
import eduessence.registro_cliente.models.entity.Persona;
import eduessence.registro_cliente.models.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistroUsuarioServiceImpl implements IRegistroUsuarioService {

    private final SecurityConfig configSegurity;
    private final IRegistroUsuarioDao usuarioRepository;
    private final IRolDao rolDao;
    private final IRegistroPersonaService personaRepository;
    private final ILogUsuarioService logUsuarioService;
    private final ILogNegocioService logNegocioService;
    private final ISesionService iSesionService;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;
    private final Authenticate authenticate;

    @Override
    public Usuario save(Usuario usuario) {
        String encoderPassword = this.passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encoderPassword);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<RegistroUsuarioDTO> consultaUsuario(String nombreUsuario) {
        return usuarioRepository.consultaUsuario(nombreUsuario);
    }
    @Override
    public List<RolDTO> consultaRol(String role) {
        return rolDao.consultaRol(role);
    }
    @Override
    public String formatoRespuestaRol(RolDTO usuario) {
        return String.format(usuario.getNameRol());
    }
    @Override
    public String generarNombreEmpleado(String nombre, String nombredos, String apellido) {
        String nombreEmpleado = nombre.substring(0, 1) + nombredos.substring(0, 1) + apellido;
        return nombreEmpleado;
    }

    @Override
    public String generarNombreUsuario(String nombre, String apellido, String nuip) {

        String nombreUsuario = nombre.substring(0, 2) + apellido.substring(0, 0) +
                nuip.substring(nuip.length() - 6);

        String nombreUsuarioAlternativo = nombre.substring(0, 1) + apellido.substring(0, 1) +
                nuip.substring(nuip.length() - 6);

        List<RegistroUsuarioDTO> usuarios = consultaUsuario(nombreUsuario);
        if (usuarios != null && !usuarios.isEmpty()) {
            return nombreUsuarioAlternativo;
        } else {
            return nombreUsuario;
        }
    }

    @Override
    public ResponseEntity<String> crearUsuario(RegistroUsuarioDTO registroRequest) {
        personaRepository.validacionError(registroRequest);

        String nombreUsuario = generarNombreUsuario(registroRequest.getPrimerNombre(),
                registroRequest.getPrimerApellido(), registroRequest.getNuip());

        Persona persona = new Persona(registroRequest.getPrimerNombre(), registroRequest.getSegundoNombre(),
                registroRequest.getPrimerApellido(), registroRequest.getSegundoApellido(),
                registroRequest.getNuip(), registroRequest.getEmail(), registroRequest.getPais());
        personaRepository.save(persona);

        List<RegistroUsuarioDTO> usuarios = consultaUsuario(nombreUsuario);
        if (!usuarios.isEmpty()) {
            String nombreUsuarioAlternativo = generarNombreUsuario(registroRequest.getPrimerNombre(),
                    registroRequest.getPrimerApellido(), registroRequest.getNuip());
            Usuario usuario = new Usuario(nombreUsuarioAlternativo, registroRequest.getPassword(), persona.getIdPerson(),
                    registroRequest.getIdTipoCliente(), registroRequest.getIdRol(), registroRequest.getIdState());
            try {
                save(usuario);
                LogUsuarioDTO logUsuario = new LogUsuarioDTO("creacionExistoso",
                        "Usuario creado usuario exitosamente puede ingresar con el nombre de usuario: "
                                + nombreUsuarioAlternativo);
                logUsuarioService.save(logUsuario);

                LogNegocioDTO logNegocio = new LogNegocioDTO("CU0002", "INSERT",
                        "Usuario creado usuario exitosamente puede ingresar con el nombre de usuario: "
                                + nombreUsuarioAlternativo,"EXITOSO");
                logNegocioService.save(logNegocio);

                String ip = request.getRemoteAddr();
                SesionDTO sesion = new SesionDTO(usuario.idUser, nombreUsuarioAlternativo, ip,"INACTIVO");
                iSesionService.save(sesion);

                String email = registroRequest.getEmail();
                String username = nombreUsuarioAlternativo;
                String nombreApellido = (registroRequest.getPrimerNombre() + " " + registroRequest.getSegundoNombre()
                        + " " + registroRequest.getPrimerApellido() + " " + registroRequest.getSegundoApellido());
                authenticate.sendMensaggerUser(email, username, nombreApellido);

                return ResponseEntity.ok("Usuario creado exitosamente puede ingresar con el nombre de usuario:  "
                        + nombreUsuarioAlternativo);
            } catch (DataIntegrityViolationException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

                LogUsuarioDTO logUsuario = new LogUsuarioDTO("creacionFallido",
                        "Error al crear el usuario. El nombre de usuario ya está en uso.");
                logUsuarioService.save(logUsuario);

                LogNegocioDTO logNegocio = new LogNegocioDTO("CU0002", "INSERT",
                        "Error al crear el usuario. El nombre de usuario ya está en uso.",
                        "FALLIDO");
                logNegocioService.save(logNegocio);

                personaRepository.delete(persona);

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al crear el usuario. El nombre de usuario ya está en uso.");
            }
        } else {
            Usuario usuario = new Usuario(nombreUsuario, registroRequest.getPassword(), persona.getIdPerson(),
                    registroRequest.getIdTipoCliente(), registroRequest.getIdRol(), registroRequest.getIdState());
            try {
                save(usuario);
                LogUsuarioDTO logUsuario = new LogUsuarioDTO("creacionExistoso",
                        "Usuario creado usuario exitosamente puede ingresar con el nombre de usuario: "
                                + nombreUsuario);

                logUsuarioService.save(logUsuario);
                LogNegocioDTO logNegocio = new LogNegocioDTO("CU0002", "INSERT",
                        "Usuario creado usuario exitosamente puede ingresar con el nombre de usuario: "
                                + nombreUsuario,"EXITOSO");
                logNegocioService.save(logNegocio);

                String ip = request.getRemoteAddr();
                SesionDTO sesion = new SesionDTO(usuario.idUser, nombreUsuario, ip,"INACTIVO");
                iSesionService.save(sesion);

                String email = registroRequest.getEmail();
                String username = nombreUsuario;
                String nombreApellido = (registroRequest.getPrimerNombre() + " " + registroRequest.getSegundoNombre()
                        + " " + registroRequest.getPrimerApellido() + " " + registroRequest.getSegundoApellido());
                authenticate.sendMensaggerUser(email, username, nombreApellido);

                return ResponseEntity.ok("Usuario creado exitosamente puede ingresar con el nombre de usuario: "
                        + nombreUsuario);
            } catch (DataIntegrityViolationException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

                personaRepository.delete(persona);

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al crear el usuario. El nombre de usuario ya está en uso.");
            }
        }
    }

    @Override
    public ResponseEntity<String> crearEmpleado(RegistroUsuarioDTO registroRequest) {
        personaRepository.validacionError(registroRequest);

        String nombreEmpleado = generarNombreEmpleado(registroRequest.getPrimerNombre(), registroRequest.getSegundoNombre(),
                registroRequest.getPrimerApellido());

        Persona persona = new Persona(registroRequest.getPrimerNombre(), registroRequest.getSegundoNombre(),
                registroRequest.getPrimerApellido(), registroRequest.getSegundoApellido(),
                registroRequest.getNuip(), registroRequest.getEmail(), registroRequest.getPais());
        personaRepository.save(persona);


        Usuario usuario = new Usuario(nombreEmpleado,registroRequest.getPassword(), persona.getIdPerson(),
                registroRequest.getIdTipoCliente(), registroRequest.getIdRol(), registroRequest.getIdState());

        try {
            save(usuario);

            LogUsuarioDTO logUsuario = new LogUsuarioDTO("creacionExistoso",
                    "Usuario creado empleado exitosamente puede ingresar con el nombre de usuario: "
                            + nombreEmpleado);
            logUsuarioService.save(logUsuario);

            LogNegocioDTO logNegocio = new LogNegocioDTO("CU0003", "INSERT",
                    "Usuario creado empleado exitosamente puede ingresar con el nombre de usuario: "
                            + nombreEmpleado,"EXITOSO");
            logNegocioService.save(logNegocio);

            String ip = request.getRemoteAddr();
            SesionDTO sesion = new SesionDTO(usuario.idUser, nombreEmpleado, ip,"INACTIVO");
            iSesionService.save(sesion);

            String email = registroRequest.getEmail();
            String username = nombreEmpleado;
            String nombreApellido = (registroRequest.getPrimerNombre() + " " + registroRequest.getSegundoNombre()
                    + " " + registroRequest.getPrimerApellido() + " " + registroRequest.getSegundoApellido());
            String password = registroRequest.getPassword();
            List<RolDTO> role = consultaRol(String.valueOf(registroRequest.getIdRol()));
            RolDTO idRol = role.get(0);
            String rol = formatoRespuestaRol(idRol);
            authenticate.sendMensaggerEmpl(email, username, password, rol, nombreApellido);

            return ResponseEntity.ok("Empleado creado exitosamente puede ingresar con el nombre de usuario: "
                    + nombreEmpleado);
        } catch (DataIntegrityViolationException e) {

            LogUsuarioDTO logUsuarioFallido = new LogUsuarioDTO("creacionFallido",
                    "No se ha podido crear un nuevo empleaodo ");
            logUsuarioService.save(logUsuarioFallido);

            LogNegocioDTO logNegocioFallido = new LogNegocioDTO("CU0003", "INSERT",
                    "No se ha podido crear un nuevo empleaodo ","FALLIDO");
            logNegocioService.save(logNegocioFallido);

            personaRepository.delete(persona);

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Se ha se encuentra el " + nombreEmpleado );
        }
    }

}
