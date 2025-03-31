package br.unitins.topicos.app.auth;

import br.unitins.topicos.app.auth.model.LoginRequest;
import br.unitins.topicos.app.configuracao.security.token.TokenJWTResponse;
import br.unitins.topicos.app.configuracao.security.token.TokenService;
import br.unitins.topicos.app.usuario.entity.Usuario;
import br.unitins.topicos.app.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager manager;
    private TokenService tokenService;
    private UsuarioService usuarioService;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService, UsuarioService usuarioService) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity login(@Valid @RequestBody LoginRequest request) throws RuntimeException {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken(((Usuario) authentication.getPrincipal()).getUsername());

        return ResponseEntity.ok(new TokenJWTResponse(tokenJWT));
    }


}
