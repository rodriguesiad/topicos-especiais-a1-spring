package br.unitins.topicos.app.configuracao.security.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa a resposta contendo o token JWT gerado após o login do usuário.
 * Este modelo é retornado para o cliente como parte da resposta do endpoint de autenticação.
 *
 * @author Iad Rodrigues
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenJWTResponse {

    private String token;

}
