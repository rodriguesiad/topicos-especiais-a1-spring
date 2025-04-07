package br.unitins.topicos.app.subcategoria.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.service.BaseService;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaRequest;

public interface SubcategoriaService extends BaseService<Subcategoria> {
    Subcategoria update(Integer id, SubcategoriaRequest request) throws ApiException;
    void delete(Integer id) throws ApiException;
}
