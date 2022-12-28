package com.slab.spring_security_lab.security.provider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("jwt Filter...");
        String jwtToken = parseJwt(request);
        log.info("jwtToken = {}", jwtToken);
        if (!validToken(jwtToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        log.info("next Filter");
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return headerAuth;
    }

    private boolean validToken(String jwt) {

        if (jwt == null || jwt.isEmpty()) {
            return false;
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);

        HttpEntity request = new HttpEntity(headers);
        try {
            ResponseEntity<Void> response = restTemplate.exchange("http://localhost:8084/auth/v1.0/token/verify", HttpMethod.GET, request, Void.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return false;
    }
}
