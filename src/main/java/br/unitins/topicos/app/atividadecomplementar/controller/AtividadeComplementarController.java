package br.unitins.topicos.app.atividadecomplementar.controller;

import br.unitins.topicos.app.atividadecomplementar.entity.AtividadeComplementar;
import br.unitins.topicos.app.atividadecomplementar.model.AtividadeComplementarMapper;
import br.unitins.topicos.app.atividadecomplementar.model.AtividadeComplementarRequest;
import br.unitins.topicos.app.atividadecomplementar.model.AtividadeComplementarResponse;
import br.unitins.topicos.app.atividadecomplementar.service.AtividadeComplementarService;
import br.unitins.topicos.app.atividadecomplementar.service.AtividadeComplementarServiceImpl;
import br.unitins.topicos.app.base.controller.BaseController;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaMapper;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaRequest;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaResponse;
import br.unitins.topicos.app.subcategoria.service.SubcategoriaService;
import br.unitins.topicos.app.usuariosituacao.model.UsuarioSituacaoResponse;
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
import java.util.List;

@RestController
@RequestMapping("/atividades_complementares")
public class AtividadeComplementarController extends BaseController<AtividadeComplementar, AtividadeComplementarRequest, AtividadeComplementarResponse> {

    protected AtividadeComplementarController(AtividadeComplementarService service, AtividadeComplementarMapper mapper) {
        super(service, mapper);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<AtividadeComplementarResponse> findById(@PathVariable Integer id) throws ApiException {
        return super.findById(id);
    }

    @PostMapping
    public ResponseEntity<AtividadeComplementarResponse> create(@Valid @RequestBody AtividadeComplementarRequest entityRequest,
                                                  UriComponentsBuilder uriComponentsBuilder) throws ApiException {
        AtividadeComplementar entity = getService().create(getMapper().fromRequest(entityRequest));
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(getMapper().toResponse(entity));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AtividadeComplementarResponse> update(@PathVariable("id") Integer id,
                                                  @Valid @RequestBody AtividadeComplementarRequest entityRequest) throws ApiException {
        return ResponseEntity.ok(getMapper().toResponse(getService().update(id, entityRequest)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(Integer id) throws ApiException {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public AtividadeComplementarService getService() {
        return (AtividadeComplementarService) super.getService();
    }

    @Override
    public AtividadeComplementarMapper getMapper() {
        return (AtividadeComplementarMapper) super.getMapper();
    }

    @GetMapping("/situacao/{usuarioId}")
    public ResponseEntity<UsuarioSituacaoResponse> getSituacaoUsuario(@PathVariable Integer usuarioId) throws ApiException {
        UsuarioSituacaoResponse situacao = ((AtividadeComplementarServiceImpl) getService()).calcularSituacaoDoUsuario(usuarioId);
        return ResponseEntity.ok(situacao);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AtividadeComplementarResponse>> getAtividadesPorUsuario(@PathVariable Integer usuarioId) throws ApiException {
        // Chama o serviço para buscar as atividades do usuário
        List<AtividadeComplementar> atividades = getService().buscarPorUsuarioId(usuarioId);

        // Mapeia as atividades para o formato de resposta
        List<AtividadeComplementarResponse> response = getMapper().toListResponse(atividades);

        return ResponseEntity.ok(response);
    }


}
