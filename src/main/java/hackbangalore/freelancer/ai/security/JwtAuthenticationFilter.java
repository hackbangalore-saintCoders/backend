package hackbangalore.freelancer.ai.security;

import ch.qos.logback.core.encoder.EchoEncoder;
import hackbangalore.freelancer.ai.security.config.UserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        log.info("Header :" + requestHeader );

        String username = null;
        String token = null;


        if(requestHeader != null && requestHeader.startsWith("Bearer")){

            token = requestHeader.substring(7);

            try{
                username = jwtHelper.getUserNameFromToken(token);
            }catch (Exception err){
                log.info("Username fetch failed");
                err.printStackTrace();
            }
        }else{
            log.info("Invalid header value");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            Boolean validateToken = jwtHelper.validateToken(token,userDetails.getUsername());


            if (validateToken){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                log.info("Invalid validation");
            }
        }

        filterChain.doFilter(request, response);


    }
}
