package prs.crazyk.cmm.exception;

import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpResponse;

@Getter
@Setter
public class HttpResponseException extends Exception {
    private HttpResponse<?> response;

    public HttpResponseException(String message, HttpResponse<?> response) {
        super(message);
        this.response = response;
    }
}
