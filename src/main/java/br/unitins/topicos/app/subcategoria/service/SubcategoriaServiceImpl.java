package br.unitins.topicos.app.subcategoria.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.categoria.service.CategoriaService;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaRequest;
import br.unitins.topicos.app.subcategoria.repository.SubcategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SubcategoriaServiceImpl extends BaseServiceIpml<Subcategoria> implements SubcategoriaService {

    private final CategoriaService categoriaService;

    protected SubcategoriaServiceImpl(final BaseRepository<Subcategoria> repository, final CategoriaService categoriaService) {
        super(repository);
        this.categoriaService = categoriaService;
    }

    @Override
    @Transactional
    public Subcategoria create(Subcategoria entity) throws ApiException {
        return super.create(entity);
    }

    @Transactional
    public Subcategoria update(String id, SubcategoriaRequest request) throws ApiException {
        Subcategoria entity = this.findById(id);

        if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
            entity.setNome(request.getNome());
        }

        if (request.getCargaHorariaMaxima() != null) {
            entity.setCargaHorariaMaxima(request.getCargaHorariaMaxima());
        }

        if (request.getCategoria() != null) {
            entity.setCategoria(categoriaService.findById(String.valueOf(request.getCategoria())));
        }

        return getRepository().save(entity);
    }

    @Transactional
    public void delete(String id) throws ApiException {
        Subcategoria entity = this.findById(id);
        getRepository().delete(entity);
    }

    @Override
    public SubcategoriaRepository getRepository() {
        return (SubcategoriaRepository) super.getRepository();
    }

}
