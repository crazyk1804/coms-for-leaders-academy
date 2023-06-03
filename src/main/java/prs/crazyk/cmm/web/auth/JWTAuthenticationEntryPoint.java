package prs.crazyk.cmm.web.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import prs.crazyk.cmm.model.response.ServerResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServerResponse<String> resp = new ServerResponse<>();

        resp.setBody("Unauthorized");
//        AuthException ex = (AuthException)request.getAttribute("authException");
//        if(ex==null)
//            resp.setResponse(ServiceResponse.AUTH_UNAUTHORIZE, "Unauthorized");
//        else {
//            resp.setResponse(ex.getCode(), ex.getMessage());
//            resp.setBody(ex.getOriginalMessage());
//        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try(OutputStream os = response.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(os, resp);
            os.flush();
        }
    }
}
