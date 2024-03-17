package eduessence.iniciar_sesion.controllers;

import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.service.IInicioSesionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/iniciar-sesion")
public class IniciarSesionController {

    @Autowired
    private IInicioSesionService inicioSesionService;

    @PostMapping("")
    public ResponseEntity<String> iniciarSesion(@Valid @RequestBody InicioSesionDTO registroRequest,
                                                SesionDTO sesionRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Error en la solicitud");
        }
        return inicioSesionService.iniciarSesion(registroRequest, sesionRequest);
    }
}
