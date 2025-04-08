package br.unitins.topicos.app.subcategoria.model;

import br.unitins.topicos.app.base.mapper.BaseMapper;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubcategoriaMapper extends BaseMapper<Subcategoria, SubcategoriaRequest, SubcategoriaResponse> {

    @Mapping(source = "categoria.id", target = "categoria")
    SubcategoriaRequest toRequest(Subcategoria entity);

    @Mapping(source = "categoria", target = "categoria.id")
    Subcategoria fromRequest(SubcategoriaRequest request);
}
