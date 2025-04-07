package br.unitins.topicos.app.subcategoria.controller;

import br.unitins.topicos.app.base.controller.BaseController;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaMapper;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaRequest;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaResponse;
import br.unitins.topicos.app.subcategoria.service.SubcategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/subcategorias")
public class SubcategoriaController extends BaseController<Subcategoria, SubcategoriaRequest, SubcategoriaResponse> {

    protected SubcategoriaController(SubcategoriaService service, SubcategoriaMapper mapper) {
        super(service, mapper);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<SubcategoriaResponse> findById(@PathVariable Integer id) throws ApiException {
        return super.findById(id);
    }

    @PostMapping
    public ResponseEntity<SubcategoriaResponse> create(@Valid @RequestBody SubcategoriaRequest entityRequest,
                                                  UriComponentsBuilder uriComponentsBuilder) throws ApiException {
        Subcategoria entity = getService().create(getMapper().fromRequest(entityRequest));
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(getMapper().toResponse(entity));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<SubcategoriaResponse> update(@PathVariable("id") Integer id,
                                                  @Valid @RequestBody SubcategoriaRequest entityRequest) throws ApiException {
        return ResponseEntity.ok(getMapper().toResponse(getService().update(id, entityRequest)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(Integer id) throws ApiException {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public SubcategoriaService getService() {
        return (SubcategoriaService) super.getService();
    }

    @Override
    public SubcategoriaMapper getMapper() {
        return (SubcategoriaMapper) super.getMapper();
    }

}
