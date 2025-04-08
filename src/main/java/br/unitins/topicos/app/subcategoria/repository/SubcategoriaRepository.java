package br.unitins.topicos.app.subcategoria.repository;

import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.subcategoria.entity.Subcategoria;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends BaseRepository<Subcategoria> {
}
