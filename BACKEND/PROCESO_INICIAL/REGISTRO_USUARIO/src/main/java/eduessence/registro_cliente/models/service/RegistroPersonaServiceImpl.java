package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dao.IRegistroPersonaDao;
import eduessence.registro_cliente.models.dto.RegistroUsuarioDTO;
import eduessence.registro_cliente.models.entity.Persona;
import eduessence.registro_cliente.models.exception.BusinessException;
import eduessence.registro_cliente.models.exception.RequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistroPersonaServiceImpl implements IRegistroPersonaService {

    private final IRegistroPersonaDao personaRepository;
    @Override
    public Persona registrarPersona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                                    String nuip, String email, String pais) {
        Persona persona = new Persona(primerNombre, segundoNombre, primerApellido, segundoApellido,
                nuip, email, pais);
        return personaRepository.save(persona);
    }
    @Override
    public Persona save(Persona persona) {
        if (existsByEmail(persona.getEmail())) {
            throw new BusinessException("El correo electrónico ya está en uso.", "P-502");
        }
        return personaRepository.save(persona);
    }
    @Override
    public void delete(Persona persona) {
        personaRepository.delete(persona);
    }
    @Override
    public boolean isValidEmailAddress(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public boolean isValidLongitudCampo(String password, int longitudMinima, int longitudMaxima) {
        if (password == null || password.length() < longitudMinima || password.length() > longitudMaxima) {
            return false;
        }
        return true;
    }
    public void validacionError(RegistroUsuarioDTO registroRequest){
        if (registroRequest.getPrimerNombre().equals("") || registroRequest.getPrimerNombre() == null ) {
            throw new RequestException("P-401", "El primer Nombre es obligatorio.");
        }
        if (registroRequest.getPrimerApellido().equals("") || registroRequest.getPrimerApellido() == null ) {
            throw new RequestException("P-402", "El primer Apellido es obligatorio.");
        }
        if (registroRequest.getNuip().equals("") || registroRequest.getNuip() == null ) {
            throw new RequestException("P-403", "El NUIP es obligatorio.");
        }
        if (registroRequest.getEmail().equals("") || registroRequest.getEmail() == null ) {
            throw new RequestException("P-404", "El Email es obligatorio.");
        }
        if (registroRequest.getPais().equals("") || registroRequest.getPais() == null ) {
            throw new RequestException("P-405", "El Pais es obligatorio.");
        }
        if (!isValidEmailAddress(registroRequest.getEmail())) {
            throw new BusinessException("Ingrese un Email con formato valido.","P-501");
        }
        if (!isValidLongitudCampo(registroRequest.getPassword(), 6, 20)) {
            throw new BusinessException("La longitud del campo 'Password' debe estar entre 6 y 20 caracteres.", "P-502");
        }
    }
    public boolean existsByEmail(String email) {
        return personaRepository.existsByEmail(email);
    }
}