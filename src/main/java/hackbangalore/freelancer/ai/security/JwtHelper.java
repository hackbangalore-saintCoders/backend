package hackbangalore.freelancer.ai.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper{


    private static final String SECRET = "357538782F413F4428472B4B6250655368566D59703373367639792442264529";

    long thirtyDaysInMillis = 30L * 24L * 60L * 60L * 1000L;


    public String generateToken(String user_id, String user_name) {
        System.out.println(user_id);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,user_id,user_name);
    }

    private String createToken(Map<String, Object> claims, String user_id, String user_name) {
        return Jwts.builder()
                .setClaims(claims)
                .claim("user_id",user_id)
                .claim("user_name", user_name)
                .claim("role","ADMIN")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + thirtyDaysInMillis))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, String userName) {
        Claims claims = getAllClaims(token);
        Date currentDate = new Date();
        Date issuedDate = claims.getIssuedAt();
        Date expirationDate = claims.getExpiration();


        return userName.equals(getUserNameFromToken(token)) && !currentDate.before(issuedDate) && !currentDate.after(expirationDate);

    }

    public String getUUIDFromToken(String token){
        Claims claims = getAllClaims(token);
        return (String) claims.get("user_id");
    }

    public String getUserNameFromToken(String token){

        Claims claims = getAllClaims(token);
        return (String) claims.get("user_name");
    }

    public String getRoleFromToken(String token){
        Claims claims = getAllClaims(token);
        return (String) claims.get("role");
    }

    private Claims getAllClaims(String token){
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
        return claims.getBody();
    }
}
