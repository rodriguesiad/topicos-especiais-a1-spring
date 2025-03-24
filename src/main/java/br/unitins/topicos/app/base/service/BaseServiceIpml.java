package br.unitins.topicos.app.base.service;

import br.unitins.topicos.app.base.entity.BaseEntity;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Transactional(readOnly = true)
public class BaseServiceIpml<T extends BaseEntity> implements BaseService<T> {

    private final BaseRepository<T> repository;

    protected BaseServiceIpml(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T create(T entity) throws ApiException {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public T update(String id, T entity) throws ApiException {
        final T t = repository.findById(id).orElseThrow(() -> new ApiException("Registro não encontrado: " + id));
        entity.setDataCriacao(t.getDataCriacao());
        bind(t, entity);
        return repository.save(t);
    }

    @Override
    public T findById(String id) throws ApiException {
        return repository
                .findById(id)
                .orElseThrow(() -> new ApiException("Registro não encontrado: " + id));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public <U> U map(T entity, Function<T, ? extends U> converter) {
        return converter.apply(entity);
    }

    public void bind(T entity, T update) {
        BeanUtils.copyProperties(update, entity, "id", "dataCriacao", "dataAlteracao");
    }

    public BaseRepository<T> getRepository() {
        return this.repository;
    }

}
