package eduessence.iniciar_sesion.controllers;

import eduessence.iniciar_sesion.models.Code.TokenCache;
import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import eduessence.iniciar_sesion.models.dto.RecuperarClaveDTO;
import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.JWT.AuthResponse;
import eduessence.iniciar_sesion.models.service.IInicioSesionService;
import eduessence.iniciar_sesion.models.service.IRecuperarClaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class IniciarSesionController {

    @Autowired
    private IInicioSesionService inicioSesionService;
    @Autowired
    private IRecuperarClaveService recuperarClaveService;
    @Autowired
    private TokenCache tokenCache;

    @Autowired
    public void setTokenCache(TokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    @PostMapping("iniciar-sesion")
    public ResponseEntity<AuthResponse> iniciarSesion(@RequestBody InicioSesionDTO registroRequest,
                                                      SesionDTO sesionRequest) {
        return inicioSesionService.iniciarSesion(registroRequest, sesionRequest);
    }

    @PostMapping("recuperar-password/p1")
    public ResponseEntity<String> recuperarPasswordP1(@RequestBody RecuperarClaveDTO registroRequest) {
        return recuperarClaveService.recuperarPasswordP1(registroRequest);
    }
    @PostMapping("/recuperar-password/p2/{user}")
    public ResponseEntity<String> recuperarPasswordP2(@PathVariable String user,
                                                      @RequestBody RecuperarClaveDTO registroRequest) {
        return recuperarClaveService.recuperarPasswordP2(registroRequest, user);
    }

}
