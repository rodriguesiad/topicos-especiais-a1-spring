package br.unitins.topicos.app.base.mapper;

import br.unitins.topicos.app.base.entity.BaseEntity;

/**
 * Classe base de mapeamento de entidades
 *
 * @param <T> - Entidade.
 * @param <R> - Classe de requisição.
 * @param <Q> - Classe de resposta.
 */
public interface BaseMapper<T extends BaseEntity, R, Q> extends BaseRequestMapper<T, R>, BaseResponseMapper<T, Q> {

}
