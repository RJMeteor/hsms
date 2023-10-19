package com;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.Date;

public class JWT {

    @Test
    public void jwt(){
        String token = Jwts.builder()
                .setSubject("primarySchoolPeriod")
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .claim("userId", "13088196807")
                .signWith(SignatureAlgorithm.HS512, "renjia")
                .compact();
        System.out.println(token);
    }

    @Test
    public void md5(){
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s);
    }
}
