package br.unitins.topicos.app.categoria.service;

import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.categoria.entity.Categoria;
import br.unitins.topicos.app.categoria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends BaseServiceIpml<Categoria> implements CategoriaService {

    protected CategoriaServiceImpl(final BaseRepository<Categoria> repository) {
        super(repository);
    }

    @Override
    public CategoriaRepository getRepository() {
        return (CategoriaRepository) super.getRepository();
    }

}
