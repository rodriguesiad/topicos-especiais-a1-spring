package br.unitins.topicos.app.base.repository;

import br.unitins.topicos.app.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

}
