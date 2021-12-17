package com.example.demo.filter;

import com.example.demo.constant.Constant;
import com.example.demo.dto.Actor;
import com.example.demo.utils.TokenHelpers;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        // check if existed token header
        if (Strings.isNullOrEmpty(header)) {
            chain.doFilter(req, res);
            return;
        }
        String token = header.replace(Constant.TOKEN_PREFIX, "");
        Claims claims = TokenHelpers.verifyToken(token);
        if (claims == null) {
            chain.doFilter(req, res);
            return;
        }

        String userId = (String) claims.get(Constant.PREFIX_USER_ID);
        List<GrantedAuthority> authorities = new ArrayList<>();
        Actor actor = Actor.builder()
                .token(token)
                .userId(userId)
                .build();

        context.setAuthentication(new UsernamePasswordAuthenticationToken(userId, actor, authorities));
        chain.doFilter(req, res);
    }
}
