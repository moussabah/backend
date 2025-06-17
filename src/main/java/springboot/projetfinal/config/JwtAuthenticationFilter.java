package springboot.projetfinal.config;

import java.io.IOException; // ✅ Correct
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboot.projetfinal.service.CustomUserDetailsService;
import springboot.projetfinal.service.JwtService;
import springboot.projetfinal.service.TokenBlacklistService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired private CustomUserDetailsService detailsService;
    @Autowired private TokenBlacklistService blacklist;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (blacklist.isBlacklisted(token)) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token révoqué");
                return;
            }

            String login = jwtService.extractLogin(token);
            if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = detailsService.loadUserByUsername(login);
                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        // IMPORTANT : laisser la chaîne continuer quoi qu'il arrive
        chain.doFilter(req, res);
    }

}
