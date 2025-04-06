package br.unitins.topicos.app.categoria.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.categoria.entity.Categoria;
import br.unitins.topicos.app.categoria.repository.CategoriaRepository;
import br.unitins.topicos.app.categoria.model.CategoriaRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoriaServiceImpl extends BaseServiceIpml<Categoria> implements CategoriaService {

    protected CategoriaServiceImpl(final BaseRepository<Categoria> repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Categoria create(Categoria entity) throws ApiException {
        return super.create(entity);
    }

//    @Transactional
//    public Categoria update(String id, CategoriaRequest request) throws ApiException {
//        Categoria entity = this.findById(id);
//
//        if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
//            entity.setNome(request.getNome());
//        }
//
//        if (request.getCargaHorariaMaxima() != null) {
//            entity.setCargaHorariaMaxima(request.getCargaHorariaMaxima());
//        }
//
//        if (request.getCategoria() != null) {
//            entity.setCategoria(categoriaService.findById(String.valueOf(request.getCategoria())));
//        }
//
//        return getRepository().save(entity);
//    }

    @Transactional
    public void delete(String id) throws ApiException {
        Categoria entity = this.findById(id);
        getRepository().delete(entity);
    }

    @Override
    public CategoriaRepository getRepository() {
        return (CategoriaRepository) super.getRepository();
    }

}
