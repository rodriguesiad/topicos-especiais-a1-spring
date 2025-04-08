package br.unitins.topicos.app.categoria.model;

import br.unitins.topicos.app.base.mapper.BaseMapper;
import br.unitins.topicos.app.categoria.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaRequest, CategoriaResponse> {
}
