package eduessence.iniciar_sesion.mappers;

import eduessence.iniciar_sesion.models.dto.SesionDTO;
import eduessence.iniciar_sesion.models.entity.Sesiones;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ISesionMapper {
    ISesionMapper INSTANCIA = Mappers.getMapper(ISesionMapper.class);
    Sesiones dtoPersistenciaToEntity(SesionDTO sesionDTO);
}
