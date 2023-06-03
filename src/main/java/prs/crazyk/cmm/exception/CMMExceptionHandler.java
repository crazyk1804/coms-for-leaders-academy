package prs.crazyk.cmm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import prs.crazyk.cmm.model.response.InvalidArgsResponse;
import prs.crazyk.cmm.model.response.ServerResponseEntity;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

// todo 여기에 Exception handling 정의
@RestControllerAdvice
@Slf4j
public class CMMExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ServerResponseEntity<ExceptionResponse> handleException(Exception exception, HttpServletRequest request) {
        log.error("request [{}] failed: {}", request.getRequestURI(), exception.getMessage());
        return ServerResponseEntity.STATUS(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ServerResponseEntity<ExceptionResponse> handleException(DuplicateKeyException exception, HttpServletRequest request) {
        return ServerResponseEntity.STATUS(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .message(String.format("중복 오류 발생: %s", exception.getMessage()))
                        .build()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ServerResponseEntity<ExceptionResponse> handleBadCredentialException(BadCredentialsException exception, HttpServletRequest request) {
        return ServerResponseEntity.STATUS(HttpStatus.UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .message("사용자 ID 혹은 비밀번호를 확인 하세요.")
                        .userFriendly(UserFriendlyRank.friendly)
                        .build()
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ServerResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException exception, HttpServletRequest request) {
        return ServerResponseEntity.STATUS(HttpStatus.FORBIDDEN).body(
                ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(BindException.class)
    public ServerResponseEntity<InvalidArgsResponse> handleMethodArgumentNotValidException(BindException exception, HttpServletRequest request) {
        return ServerResponseEntity.STATUS(HttpStatus.BAD_REQUEST).body(
                new InvalidArgsResponse(exception)
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ServerResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {
        return ServerResponseEntity.STATUS(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .message("Data integrity violation occurred.")
                        .userFriendly(UserFriendlyRank.notAtAll)
                        .build()
        );
    }
}
