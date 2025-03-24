package br.unitins.topicos.app.base.mapper;

import br.unitins.topicos.app.base.entity.BaseEntity;

/**
 * Classe base de mapeamento de entidades
 *
 * @param <T> - Entidade.
 * @param <R> - Classe de requisição.
 */
public interface BaseRequestMapper<T extends BaseEntity, R> {

    T fromRequest(R request);
    R toRequest(T entity);

}
