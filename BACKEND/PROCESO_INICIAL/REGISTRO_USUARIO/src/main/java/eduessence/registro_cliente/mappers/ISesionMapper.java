package eduessence.registro_cliente.mappers;

import eduessence.registro_cliente.models.dto.SesionDTO;
import eduessence.registro_cliente.models.entity.Sesiones;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ISesionMapper {
    ISesionMapper INSTANCIA = Mappers.getMapper(ISesionMapper.class);
    Sesiones dtoPersistenciaToEntity(SesionDTO sesionDTO);
}
