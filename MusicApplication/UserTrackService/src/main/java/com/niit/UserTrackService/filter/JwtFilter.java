package com.niit.UserTrackService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("authorization");

        //if the method is option s the request can pass through not validation of token is required
//        if ("OPTIONS".equals(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//            filterChain.doFilter(request,response);
//        }

        if (authHeader==null || !authHeader.startsWith("Bearer")){
            throw new ServletException("Invalid or Missing Token");
        }

        String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("Secured-Key").parseClaimsJws(token).getBody();

        request.setAttribute("claims",claims);
        System.out.println();
        System.out.println("claims = " + claims);
        filterChain.doFilter(request,response);
    }
}
