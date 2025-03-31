package br.unitins.topicos.app.configuracao.security.token;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final String BRAZILIAN_TIME_ZONE_DIFFERENCE = "-03:00";

    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String gerarToken(String username) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(Instant.now())
                .expiresAt(expirationDate())
                .claim("role", "USER")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getSubject(String tokenJWT) throws RuntimeException {
        tokenJWT = tokenJWT.replace("Bearer", "").strip();
        Jwt decodedJwt = jwtDecoder.decode(tokenJWT);

        return decodedJwt.getSubject();
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(3)
                .toInstant(ZoneOffset.of(BRAZILIAN_TIME_ZONE_DIFFERENCE));
    }
}
