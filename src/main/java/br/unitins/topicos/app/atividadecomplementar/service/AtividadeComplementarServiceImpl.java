package br.unitins.topicos.app.atividadecomplementar.service;

import br.unitins.topicos.app.atividadecomplementar.entity.AtividadeComplementar;
import br.unitins.topicos.app.atividadecomplementar.model.AtividadeComplementarRequest;
import br.unitins.topicos.app.atividadecomplementar.repository.AtividadeComplementarRepository;
import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.subcategoria.service.SubcategoriaService;
import br.unitins.topicos.app.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AtividadeComplementarServiceImpl extends BaseServiceIpml<AtividadeComplementar> implements AtividadeComplementarService {

    private final SubcategoriaService subcategoriaService;
    private final UsuarioService usuarioService;

    protected AtividadeComplementarServiceImpl(final BaseRepository<AtividadeComplementar> repository,
                                               final SubcategoriaService subcategoriaService,
                                               final UsuarioService usuarioService) {
        super(repository);
        this.subcategoriaService = subcategoriaService;
        this.usuarioService = usuarioService;
    }

    @Override
    @Transactional
    public AtividadeComplementar create(AtividadeComplementar entity) throws ApiException {
        return super.create(entity);
    }

    @Transactional
    public AtividadeComplementar update(Integer id, AtividadeComplementarRequest request) throws ApiException {
        AtividadeComplementar entity = this.findById(id);

        if (Objects.nonNull(request.getTitulo()) && !request.getTitulo().isBlank()) {
            entity.setTitulo(request.getTitulo());
        }

        if (Objects.nonNull(request.getDescricao()) && !request.getDescricao().isBlank()) {
            entity.setDescricao(request.getDescricao());
        }

        if (request.getCargaHoraria() != null) {
            entity.setCargaHoraria(request.getCargaHoraria());
        }

        if (request.getSubcategoria() != null) {
            entity.setSubcategoria(subcategoriaService.findById(request.getSubcategoria()));
        }

        if (request.getUsuario() != null) {
            entity.setUsuario(usuarioService.findById(request.getUsuario()));
        }

        return getRepository().save(entity);
    }

    @Transactional
    public void delete(Integer id) throws ApiException {
        AtividadeComplementar entity = this.findById(id);
        getRepository().delete(entity);
    }

    @Override
    public AtividadeComplementarRepository getRepository() {
        return (AtividadeComplementarRepository) super.getRepository();
    }

}
