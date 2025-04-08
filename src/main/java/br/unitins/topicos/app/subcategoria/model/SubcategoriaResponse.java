package br.unitins.topicos.app.subcategoria.model;

import br.unitins.topicos.app.categoria.model.CategoriaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaResponse {

    private Integer id;
    private String nome;
    private CategoriaResponse categoria;
    private Integer cargaHorariaMaxima;

}
