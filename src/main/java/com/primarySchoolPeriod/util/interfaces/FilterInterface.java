package com.primarySchoolPeriod.util.interfaces;

import com.primarySchoolPeriod.util.JWTUtil;
import com.primarySchoolPeriod.util.exceptions.HeaderException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FilterInterface {
    public final static ConcurrentHashMap<String, String> userToken = new ConcurrentHashMap();

    @Value("${path.nomatching}")
    protected String nomatching;

    @Value("${handle.error}")
    protected String handleError;

    @Value("${key.error}")
    protected String keyError;

    @Value("${path.success}")
    protected String success;

    @Value("${header.token}")
    protected String token;

    @Autowired
    protected JWTUtil jwtUtil;

    public  Boolean verificationHeader(HttpServletRequest request, ServletResponse servletResponse) throws IOException, ServletException {
        Enumeration<String> headerNames = request.getHeaderNames();
            Integer count = 0;
            while (headerNames.hasMoreElements()) {
                String s = headerNames.nextElement();
                count = (s.equalsIgnoreCase(this.token)) ? count+1 : count;
            }
            if (count != 1) {
                request.setAttribute(keyError, HeaderException.class);
                request.getRequestDispatcher(handleError).forward(request, servletResponse);
                return  true;
            }
        return false;
    }

    public String tokenVerification( String token, HttpServletRequest request, ServletResponse servletResponse) throws IOException, ServletException {
        try {
            if (StringUtils.isEmpty(token) || token.length()==0){
                throw new MalformedJwtException("");
            }
            return jwtUtil.decodeToken(token);
        } catch (ExpiredJwtException e) {
            request.setAttribute(keyError, ExpiredJwtException.class);
            request.getRequestDispatcher(handleError).forward(request, servletResponse);
        } catch (SignatureException e) {
            request.setAttribute(keyError, SignatureException.class);
            request.getRequestDispatcher(handleError).forward(request, servletResponse);
        }catch (MalformedJwtException e){
            request.setAttribute(keyError, MalformedJwtException.class);
            request.getRequestDispatcher(handleError).forward(request, servletResponse);
        }
        return null;
    }
}
