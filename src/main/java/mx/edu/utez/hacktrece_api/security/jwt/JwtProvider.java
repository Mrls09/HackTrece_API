package mx.edu.utez.hacktrece_api.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class JwtProvider {

    Logger logger = Logger.getLogger(JwtProvider.class.getName());

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.expiration.restore}")
    private long expirationRestore;

    public String generateToken(Authentication auth) {
        UserDetails user = (UserDetails) auth.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + expiration * 1000);
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date()).setExpiration(tokenValidity).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseJwtClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) return parseJwtClaims(token);
            return parseJwtClaims(null);
        } catch (ExpiredJwtException e) {
            String contextualInfo = "The JWT token has expired. Additional Info: " + e.getMessage();
            logger.severe(contextualInfo);
            throw e;
        } catch (Exception e) {
            String contextualInfo = "An error occurred while trying to resolve the JWT token. Additional Info: " + e.getMessage();
            logger.severe(contextualInfo);
            throw e;

        }
    }

    public String resolveToken(HttpServletRequest req) {
        String tokenHeader = "Authorization";
        String bearerToken = req.getHeader(tokenHeader);
        String tokenType = "Bearer ";
        if (bearerToken != null && bearerToken.startsWith(tokenType)) return bearerToken.replace(tokenType, "");
        return null;
    }

    public boolean validateClaims(Claims claims, String token) {
        try {
            parseJwtClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}
