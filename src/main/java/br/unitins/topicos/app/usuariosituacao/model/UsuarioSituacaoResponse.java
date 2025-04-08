package br.unitins.topicos.app.usuariosituacao.model;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSituacaoResponse {
    private Double percentualConclusaoGeral;
    private List<CategoriaProgresso> progressoPorCategoria;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriaProgresso {
        private String nomeCategoria;
        private Double percentualConclusao;
    }
}
