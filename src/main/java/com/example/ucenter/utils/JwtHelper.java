package com.example.ucenter.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by admin on 2018/3/30.
 */
public class JwtHelper {
    public  static Claims parseJWT(String jsonWebToken, String base64Security){
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

    public  static  String createJWT(String name,String userId,String role,
                                     String audience,String issuer, long TTLMillis, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);

        //生成签名密钥
        byte[] apikeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apikeySecretBytes,signatureAlgorithm.getJcaName());
        //添加构成JWT参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ","JWT")
                .claim("role",role)
                .claim("unique_name",name)
                .claim("userid",userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm,signingKey);
        //添加Token 过期时间
        if(TTLMillis >=0){
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(date);
        }
        //生成JWT
        return builder.compact();
    }
}
