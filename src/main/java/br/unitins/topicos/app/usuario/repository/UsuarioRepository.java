package br.unitins.topicos.app.usuario.repository;

import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável pela persistência dos dados da entidade {@link Usuario}.
 * Extende {@link br.unitins.topicos.app.base.repository.BaseRepository} e fornece métodos para operações de consulta e manipulação
 * dos dados de usuários, incluindo a verificação de e-mails duplicados.
 *
 * @author Iad Rodrigues
 */
@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {

    @Query("""
                SELECT u FROM Usuario u WHERE u.email = :email AND u.id <> :id ORDER BY u.dataCriacao LIMIT 1
            """)
    Optional<Usuario> findEmailDuplicado(String email, Integer id);

    Optional<UserDetails> findByEmail(String email);
}

