package br.unitins.topicos.app.usuario.repository;

import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {

    @Query("""
                SELECT u FROM Usuario u WHERE u.email = :email AND u.id <> :id ORDER BY u.dataCriacao LIMIT 1
            """)
    Optional<Usuario> findEmailDuplicado(String email, String id);
    
}

