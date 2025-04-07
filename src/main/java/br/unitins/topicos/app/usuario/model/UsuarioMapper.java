package br.unitins.topicos.app.usuario.model;

import br.unitins.topicos.app.base.mapper.BaseMapper;
import br.unitins.topicos.app.usuario.entity.Usuario;
import org.mapstruct.Mapper;

/**
 * Interface que define o mapeamento entre a entidade {@link Usuario} e os objetos de solicitação e resposta relacionados.
 * Utiliza o framework {@link org.mapstruct.Mapper} para gerar a implementação do mapeamento automaticamente.
 *
 * @author Iad Rodrigues
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioRequest, UsuarioResponse> {
}
