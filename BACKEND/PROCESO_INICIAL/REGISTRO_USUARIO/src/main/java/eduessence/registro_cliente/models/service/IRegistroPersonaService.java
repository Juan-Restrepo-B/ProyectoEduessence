package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.models.dto.RegistroUsuarioDTO;
import eduessence.registro_cliente.models.entity.Persona;

public interface IRegistroPersonaService {
    Persona registrarPersona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                             String nuip, String email, String pais);

    Persona save(Persona persona);
    void  delete(Persona persona);

    boolean isValidEmailAddress(String email);

    boolean isValidLongitudCampo(String password, int longitudMinima, int longitudMaxima);

    void validacionError(RegistroUsuarioDTO registroRequest);
}