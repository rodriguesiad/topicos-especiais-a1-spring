package br.unitins.topicos.app.base.mapper;

import br.unitins.topicos.app.base.entity.BaseEntity;

import java.util.List;

/**
 * Classe base de mapeamento de entidades
 *
 * @param <T> - Entidade.
 * @param <Q> - Classe de resposta.
 */
public interface BaseResponseMapper<T extends BaseEntity, Q> {

    Q toResponse(T entity);

    List<Q> toListResponse(List<T> entityList);

}
