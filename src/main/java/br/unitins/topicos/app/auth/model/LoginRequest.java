package br.unitins.topicos.app.auth.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa o modelo de dados de entrada para a solicitação de login.
 *
 * @author Iad Rodrigues
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {


    @NotBlank
    private String login;

    @NotBlank
    private String senha;

}
