package br.unitins.topicos.app.usuario.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Classe que representa o modelo de dados para a atualização de um usuário existente.
 *
 * @author Iad Rodrigues
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateRequest {

    @NotBlank(message = "O campo nome completo não pode ser vazio.")
    @Size(max = 500, message = "O campo nome completo não pode ter mais de 500 caracteres.")
    private String nomeCompleto;

    @Size(max = 500, message = "O campo campus não pode ter mais de 500 caracteres.")
    private String campus;

    @Size(max = 500, message = "O campo curso não pode ter mais de 500 caracteres.")
    private String curso;

}
