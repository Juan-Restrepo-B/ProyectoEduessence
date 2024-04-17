package eduessence.iniciar_sesion.controllers;

import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.LoginRequest;
import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.security.AuthResponse;
import eduessence.iniciar_sesion.models.service.IInicioSesionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class IniciarSesionController {

    @Autowired
    private IInicioSesionService inicioSesionService;

    @PostMapping("iniciar-sesion")
    public ResponseEntity<AuthResponse> iniciarSesion(@RequestBody InicioSesionDTO registroRequest,
                                                      SesionDTO sesionRequest, LoginRequest loginRequest) {
        return inicioSesionService.iniciarSesion(registroRequest, sesionRequest, loginRequest);
    }
}
