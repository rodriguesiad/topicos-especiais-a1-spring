package br.unitins.topicos.app.atividadecomplementar.model;

import br.unitins.topicos.app.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubcategoriaMapper extends BaseMapper<Subcategoria, SubcategoriaRequest, SubcategoriaResponse> {

    @Mapping(source = "categoria.id", target = "categoria")
    SubcategoriaRequest toRequest(Subcategoria entity);

    @Mapping(source = "categoria", target = "categoria.id")
    Subcategoria fromRequest(SubcategoriaRequest request);
}
