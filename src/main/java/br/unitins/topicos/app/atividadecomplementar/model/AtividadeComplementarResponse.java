package br.unitins.topicos.app.atividadecomplementar.model;

import br.unitins.topicos.app.categoria.model.CategoriaResponse;
import br.unitins.topicos.app.subcategoria.model.SubcategoriaResponse;
import br.unitins.topicos.app.usuario.model.UsuarioResponse;
import br.unitins.topicos.app.usuario.repository.UsuarioRepository;
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
public class AtividadeComplementarResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private SubcategoriaResponse subcategoria;
    private UsuarioResponse usuario;
    private Integer cargaHoraria;

}
