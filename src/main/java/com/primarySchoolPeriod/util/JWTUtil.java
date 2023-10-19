package com.primarySchoolPeriod.util;

import com.primarySchoolPeriod.filter.WebFilter;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {
    @Value("${secretKey}")
    private String secretKey;
    @Value("#{${expirationTime}}")
    private Long expirationTime;
    @Value("${userKey}")
    private String userKey;

    public String decodeToken(String token)  throws ExpiredJwtException,  SignatureException,MalformedJwtException{
        String userId = ((String) Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().get(userKey));
        return userId;
    }

    public String encodeToken(String userId){
        String token = Jwts.builder()
                .setSubject("primarySchoolPeriod")
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim(userKey, userId)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        WebFilter.userToken.put(userId,token);
       return token;
    }
}
