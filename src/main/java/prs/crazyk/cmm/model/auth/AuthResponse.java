package prs.crazyk.cmm.model.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
//    private LoginVO member;
    private String token;
}
