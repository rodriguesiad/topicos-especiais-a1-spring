package br.unitins.topicos.app.atividadecomplementar.model;

import br.unitins.topicos.app.atividadecomplementar.entity.AtividadeComplementar;
import br.unitins.topicos.app.base.mapper.BaseMapper;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import br.unitins.topicos.app.usuario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtividadeComplementarMapper extends BaseMapper<AtividadeComplementar, AtividadeComplementarRequest, AtividadeComplementarResponse> {

    @Mapping(source = "subcategoria.id", target = "subcategoria")
    @Mapping(source = "usuario.id", target = "usuario")
    AtividadeComplementarRequest toRequest(AtividadeComplementar entity);

    @Mapping(source = "subcategoria", target = "subcategoria.id")
    @Mapping(source = "usuario", target = "usuario.id")
    AtividadeComplementar fromRequest(AtividadeComplementarRequest request);
}
