package pers.zjc.sams.utils;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
    private static final String skey="SDKJSFZVCXZVDANGNGHJVC08-L";
    /**
     *
     */
    public static SecretKey generalKey(){
        String stringKey = skey;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    /**
     *
     */
    public static String createJWT(String id,String issuer, Map<String, Object> claims, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Time now = new Time(nowMillis);
        System.out.println( " now :" + now);

        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setSubject("1111")
                .signWith(signatureAlgorithm, key);
        System.out.println( " builder :" + builder);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            System.out.println( " expMillis :" + expMillis);
            Time exp = new Time(expMillis);
            System.out.println( " exp :" + exp);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
    /**
     *
     */
    public static String createJWT(String username, String pwd,long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userName", username);
        claims.put("pwd", pwd);

        long nowMillis = System.currentTimeMillis();
        Time now = new Time(nowMillis);
        System.out.println( " now :" + now);

        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuer(username)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, key);
        System.out.println( " builder :" + builder);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            System.out.println( " expMillis :" + expMillis);
            Time exp = new Time(expMillis);
            System.out.println( " exp :" + exp);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
    /**
     *
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        return claims;
    }
}