package br.unitins.topicos.app.base.service;

import br.unitins.topicos.app.base.entity.BaseEntity;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public interface BaseService<T extends BaseEntity> {

    T create(T entity) throws ApiException;

    T update(String id, T entity) throws ApiException;

    T findById(String id) throws ApiException;

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    void bind(T entity, T update);

    BaseRepository<T> getRepository();

    <U> U map(T entity, Function<T, ? extends U> converter);

}
