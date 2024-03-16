package eduessence.iniciar_sesion.models.service;

import eduessence.iniciar_sesion.mappers.ISesionMapper;
import eduessence.iniciar_sesion.models.dao.ISesionDao;
import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.entity.Sesiones;
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