package br.unitins.topicos.app.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe que representa o modelo de dados de resposta ao retornar informações de um usuário.
 *
 * @author Iad Rodrigues
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private String id;
    private String email;
    private String nomeCompleto;
    private String campus;
    private String curso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;

}
