package br.unitins.topicos.app.usuario.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.repository.BaseRepository;
import br.unitins.topicos.app.base.service.BaseServiceIpml;
import br.unitins.topicos.app.usuario.entity.Usuario;
import br.unitins.topicos.app.usuario.model.UsuarioUpdateRequest;
import br.unitins.topicos.app.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceIpml extends BaseServiceIpml<Usuario> implements UsuarioService {

    protected UsuarioServiceIpml(final BaseRepository<Usuario> repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Usuario create(Usuario entity) throws ApiException {
        this.validate(entity);
        validarRepeticaoEmail(entity.getEmail(), "id padrão para pesquisa");

        return super.create(entity);
    }

    @Override
    @Transactional
    public Usuario update(String id, UsuarioUpdateRequest request) throws ApiException {
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

    private void validate(Usuario entity) throws ApiException {
        if (Objects.isNull(entity)) {
            throw new ApiException("A entidade não pode ser nula.");
        }
    }

    private void validarRepeticaoEmail(String email, String id) throws ApiException {
        Optional<Usuario> user = getRepository().findEmailDuplicado(email, id);

        if (Boolean.FALSE.equals(user.isEmpty())) {
            throw new ApiException("O e-mail informado já está sendo utilizado.");
        }
    }

    @Override
    public UsuarioRepository getRepository() {
        return (UsuarioRepository) super.getRepository();
    }

}
