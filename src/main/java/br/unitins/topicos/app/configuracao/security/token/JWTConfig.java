package br.unitins.topicos.app.configuracao.security.token;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;


/**
 * Configuração de segurança para a criação e decodificação de tokens JWT.
 * Esta classe fornece os beans necessários para codificar e decodificar tokens JWT
 * usando o algoritmo HMAC SHA-256 e uma chave secreta configurada.
 *
 * @author Iad Rodrigues
 */
@Configuration
public class JWTConfig {

    @Value("${app.security.token.secret}")
    private String secret;

    @Bean
    public JwtEncoder jwtEncoder() {
        SecretKey secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

        // Customizando o JwtEncoder para usar chave secreta HMAC SHA-256
        return parameters -> {
            try {
                MACSigner signer = new MACSigner(secretKeySpec);

                // Criando os claims do JWT
                JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder();
                parameters.getClaims().getClaims().forEach((key, value) ->
                        claimsSetBuilder.claim(key, value instanceof Instant ? Date.from((Instant) value) : value)
                );

                JWTClaimsSet claimsSet = claimsSetBuilder.build();

                // Criando o cabeçalho (header) do JWT
                JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

                SignedJWT signedJWT = new SignedJWT(header, claimsSet);
                signedJWT.sign(signer);

                // Retornando o JWT serializado
                return Jwt.withTokenValue(signedJWT.serialize())
                        .header("alg", header.getAlgorithm().getName())
                        .subject(claimsSet.getSubject())
                        .issuer(claimsSet.getIssuer())
                        .claims(claims -> claims.putAll(claimsSet.getClaims()))
                        .issuedAt(claimsSet.getIssueTime().toInstant())
                        .expiresAt(claimsSet.getExpirationTime().toInstant())
                        .build();

            } catch (Exception e) {
                throw new IllegalStateException("Erro na criação do JWT", e);
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

}
