package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.mappers.ISesionMapper;
import eduessence.registro_cliente.models.dao.ISesionDao;
import eduessence.registro_cliente.models.dto.SesionDTO;
import eduessence.registro_cliente.models.entity.Sesiones;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class SesionServiceImpl implements ISesionService{
    private final ISesionDao sesionDao;

    @Override
    public Sesiones save(SesionDTO sesion) {
        Sesiones logEntity = ISesionMapper.INSTANCIA.dtoPersistenciaToEntity(sesion);
        return sesionDao.save(logEntity);
    }

    @Override
    public List<Sesiones> findAll() {
        return sesionDao.findAll();
    }
}