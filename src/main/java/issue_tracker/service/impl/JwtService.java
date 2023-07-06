package issue_tracker.service.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "674838616e63546f514b6c48654a4777592b5167537756375862654b6548495738337a30485a385167537743514b4c78386e514532506241425072442b48767134745876306b373971696c416278416173747563756b574c77786d775879415377584836424552482f734f734e31433037706f557a526a434a67556b4d7368644a57583942752b4836724d785a37365a49322f2b4633714c39536c506854482f6c4f7558634d557255574b3471614e504935324f37645a58784837395a4c58694b72495439377a30683171786e6d526d484e62374f2b512f50524f67746f335665705452742b415849335935726c595039716e6f396f6445334574793278356c5978506e55662f6f6d697a79527878643052796470335973614646693670565053766272782b53725244656b622f3043797165767a42637054716a46717071746277314a555975715a36647a37336c59616d386a336750693263674e363649546b6870556c3937686a64513d0d0a";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver)    {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
