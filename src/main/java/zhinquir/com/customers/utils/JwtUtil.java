package zhinquir.com.customers.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import zhinquir.com.customers.entities.User;

import java.util.Date;

public class JwtUtil {

    private static final String SECRETER_KEY = "ge6238";
    private static Algorithm algorithm = Algorithm.HMAC256(SECRETER_KEY);

    public static String generateToken(User user){

        // use desing patron builder, concat set
       // Algorithm algorithm = Algorithm.HMAC256(SECRETER_KEY);
        String token = JWT.create()
                .withIssuer("Zhinquir") // quien genera el token
                .withClaim("userId",user.getId().toString()) // quien reclama el inicio de sesion
                .withIssuedAt(new Date())
                .withExpiresAt(getExpiresDate())
                .sign(algorithm); // firma

        return token;
    }

    private static Date getExpiresDate() {
        return new Date(System.currentTimeMillis()
                + (1000L * 60 * 60 * 24 * 14)); // 14 days from current day
    }

    public String getUserIdByToken(String token){
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Zhinquir")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token); // verifica que el token no haya sido manipulado o haya expirado
        String userId = decodedJWT.getClaim("userId").asString();// se usa la misma que se uso para generar el tokeb
        return userId;
    }
}
