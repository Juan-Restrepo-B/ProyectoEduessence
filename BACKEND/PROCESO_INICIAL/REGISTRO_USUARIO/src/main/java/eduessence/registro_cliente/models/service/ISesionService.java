package eduessence.registro_cliente.models.service;


import eduessence.registro_cliente.models.entity.Sesiones;
import jakarta.transaction.Transactional;

import java.util.List;
@Transactional
public interface ISesionService {

    Sesiones save();

    List<Sesiones> findAll();
}
