package br.com.davicabeleireiro.davicabeleireiro.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTTokenFilter extends GenericFilterBean {

    @Autowired
    private JWTTokenProvider tokenProvider;

    public JWTTokenFilter(JWTTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String token = tokenProvider.resolveToken((HttpServletRequest) request);

        Authentication auth = null;

        if (token != null && tokenProvider.validateToken(token)){

            if (tokenProvider.isClient(token)){
                auth = tokenProvider.getAuthenticationFromClient(token);
            }else {
                auth = tokenProvider.getAuthenticationFromUser(token);
            }
            if (auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
