package br.unitins.topicos.app.atividadecomplementar.service;

import br.unitins.topicos.app.atividadecomplementar.entity.AtividadeComplementar;
import br.unitins.topicos.app.atividadecomplementar.model.AtividadeComplementarRequest;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.service.BaseService;

public interface AtividadeComplementarService extends BaseService<AtividadeComplementar> {
    AtividadeComplementar update(String id, AtividadeComplementarRequest request) throws ApiException;
    void delete(String id) throws ApiException;
}
