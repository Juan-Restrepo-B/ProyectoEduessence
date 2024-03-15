package eduessence.registro_cliente.models.service;

import eduessence.registro_cliente.mappers.ISesionMapper;
import eduessence.registro_cliente.models.dao.ISesionDao;
import eduessence.registro_cliente.models.dto.SesionDTO;
import eduessence.registro_cliente.models.entity.Sesion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class SesionServiceImpl implements ISesionService{
    private final ISesionDao sesionDao;

    @Override
    public Sesion save(SesionDTO sesion) {
        Sesion logEntity = ISesionMapper.INSTANCIA.dtoPersistenciaToEntity(sesion);
        return sesionDao.save(logEntity);
    }

    @Override
    public List<Sesion> findAll() {
        return sesionDao.findAll();
    }
}