package eduessence.registro_cliente.mappers;

import eduessence.registro_cliente.models.dto.LogUsuarioDTO;
import eduessence.registro_cliente.models.entity.LogUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ILogUsuarioMapper {
    ILogUsuarioMapper INSTANCIA = Mappers.getMapper(ILogUsuarioMapper.class);
    LogUsuario dtoPersistenciaToEntity(LogUsuarioDTO logUsuarioDTO);
}
