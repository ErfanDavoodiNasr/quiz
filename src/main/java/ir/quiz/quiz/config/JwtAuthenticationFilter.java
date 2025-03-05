//package ir.quiz.quiz.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.lang.NonNull;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse resp, @NonNull FilterChain chain) throws ServletException, IOException {
//        String authHeader = req.getHeader("auth_token");
//        String jwtToken;
//        String username;
//
//        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("bearer ")){
//            chain.doFilter(req,resp);
//            return;
//        }
//        jwtToken = authHeader.substring(7);
//        username = jwtService.findUserName(jwtToken);
//        if (StringUtils.isNotBlank(authHeader) && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//        }
//    }
//}
