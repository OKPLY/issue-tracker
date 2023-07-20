package issue_tracker.utility;


import io.jsonwebtoken.*;
import issue_tracker.service.impl.AuthServiceImpl;
import issue_tracker.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtUtil {
   private final UserServiceImpl userService;


    @Value("${app.secret}")
    private String secret;

    //1 Generate Token ---MANDATORY
    public String generateToken( String subject){
       return Jwts.builder()
                .setSubject(subject)
                .setIssuer("maggie")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS512,secret.getBytes())
                .compact();
    }

    //2. Read claims
    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    //3 Read Exp Date
    public Date getExpDate(String token){


        return getClaims(token).getExpiration();
    }
    //4.Read subject/username
    public String getUsername(String token){
        return getClaims(token)
                .getSubject();
    }
    //5 validate Exp Date
    public boolean isTokenExp(String token){
        Date expDate = getExpDate(token);
        return !(expDate.before(new Date(System.currentTimeMillis())));
    }

    public boolean validateToken(String token) {
        try {
            final String username = getUsername(token);
            return username.equals(getUserDetails(token).getUsername()) && isTokenExp(token);

        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Authentication getAuthentication(String token) {

        UserDetails userDetails = getUserDetails(token);
        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        return authentication;
    }

    public UserDetails getUserDetails(String token){
        Claims claims = getClaims(token);
        UserDetails userDetails = userService.loadUserByUsername(claims.getSubject()); // LEFT THIS HERE ON PURPOSE
        return userDetails;

    }


}
