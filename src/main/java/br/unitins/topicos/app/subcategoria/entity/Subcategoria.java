package br.unitins.topicos.app.subcategoria.entity;

import br.unitins.topicos.app.base.entity.BaseEntity;
import br.unitins.topicos.app.categoria.entity.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subcategoria")
public class Subcategoria extends BaseEntity {

    @Id
    @Column(name = "id_subcategoria", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 500)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "carga_horaria_maxima", nullable = false)
    private Integer cargaHorariaMaxima;

}
