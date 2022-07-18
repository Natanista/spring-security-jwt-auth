package br.com.springjwt.desafio.security;

import br.com.springjwt.desafio.data.GerenteDetails;
import br.com.springjwt.desafio.model.GerenteModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public static final String TOKEN_PASSWORD = "8d50bd78-fb16-11ec-b939-0242ac120002";
    public static final int TOKEN_EXPIRACAO = 600_000;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            GerenteModel gerenteModel = new ObjectMapper().readValue(request.getInputStream(), GerenteModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    gerenteModel.getEmail(),
                    gerenteModel.getSenha(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao autenticar gerente", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        GerenteDetails gerenteDetails = (GerenteDetails) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(gerenteDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));


        response.getWriter().write(token);
        response.getWriter().flush();
    }

}
