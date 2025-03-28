package br.unitins.topicos.app.usuario.entity;

import br.unitins.topicos.app.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity {

    @Id
    @Column(name = "id_usuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_completo", nullable = false, length = 500)
    private String nomeCompleto;

    @Column(name = "email", nullable = false, length = 500, unique = true)
    private String email;

    @Column(name = "campus", length = 500)
    private String campus;

    @Column(name = "curso", length = 500)
    private String curso;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

}
