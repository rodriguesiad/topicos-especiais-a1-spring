package br.unitins.topicos.app.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
