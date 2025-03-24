package br.unitins.topicos.app.usuario.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UsuarioRequest {

    @Email
    @NotBlank(message = "O campo e-mail não pode ser vazio.")
    @Size(max = 500, message = "O campo e-mail não pode ter mais de 500 caracteres.")
    private String email;

    @NotBlank(message = "O campo nome completo não pode ser vazio.")
    @Size(max = 500, message = "O campo nome completo não pode ter mais de 500 caracteres.")
    private String nomeCompleto;

    @NotBlank(message = "O campo senha não pode ser vazio.")
    @Size(max = 100, message = "O campo senha não pode ter mais de 100 caracteres.")
    private String senha;

    @Size(max = 500, message = "O campo campus não pode ter mais de 500 caracteres.")
    private String campus;

    @Size(max = 500, message = "O campo curso não pode ter mais de 500 caracteres.")
    private String curso;

}
