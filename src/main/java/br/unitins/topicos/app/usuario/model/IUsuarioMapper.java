package br.unitins.topicos.app.usuario.model;

import br.unitins.topicos.app.base.mapper.BaseMapper;
import br.unitins.topicos.app.usuario.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper extends BaseMapper<Usuario, UsuarioRequest, UsuarioResponse> {
}
