package com.primarySchoolPeriod.filter;

import com.primarySchoolPeriod.util.interfaces.FilterInterface;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.servlet.annotation.WebFilter(urlPatterns = {"/*"})
public class WebFilter extends FilterInterface implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (antPathMatcher.match(this.handleError, requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (verificationHeader(request, servletResponse)) return;
            String token = request.getHeader(this.token);
            if (antPathMatcher.match(nomatching, requestURI)) {
                if (StringUtils.isEmpty(token)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
                if (token.equals(userToken.get(tokenVerification(token, request, servletResponse)))) {
                    request.getRequestDispatcher(success).forward(servletRequest, servletResponse);
                }
            } else if (!StringUtils.isEmpty(token)) {
                String tokenVerification = tokenVerification(token, request, servletResponse);
                if (!ObjectUtils.isEmpty(tokenVerification)) {
                    if (token.equals(userToken.get(tokenVerification(token, request, servletResponse)))) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            } else {
                request.setAttribute(keyError, MalformedJwtException.class);
                request.getRequestDispatcher(handleError).forward(request, servletResponse);
            }
        }
    }

}
