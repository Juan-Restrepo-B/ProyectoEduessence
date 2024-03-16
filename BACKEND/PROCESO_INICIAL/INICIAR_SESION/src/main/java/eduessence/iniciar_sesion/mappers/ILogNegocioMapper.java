package eduessence.iniciar_sesion.mappers;

import eduessence.iniciar_sesion.models.dto.LogNegocioDTO;
import eduessence.iniciar_sesion.models.entity.LogNegocio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ILogNegocioMapper {
    ILogNegocioMapper INSTANCIA = Mappers.getMapper(ILogNegocioMapper.class);
    LogNegocio dtoPersistenciaToEntity(LogNegocioDTO logNegocio);
}