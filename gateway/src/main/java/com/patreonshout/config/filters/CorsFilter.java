package com.patreonshout.config.filters;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Filter for all http requests we receive
 */
@Component
public class CorsFilter implements Filter {

    /**
     * Creates the filter and assigns all attributes we want
     *
     * @param servletRequest is the config for request information we receive for each http request
     * @param servletResponse is the config for response we send to each http request
     * @param filterChain is the filter we are appending our options to
     * @throws IOException in case we cannot read our data correctly
     * @throws ServletException in case the servlet has an error
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getHeader("Origin") != null) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * init method for CORS
     *
     * @param filterConfig is the {@link javax.servlet.FilterConfig} used during init
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * destroy method for CORS
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
