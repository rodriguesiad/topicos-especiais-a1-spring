package br.unitins.topicos.app.auth;

import br.unitins.topicos.app.auth.model.LoginRequest;
import br.unitins.topicos.app.configuracao.security.token.TokenJWTResponse;
import br.unitins.topicos.app.configuracao.security.token.TokenService;
import br.unitins.topicos.app.usuario.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador responsável pelo gerenciamento de autenticação de usuários.
 * Esta classe permite que um usuário faça login fornecendo seu nome de usuário
 * e senha. Se as credenciais forem válidas, ela retorna um token JWT que
 * pode ser usado para autenticação em requisições subsequentes.
 *
 * @author Iad Rodrigues
 */
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager manager;
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@Valid @RequestBody LoginRequest request) throws RuntimeException {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken(((Usuario) authentication.getPrincipal()).getUsername());

        return ResponseEntity.ok(new TokenJWTResponse(tokenJWT));
    }

}
