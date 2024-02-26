package eduessence.registro_cliente.mappers;

import eduessence.registro_cliente.models.dto.LogNegocioDTO;
import eduessence.registro_cliente.models.entity.LogNegocio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IlogNegocioMapper {
    IlogNegocioMapper INSTANCIA = Mappers.getMapper(IlogNegocioMapper.class);
    LogNegocio dtoPersistenciaToEntity(LogNegocioDTO logNegocio);
}
