package br.unitins.topicos.app.configuracao.security;

import br.unitins.topicos.app.configuracao.security.token.TokenService;
import br.unitins.topicos.app.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança responsável por interceptar as requisições e validar os tokens JWT
 * para autenticar o usuário na aplicação.
 * Ele verifica o token presente no cabeçalho da requisição e, se válido, autentica o usuário
 * no contexto de segurança do Spring Security.
 *
 * @author Iad Rodrigues
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = obterToken(request);

        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = userRepository.findByEmail(subject).orElseThrow(() -> new RuntimeException("Não foi possível consultar usuário pelo e-mail " + subject));

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String obterToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            return null;
        }

        return authorizationHeader.replace("Bearer", "").strip();
    }

}
