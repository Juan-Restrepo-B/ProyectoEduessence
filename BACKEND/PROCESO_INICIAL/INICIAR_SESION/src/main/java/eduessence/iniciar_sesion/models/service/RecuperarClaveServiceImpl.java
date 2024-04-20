package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.models.Code.TokenCache;
import eduessence.iniciar_sesion.models.SendMail.Authenticate;
import eduessence.iniciar_sesion.models.dao.IRecuperarClaveDao;
import eduessence.iniciar_sesion.models.dao.ISendMailDao;
import eduessence.iniciar_sesion.models.dto.*;
import eduessence.iniciar_sesion.models.exception.RequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class RecuperarClaveServiceImpl implements IRecuperarClaveService {
    private final IRecuperarClaveDao recuperarClaveDao;
    private final ISendMailDao sendMailDao;
    private final PasswordEncoder passwordEncode;
    private final TokenCache tokenCache;
    private final Authenticate authenticate;
    @Override
    public ResponseEntity<String> recuperarPasswordP1(RecuperarClaveDTO registroRequest) {
        validacionError(registroRequest);

        List<RecuperarClaveDTO> usuarios = consultaUsuario(registroRequest.getUsername(), registroRequest.getNuip());
        RecuperarClaveDTO usuarioEncontrado = usuarios.get(0);
        String usuario = formatoRespuestaUsuario(usuarioEncontrado);

        List<SendEmailDTO> emails = consultaEmail(registroRequest.getUsername(), registroRequest.getNuip());
        SendEmailDTO emailEncontrado = emails.get(0);
        String email = formatoRespuestaEmail(emailEncontrado);

        List<SendEmailDTO> persons = consultaPersona(registroRequest.getUsername(), registroRequest.getNuip());
        SendEmailDTO person = persons.get(0);
        String nombreApellido = formatoRespuestaPersona(person);

        String token = generateToken(registroRequest.getUsername());

        authenticate.sendMensagger(email, token, usuario, nombreApellido);

        return ResponseEntity.ok(usuario + " / Code: " + token);
    }
    @Override
    public ResponseEntity<String> recuperarPasswordP2(RecuperarClaveDTO registroRequest, String user) {
        validacionError2(registroRequest, user);

        String token = registroRequest.getCode();
        validateToken(token, user);

        String password = registroRequest.getPassword2();
        actualizarClave(user, password);

        return ResponseEntity.ok("La contraseña ha sido cambiada exitosamente.");
    }
    public void actualizarClave(String user, String password){
        recuperarClaveDao.actualizarClave(passwordEncode.encode(password), user);
        recuperarClaveDao.flush();
    }

    @Override
    public List<SendEmailDTO> consultaEmail(String username, String nuip) {
        return sendMailDao.consultaEmail(username, nuip);
    }
    @Override
    public List<SendEmailDTO> consultaPersona(String username, String nuip) {
        return sendMailDao.consultaEmail(username, nuip);
    }
    @Override
    public List<RecuperarClaveDTO> consultaUsuario(String username, String nuip) {
        return recuperarClaveDao.consultaUsuario(username, nuip);
    }
    @Override
    public List<RecuperarClaveDTO> consultaUsuario2(String user) {
        return recuperarClaveDao.consultaUsuario2(user);
    }
    @Override
    public List<RecuperarClaveDTO> consultaEstado(String estado, String username, String nuip) {
        return recuperarClaveDao.consultaEstado(username, nuip);
    }
    @Override
    public String formatoRespuestaUsuario(RecuperarClaveDTO usuario) {
        return String.format("Usuario: %s",
                usuario.getUsername());
    }
    @Override
    public String formatoRespuestaEmail(SendEmailDTO usuario) {
        return String.format(usuario.getEmail());
    }
    @Override
    public String formatoRespuestaPersona(SendEmailDTO usuario) {
        return String.format(usuario.getPrimerNombre() + " " + usuario.getSegundoNombre() + " " +
                usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
    }
    @Override
    public String generateToken(String user) {
        return tokenCache.createToken(user);
    }

    @Override
    public void validateToken(String token, String user) {
        tokenCache.validateToken(token, user);
    }
    @Override
    public boolean validarPassword(String passwordAlmacenada, String passwordIngresada) {
        return passwordEncode.matches(passwordIngresada, passwordAlmacenada);
    }
    @Override
    public List<RecuperarClaveDTO> consultaPassword(String passwordValidada) {
        return recuperarClaveDao.consultaPassword(passwordValidada);
    }
    @Override
    public ResponseEntity<String> validacionError(RecuperarClaveDTO registroRequest) {

        if ((registroRequest.getUsername() == null || registroRequest.getUsername().isEmpty()) &&
                (registroRequest.getNuip() == null || registroRequest.getNuip().isEmpty())) {
            throw new RequestException("P-408", "Al menos uno de los campos (Usuario o NUIP) es obligatorio.");
        }

        List<RecuperarClaveDTO> usuarios = consultaUsuario(registroRequest.getUsername(), registroRequest.getNuip());
        if (usuarios.isEmpty()) {
            throw new RequestException("P-409", "El usuario " + registroRequest.getUsername() +
                    " no se encuentra registrado.");
        }

        List<RecuperarClaveDTO> estado = consultaEstado(registroRequest.getIdstate(), registroRequest.getUsername(),
                                                    registroRequest.getNuip());
        if (!estado.isEmpty() && !"ACTIVO".equals(estado.get(0).getIdstate())) {
            throw new RequestException("P-410", "El  usuario " + registroRequest.getUsername() +
                    " se encuentra en estado ACTIVO.");
        }

        return ResponseEntity.ok(registroRequest.getUsername());
    }
    @Override
    public ResponseEntity<String> validacionError2(RecuperarClaveDTO registroRequest, String user) {
        List<RecuperarClaveDTO> usuarios = consultaUsuario2(user);
        if (usuarios.isEmpty()) {
            throw new RequestException("P-409", "Usuario no encontrado.");
        }

        String passwordAlmacenada = usuarios.get(0).getPassword();
        String passwordIngresada = registroRequest.getPassword();
        if (validarPassword(passwordAlmacenada, passwordIngresada)) {
            throw new RequestException("P-411", "La contraseña ingresada no debe ser igual a la contraseña anterior.");
        }

        String password = registroRequest.getPassword();
        String password2 = registroRequest.getPassword2();
        if (!password.equals(password2)) {
            throw new RequestException("P-412", "Las contraseñas no coinciden.");
        }

        if (registroRequest.getCode().isEmpty()) {
            throw new RequestException("P-413", "El codigo de validacion es obligatorio.");
        }

        return ResponseEntity.ok("Validación exitosa. Usuario: " + user);
    }
}
