package br.unitins.topicos.app.usuario.service;

import br.unitins.topicos.app.base.exception.ApiException;
import br.unitins.topicos.app.base.service.BaseService;
import br.unitins.topicos.app.usuario.entity.Usuario;
import br.unitins.topicos.app.usuario.model.UsuarioUpdateRequest;

public interface UsuarioService extends BaseService<Usuario> {

    Usuario update(Integer id, UsuarioUpdateRequest request) throws ApiException;

}
