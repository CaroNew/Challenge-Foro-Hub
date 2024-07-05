package com.challenge.forohub.infra.security;

import com.challenge.forohub.domain.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {


        String autenticacionHeader = request.getHeader("Authorization");
        String subject = "";

        if(autenticacionHeader != null){
            var token = autenticacionHeader.replace("Bearer ", "");
            subject = tokenService.getSubject(token);

            if(subject != null){
                var usuario = usuarioRepository.findByCorreoElectronico(subject)
                        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,
                        null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
