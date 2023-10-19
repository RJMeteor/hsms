package com.primarySchoolPeriod.config;


import com.primarySchoolPeriod.util.Metadata;
import com.primarySchoolPeriod.util.R;
import com.primarySchoolPeriod.util.exceptions.GrantException;
import com.primarySchoolPeriod.util.exceptions.HeaderException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler({HeaderException.class})
    public ResponseEntity<R> headerException(HeaderException e) {
        R<String> build = R.<String>builder()
                .code(Metadata.HeadException.getCode())
                .data(Metadata.HeadException.getMessage()).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<R> signatureException(SignatureException e) {
        R<String> build = R.<String>builder()
                .code(Metadata.SignatureException.getCode())
                .data(Metadata.SignatureException.getMessage()).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }

    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity<R> malformedJwtException(MalformedJwtException e) {
        R<String> build = R.<String>builder()
                .code(Metadata.MalformedJwtException.getCode())
                .data(Metadata.MalformedJwtException.getMessage()).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<R> tamperTokenException(ExpiredJwtException e) {
        R<String> build = R.<String>builder()
                .code(Metadata.ExpiredJwtException.getCode())
                .data(Metadata.ExpiredJwtException.getMessage()).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<R> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<String> list = new ArrayList<>();
        result.getFieldErrors().forEach(error -> {
            String msg = error.getDefaultMessage();
            list.add(msg);
        });
        R<List<String>> build = R.<List<String>>builder()
                .code(Metadata.MethodArgumentNotValidException.getCode())
                .data(list).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }

    @ExceptionHandler({LoginException.class})
    public ResponseEntity<R> loginException(LoginException e){
        R<String> build = R.<String>builder()
                .code(Metadata.LogingException.getCode())
                .data(Metadata.LogingException.getMessage()).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }


    @ExceptionHandler({GrantException.class})
    public ResponseEntity<R> grantException(GrantException e){
        R<String> build = R.<String>builder()
                .code(Metadata.GrantException.getCode())
                .data(Metadata.GrantException.getMessage()).build();
        return new ResponseEntity<R>(build, HttpStatus.OK);
    }

}
