package com.example.demo.SequrityConfig;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Services.JWTService2;
import com.example.demo.Services.MyUserDetailsService2;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JWTFilter2 extends OncePerRequestFilter{

//	eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJEYXJzaGFuIiwiaWF0IjoxNzUyMTI0MTIwLCJleHAiOjE3NTIxMjc3MjB9.jv3t0h4gUhYvKJUyoq8F3E1Cr9G485w22IjanuLM7CKTdraUsC7nkP0rGC_2siJn
    @Autowired
    private JWTService2 jwtService;

    @Autowired
    private MyUserDetailsService2 userDetailsService;
    
//    Step2: Then provide the implementation for “doFilterInternal()” internal method.

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
//    	Step3 :In “doFilterInternal()” internal method we have to get value from request.    	
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//        	Step4: Then Get actual token value from full token value.
            token = authHeader.substring(7); // remove "Bearer " prefix
//          Stpe5: further we have to extractUserName by creating method in jwtService.
            username = jwtService.extractUserName(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
//        	Step6: Then we have to fetch userDetails object by the help of loadUserByUsername method.
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//            Stpe7: Then we have to create object of UsernamePasswordAuthenticationToken class and provide parameter with userDetails object and its authorisation status, which denotes that object is authenticated.
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                		
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
