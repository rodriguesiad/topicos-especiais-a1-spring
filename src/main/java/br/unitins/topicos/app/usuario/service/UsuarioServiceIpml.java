package br.unitins.topicos.app.usuario.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.usuario.entity.Usuario;
import br.unitins.topicos.app.usuario.model.UsuarioUpdateRequest;
import br.unitins.topicos.app.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementação do serviço de usuário que fornece funcionalidades como criação, atualização e validação de usuários.
 * Esta classe implementa a lógica de negócios associada à manipulação dos dados do usuário, incluindo criptografia de senha
 * e validação de e-mails duplicados. Utiliza {@link PasswordEncoder} para a segurança das senhas.
 *
 * @author Iad Rodrigues
 */
@Service
public class UsuarioServiceIpml extends BaseServiceIpml<Usuario> implements UsuarioService {

    private final PasswordEncoder passwordEncoder;

    protected UsuarioServiceIpml(final BaseRepository<Usuario> repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Usuario create(Usuario entity) throws ApiException {
        this.validate(entity);

        String encodedPassword = passwordEncoder.encode(entity.getSenha());
        entity.setSenha(encodedPassword);

        Integer idPadraoPesquisa = 111;
        validarRepeticaoEmail(entity.getEmail(), idPadraoPesquisa);

        return super.create(entity);
    }

    @Override
    @Transactional
    public Usuario update(Integer id, UsuarioUpdateRequest request) throws ApiException {
        this.validarUsuario(id);
        Usuario entity = this.findById(id);

        if (Objects.nonNull(request.getNomeCompleto()) && !request.getNomeCompleto().isBlank()) {
            entity.setNomeCompleto(request.getNomeCompleto());
        }

        if (Objects.nonNull(request.getCampus()) && !request.getCampus().isBlank()) {
            entity.setCampus(request.getCampus());
        }

        if (Objects.nonNull(request.getCurso()) && !request.getCurso().isBlank()) {
            entity.setCurso(request.getCurso());
        }

        return getRepository().save(entity);
    }

    @Override
    public Usuario findById(Integer id) throws ApiException {
        validarUsuario(id);
        return super.findById(id);
    }

    private void validate(Usuario entity) throws ApiException {
        if (Objects.isNull(entity)) {
            throw new ApiException("A entidade não pode ser nula.");
        }
    }

    private void validarRepeticaoEmail(String email, Integer id) throws ApiException {
        Optional<Usuario> user = getRepository().findEmailDuplicado(email, id);

        if (Boolean.FALSE.equals(user.isEmpty())) {
            throw new ApiException("O e-mail informado já está sendo utilizado.");
        }
    }

    private Usuario getUsuarioLogado() throws ApiException {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void validarUsuario(Integer id) throws ApiException {
        Usuario usuarioLogado = getUsuarioLogado();
        if (!usuarioLogado.getId().equals(id)) {
            throw new ApiException("Ação permitida apenas para o próprio usuário.");
        }
    }

    @Override
    public UsuarioRepository getRepository() {
        return (UsuarioRepository) super.getRepository();
    }

}
