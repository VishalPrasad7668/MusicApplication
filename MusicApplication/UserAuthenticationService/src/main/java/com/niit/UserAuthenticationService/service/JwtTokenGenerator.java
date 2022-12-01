package com.niit.UserAuthenticationService.service;

import com.niit.UserAuthenticationService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtTokenGenerator implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateTokenForUser(User user) {

        String jwtToken = null;
        jwtToken = Jwts.builder().setSubject(String.valueOf(user.getUserId())).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"Secured-Key").compact();

        Map<String,String> map = new HashMap<>();
        map.put("TOKEN",jwtToken);
        map.put("MESSAGE","LOGGED IN SUCCESSFULLY !!");
        return map;
    }
}
