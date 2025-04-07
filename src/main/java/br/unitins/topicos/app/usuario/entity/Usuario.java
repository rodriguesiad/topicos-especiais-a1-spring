package br.unitins.topicos.app.usuario.entity;

import br.unitins.topicos.app.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Entidade que representa um usuário no sistema.
 * Esta classe mapeia a tabela {@code usuario} no banco de dados e implementa a interface
 * {@link org.springframework.security.core.userdetails.UserDetails}, sendo usada para autenticação e autorização.
 *
 * @author Iad Rodrigues
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
