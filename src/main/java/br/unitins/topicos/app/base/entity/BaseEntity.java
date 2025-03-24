package br.unitins.topicos.app.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseEntity {

    @Column(name = "data_criacao", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCriacao;

    @Column(name = "data_alteracao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAlteracao;

    @PrePersist
    protected void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }

    public abstract Integer getId();

    public abstract void setId(Integer id);

}
