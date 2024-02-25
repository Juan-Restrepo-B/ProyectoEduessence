package eduessence.registro_cliente.mappers;

import eduessence.registro_cliente.models.dto.LogUsuarioDTO;
import eduessence.registro_cliente.models.entity.LogUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IlogNegocioMapper {
    ILogNegocioMapper INSTANCIA = Mappers.getMapper(IlogNegocioMapper.class);
    LogUsuario dtoPersistenciaToEntity(ILogNegocioMapper logNegocioMapperDTO);
}
