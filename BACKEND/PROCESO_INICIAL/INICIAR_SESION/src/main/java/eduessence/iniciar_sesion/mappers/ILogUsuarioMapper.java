package eduessence.iniciar_sesion.mappers;

import eduessence.iniciar_sesion.models.dto.LogUsuarioDTO;
import eduessence.iniciar_sesion.models.entity.LogUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ILogUsuarioMapper {
    ILogUsuarioMapper INSTANCIA = Mappers.getMapper(ILogUsuarioMapper.class);
    LogUsuario dtoPersistenciaToEntity(LogUsuarioDTO logUsuarioDTO);
}
