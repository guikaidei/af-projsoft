package br.insper.af.Common;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;

public class TokenUtils {

    public static String getEmailFromToken(String token) {
        try {
            JWT jwt = JWTParser.parse(token.split(" ")[1]);
            return (String) jwt.getJWTClaimsSet().getClaim("email");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getIss(String token) {
        try {
            JWT jwt = JWTParser.parse(token.split(" ")[1]);
            String iss = (String) jwt.getJWTClaimsSet().getClaim("iss");
            return iss.substring(iss.lastIndexOf('/') + 1);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractSignatures(String token) {
        String[] parts = token.split("\\.");
        return parts[2];
    }

    public static String removeBearerPrefix(String token) {
        if (token.contains("Bearer")) {
            return token.split(" ")[1];
        }
        return token;
    }
}