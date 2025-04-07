package br.unitins.topicos.app.categoria.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.service.BaseService;
import br.unitins.topicos.app.categoria.entity.Categoria;
import br.unitins.topicos.app.categoria.model.CategoriaRequest;

public interface CategoriaService extends BaseService<Categoria> {
    Categoria update(Integer id, CategoriaRequest request) throws ApiException;
    void delete(Integer id) throws ApiException;
}
