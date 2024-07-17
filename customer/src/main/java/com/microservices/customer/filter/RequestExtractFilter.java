package com.microservices.customer.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
public class RequestExtractFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LogManager.getLogger(RequestExtractFilter.class);

    private static final List<String> CONTEXT_PATHS = Arrays.asList(
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-config",
            "/swagger-ui",
            "/actuator",
            "/webjars"
    );

    /**
     * @param request request
     * @param response response
     * @param filterChain filterChain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (CONTEXT_PATHS.stream().anyMatch(path -> request.getRequestURI().contains(path))) {
            filterChain.doFilter(request, response);
            return;
        }
        String correlationId = request.getHeader("Correlation-Id");
        LOGGER.info("Correlation-Id: {}", correlationId);
        String xCastId = request.getHeader("X-castId");
        LOGGER.info("xCastId: {}", xCastId);
        ThreadContext.put("Correlation-Id", correlationId);
        ThreadContext.put("X-castId", xCastId);

        filterChain.doFilter(request, response);
    }
}
