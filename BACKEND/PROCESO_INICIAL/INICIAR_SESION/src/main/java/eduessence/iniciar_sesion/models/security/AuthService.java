package eduessence.iniciar_sesion.models.security;

import eduessence.iniciar_sesion.models.dao.IInicioSesionDao;
import eduessence.iniciar_sesion.models.dto.LoginRequest;
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
    public AuthResponse loggin(LoginRequest loginRequest) {
        try {
            System.out.println("Attempting to authenticate user: " + loginRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            UserDetails user = (UserDetails) userRepositor.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found."));
            String token = jwtService.getToken(user);
            System.out.println("Authentication successful for user: " + loginRequest.getUsername());
            return AuthResponse.builder().token(token).build();
        } catch (AuthenticationException e) {
            System.err.println("Authentication failed for user: " + loginRequest.getUsername() + " - " + e.getMessage());
            throw new BadCredentialsException("Invalid username or password.");
        } catch (Exception e) {
            System.err.println("Error during login process: " + e.getMessage());
            throw e;
        }
    }
}
