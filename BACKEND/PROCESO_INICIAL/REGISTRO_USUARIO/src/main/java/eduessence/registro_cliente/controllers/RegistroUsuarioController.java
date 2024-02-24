package eduessence.registro_cliente.controllers;

import eduessence.registro_cliente.models.dto.RegistroUsuarioDTO;
import eduessence.registro_cliente.models.service.IRegistroPersonaService;
import eduessence.registro_cliente.models.service.IRegistroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud");
        }
        return usuarioRepository.crearEmpleado(registroRequest);
    }

    @PostMapping("/crear-cliente")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody RegistroUsuarioDTO registroRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Error en la solicitud");
        }
        return usuarioRepository.crearUsuario(registroRequest);
    }

}

