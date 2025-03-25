package br.unitins.topicos.app.subcategoria.model;

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
public class SubcategoriaRequest {

    @NotBlank(message = "O campo nome não pode ser vazio.")
    @Size(max = 500, message = "O campo nome não pode ter mais de 500 caracteres.")
    private String nome;

    @NotNull(message = "O campo categoria não pode ser nulo.")
    private Integer categoria;

    @NotNull(message = "O campo carga horária não pode ser nulo.")
    private Integer cargaHorariaMaxima;

}
