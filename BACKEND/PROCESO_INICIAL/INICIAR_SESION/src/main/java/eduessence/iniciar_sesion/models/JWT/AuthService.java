package eduessence.iniciar_sesion.models.JWT;

import eduessence.iniciar_sesion.models.dao.IInicioSesionDao;
import eduessence.iniciar_sesion.models.dto.InicioSesionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IInicioSesionDao userRepositor;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse loggin(InicioSesionDTO loginRequest) {
        try {
            System.out.println("Intentando autenticar al usuario: " + loginRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            UserDetails user = (UserDetails) userRepositor.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
            String token = jwtService.getToken(user);
            System.out.println("Autenticación exitosa para el usuario: " + loginRequest.getUsername());
            return AuthResponse.builder().token(token).build();
        } catch (AuthenticationException e) {
            System.err.println("Error de autenticación para el usuario: " + loginRequest.getUsername() + " - " + e.getMessage());
            throw new BadCredentialsException("Usuario o contraseña invalido.");
        } catch (Exception e) {
            System.err.println("Error durante el proceso de inicio de sesión: " + e.getMessage());
            throw e;
        }
    }
}
