package com.primarySchoolPeriod.config;

import com.primarySchoolPeriod.util.Metadata;
import com.primarySchoolPeriod.util.R;
import com.primarySchoolPeriod.util.exceptions.HeaderException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("captureErrorsPassedDown")
public class CaptureErrorsPassedDown {
    @Value("${key.error}")
    private String keyError;

    @RequestMapping("error")
    public void error(HttpServletRequest request) throws HeaderException, ExpiredJwtException, LoginException {
        Class<?> attribute = (Class<?>) request.getAttribute(keyError);
        if (HeaderException.class.equals(attribute)) {
            throw new HeaderException();
        }
        if (ExpiredJwtException.class.equals(attribute)){
            throw new ExpiredJwtException(null,null,"token过期了");
        }
        if (SignatureException.class.equals(attribute)){
            throw new SignatureException("token被篡改");
        }
        if(MalformedJwtException.class.equals(attribute)){
            throw new MalformedJwtException("token格式有误");
        }
    }

    @RequestMapping("welcome")
    @ResponseBody
    public R<String> welcome(HttpServletRequest request) {
        return R.<String>builder()
                .code(Metadata.NoLogin.getCode())
                .data(Metadata.NoLogin.getMessage()).build();
    }

}
