////package com.cryptosoft.config;
////
////import java.io.IOException;
////
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
////import org.springframework.stereotype.Component;
////import org.springframework.web.filter.OncePerRequestFilter;
////
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import lombok.RequiredArgsConstructor;
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Component
////@RequiredArgsConstructor
////public class JwtAuthFilter extends OncePerRequestFilter {
////
////	private final JwtUtils jwtUtils;
////	private final UserDetailsService userDetailsService;
////
////	@Override
////	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////			throws ServletException, IOException {
////		final String authHeader = request.getHeader("Authorization");
////		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////			log.info("AUTH header missing Or it does not starts with Bearer forwarding to next filter");
////			filterChain.doFilter(request, response);
////			return;
////		}
////		final String jwtToken = authHeader.substring(7);
////		final String userEmail = jwtUtils.extractUsername(jwtToken);
////
////		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////			UserDetails user = userDetailsService.loadUserByUsername(userEmail);
////
////			if (jwtUtils.isTokenValid(jwtToken, user)) {
////				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
////						null, user.getAuthorities());
////				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////			}
////		}
////		filterChain.doFilter(request, response);
////	}
////
////}
//package com.cryptosoft.config;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);
//
//    private final JwtUtils jwtUtils;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = authHeader.substring(7);
//        String username = jwtUtils.extractUsername(token);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtUtils.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
//package com.cryptosoft.config;
//
//import jakarta.servlet.FilterChain;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import java.io.IOException;
//
//public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
//    private final JwtUtils jwtUtils;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String token = jwtUtils.extractToken(httpRequest);
//        if (token != null && jwtUtils.validateToken(token)) {
//            String username = jwtUtils.extractUsername(token);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (userDetails != null) {
//                SecurityContextHolder.getContext().setAuthentication(jwtUtils.getAuthentication(token, userDetails));
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}

//package com.cryptosoft.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.lang.NonNull;
//import java.io.IOException;
//
//public class JwtAuthFilter extends OncePerRequestFilter {
//    private final JwtUtils jwtUtils;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            // Extract the token (remove "Bearer " prefix)
//            final String jwt = authHeader.substring(7);
//            final String username = jwtUtils.extractUsername(jwt);
//
//            // Only process if we have a username and no existing authentication
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//                
//                if (jwtUtils.validateToken(jwt)) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                    );
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Cannot set user authentication: {}", e);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
//package com.cryptosoft.config;
//
//import java.io.IOException;
//
//
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//	private final JwtUtils jwtUtils;
//	private final UserDetailsService userDetailsService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		final String authHeader = request.getHeader("Authorization");
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			log.info("AUTH header missing Or it does not starts with Bearer forwarding to next filter");
//			filterChain.doFilter(request, response);
//			return;
//		}
//		final String jwtToken = authHeader.substring(7);
//		final String userEmail = jwtUtils.extractUsername(jwtToken);
//
//		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails user = userDetailsService.loadUserByUsername(userEmail);
//
//			if (jwtUtils.isTokenValid(jwtToken, user)) {
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
//						null, user.getAuthorities());
//				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//			}
//		}
//		filterChain.doFilter(request, response);
//	}
//
//}
package com.cloud.ChronoSyncPro.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("AUTH header missing Or it does not starts with Bearer forwarding to next filter");
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken = authHeader.substring(7);
        final String userEmail = jwtUtils.extractUsername(jwtToken);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(userEmail);

            if (jwtUtils.isTokenValid(jwtToken, user)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                        null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
