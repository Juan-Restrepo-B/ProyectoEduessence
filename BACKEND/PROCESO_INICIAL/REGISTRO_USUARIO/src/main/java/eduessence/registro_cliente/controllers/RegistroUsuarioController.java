package eduessence.registro_cliente.controllers;

import eduessence.registro_cliente.models.dto.RegistroUsuarioDTO;
import eduessence.registro_cliente.models.entity.Persona;
import eduessence.registro_cliente.models.entity.Usuario;
import eduessence.registro_cliente.models.service.IRegistroPersonaService;
import eduessence.registro_cliente.models.service.IRegistroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/registro-usuario")
public class RegistroUsuarioController {

        @Autowired
        private IRegistroPersonaService personaRepository;

        @Autowired
        private IRegistroUsuarioService usuarioRepository;

    @PostMapping("/crear-empleado")
    public ResponseEntity<String> crearEmpleado(@Valid @RequestBody RegistroUsuarioDTO registroRequest, BindingResult bindingResult) {

        personaRepository.validacionError(registroRequest);

        String nombreEmpleado = usuarioRepository.generarNombreEmpleado(registroRequest.getPrimerNombre(), registroRequest.getSegundoNombre(),
                registroRequest.getPrimerApellido());

        Persona persona = new Persona(registroRequest.getPrimerNombre(), registroRequest.getSegundoNombre(),
                registroRequest.getPrimerApellido(), registroRequest.getSegundoApellido(),
                registroRequest.getNuip(), registroRequest.getEmail(), registroRequest.getPais());
        personaRepository.save(persona);


            Usuario usuario = new Usuario(nombreEmpleado,registroRequest.getPassword(), persona.getIdPerson(),
                    registroRequest.getIdTipoCliente(), registroRequest.getIdRol(), 1);
            try {
                usuarioRepository.save(usuario);
                return ResponseEntity.ok("Usuario creado exitosamente puede ingresar con el nombre de usuario: " + nombreEmpleado);
            } catch (DataIntegrityViolationException e) {
                personaRepository.delete(persona);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("a se encuentra el " + nombreEmpleado +" con el Rol <<ROL>> registrado en el sistema");
            }
        }

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody RegistroUsuarioDTO registroRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud");
        }
        return usuarioRepository.crearUsuario(registroRequest);
    }

}

