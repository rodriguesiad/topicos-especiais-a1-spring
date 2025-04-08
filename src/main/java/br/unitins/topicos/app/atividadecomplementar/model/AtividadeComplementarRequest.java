package br.unitins.topicos.app.atividadecomplementar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AtividadeComplementarRequest {

    @NotBlank(message = "O campo titulo não pode ser vazio.")
    @Size(max = 500, message = "O campo titulo não pode ter mais de 500 caracteres.")
    private String titulo;

    @Size(max = 500, message = "O campo descrião não pode ter mais de 500 caracteres.")
    private String descricao;

    @NotNull(message = "O campo subcategoria não pode ser nulo.")
    private Integer subcategoria;

    @NotNull(message = "O campo usuario não pode ser nulo.")
    private Integer usuario;

    @NotNull(message = "O campo carga horária não pode ser nulo.")
    private Integer cargaHoraria;

}
