//package ir.quiz.quiz.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtService {
//
//    private static final String SECRET_KET = "76a143bda362a8a3eaa6c875a2e3358b5b2453e35247bf002e536e774518bfb4";
//
//    public String findUserName(String jwtToken) {
//        return findClaim(jwtToken, (c) -> c.getSubject());
//    }
//
//    public <T> T findClaim(String jwtToken, Function<Claims, T> claimsResolver) {
//        Claims claims = findAllClaims(jwtToken);
//        return claimsResolver.apply(claims);
//    }
//
//    public String generateJwtToken(Map<String, Object> claims, UserDetails userDetails) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + (60 * 5 * 1_000)))
//                .signWith(getSignInKey(), SignatureAlgorithm.ES256)
//                .compact();
//    }
//
//    public String generateJwtToken(UserDetails userDetails) {
//        return generateJwtToken(new HashMap<>(),userDetails);
//    }
//
//    public Boolean isTokenValid(String jwtToken, UserDetails userDetails){
//        String username = findUserName(jwtToken);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
//    }
//
//    private Boolean isTokenExpired(String jwtToken) {
//        return findExpiration(jwtToken).before(new Date(System.currentTimeMillis()));
//    }
//
//    private Date findExpiration(String jwtToken) {
//        return findClaim(jwtToken, (c) -> c.getExpiration());
//    }
//
//    private Claims findAllClaims(String jwtToken) {
//        return Jwts
//                .parser()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(jwtToken)
//                .getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//}
