package br.unitins.topicos.app.base.controller;

import br.unitins.topicos.app.base.entity.BaseEntity;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.mapper.BaseMapper;
import br.unitins.topicos.app.base.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class BaseController<T extends BaseEntity, R, Q> {

    private final BaseService<T> service;
    private final BaseMapper<T, R, Q> mapper;

    protected BaseController(final BaseService<T> service, final BaseMapper<T, R, Q> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Q>> findAll() throws ApiException {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).toList());
    }

    @GetMapping
    public ResponseEntity<Page<Q>> findAll(Pageable pageable) throws ApiException {
        return ResponseEntity.ok(service.findAll(pageable).map(mapper::toResponse));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Q> findById(@PathVariable String id) throws ApiException {
        return ResponseEntity.ok(getMapper().toResponse(service.findById(id)));
    }

    protected BaseService<T> getService() {
        return service;
    }

    protected BaseMapper<T, R, Q> getMapper() {
        return mapper;
    }

}
