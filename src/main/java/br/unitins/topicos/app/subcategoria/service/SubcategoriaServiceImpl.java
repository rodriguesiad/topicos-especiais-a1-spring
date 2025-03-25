package br.unitins.topicos.app.subcategoria.service;

import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import br.unitins.topicos.app.subcategoria.repository.SubcategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class SubcategoriaServiceImpl extends BaseServiceIpml<Subcategoria> implements SubcategoriaService {

    protected SubcategoriaServiceImpl(final BaseRepository<Subcategoria> repository) {
        super(repository);
    }

    @Override
    public SubcategoriaRepository getRepository() {
        return (SubcategoriaRepository) super.getRepository();
    }

}
