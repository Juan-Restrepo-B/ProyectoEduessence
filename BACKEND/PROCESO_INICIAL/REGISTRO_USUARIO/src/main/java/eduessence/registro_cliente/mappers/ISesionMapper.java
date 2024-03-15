package eduessence.registro_cliente.mappers;

import eduessence.registro_cliente.models.dto.SesionDTO;
import eduessence.registro_cliente.models.entity.Sesion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ISesionMapper {
    ISesionMapper INSTANCIA = Mappers.getMapper(ISesionMapper.class);
    Sesion dtoPersistenciaToEntity(SesionDTO sesionDTO);
}
