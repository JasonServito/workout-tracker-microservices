package FrontEnd;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtBuilder;
import java.security.Key;
import java.util.Date;
import Helper.User;
import Persistence.UserDAO;

public class Authenticate {
    private final Key signingKey;
    private final long ttlMillis = 3600000; // 1 hour validity

    public Authenticate() {
        signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String authenticate(String username, String password) {
        UserDAO dao = new UserDAO();
        User user = dao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            JwtBuilder builder = Jwts.builder()
                    .setSubject(username)
                    .claim("userID", user.getId())
                    .setIssuedAt(now)
                    .setExpiration(new Date(nowMillis + ttlMillis))
                    .signWith(signingKey, SignatureAlgorithm.HS256);
            return builder.compact();
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
