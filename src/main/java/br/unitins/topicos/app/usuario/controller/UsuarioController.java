package br.unitins.topicos.app.usuario.controller;

import br.unitins.topicos.app.base.controller.BaseController;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.usuario.entity.Usuario;
import br.unitins.topicos.app.usuario.model.UsuarioMapper;
import br.unitins.topicos.app.usuario.model.UsuarioRequest;
import br.unitins.topicos.app.usuario.model.UsuarioResponse;
import br.unitins.topicos.app.usuario.model.UsuarioUpdateRequest;
import br.unitins.topicos.app.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * Controlador responsável pela gestão dos usuários na aplicação.
 * Fornece endpoints para criação, leitura e atualização de usuários.
 *
 * @author Iad Rodrigues
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, UsuarioRequest, UsuarioResponse> {

    protected UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        super(service, mapper);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Integer id) throws ApiException {
        return super.findById(id);
    }

    // Impedindo que um usuário acesse as informações dos demais usuários
    @Override
    @GetMapping(path = "/all")
    public ResponseEntity<List<UsuarioResponse>> findAll() throws ApiException {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.emptyList());
    }

    // Impedindo que um usuário acesse as informações dos demais usuários
    @Override
    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> findAll(Pageable pageable) throws ApiException {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Page.empty());
    }


    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest entityRequest,
                                                  UriComponentsBuilder uriComponentsBuilder) throws ApiException {
        Usuario entity = getService().create(getMapper().fromRequest(entityRequest));
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(getMapper().toResponse(entity));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable("id") Integer id,
                                                  @Valid @RequestBody UsuarioUpdateRequest entityRequest) throws ApiException {
        return ResponseEntity.ok(getMapper().toResponse(getService().update(id, entityRequest)));
    }

    @Override
    public UsuarioService getService() {
        return (UsuarioService) super.getService();
    }

    @Override
    public UsuarioMapper getMapper() {
        return (UsuarioMapper) super.getMapper();
    }

}
