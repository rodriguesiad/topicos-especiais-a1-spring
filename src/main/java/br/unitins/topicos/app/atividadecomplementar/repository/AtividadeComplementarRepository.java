package br.unitins.topicos.app.atividadecomplementar.repository;

import br.unitins.topicos.app.atividadecomplementar.entity.AtividadeComplementar;
import br.unitins.topicos.app.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeComplementarRepository extends BaseRepository<AtividadeComplementar> {
    @Query("SELECT COALESCE(SUM(a.cargaHoraria), 0) FROM AtividadeComplementar a WHERE a.subcategoria.id = :subcategoriaId")
    Integer calcularCargaHorariaPorSubcategoria(@Param("subcategoriaId") Integer subcategoriaId);

    List<AtividadeComplementar> findByUsuarioId(Integer usuarioId);
}
