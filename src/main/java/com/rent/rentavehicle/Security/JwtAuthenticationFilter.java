package com.rent.rentavehicle.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = null;

        // 1️⃣ Skip JWT filter for authentication endpoints (login, register)
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/auth/") || requestURI.equals("/auth/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Try to get the token from the "Authorization" header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        // 3️⃣ If token is not found in the header, try getting it from the request body
        // (if applicable)
        if (token == null && request.getContentType() != null
                && request.getContentType().contains("application/json")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> body = objectMapper.readValue(request.getInputStream(),
                        new TypeReference<Map<String, String>>() {
                        });

                token = body.get("token");
            } catch (Exception e) {
                System.out.println("Error parsing request body: " + e.getMessage());
            }
        }

        // 4️⃣ Validate and authenticate the token
        if (token != null && jwtUtil.validateToken(token)) {
            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token);

            UserDetails userDetails = new User(email, "", Collections.singletonList(new SimpleGrantedAuthority(role)));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
