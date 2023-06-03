package prs.crazyk.cmm.model.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JWTRefreshToken {
    private String userId;
    private String refreshToken;
    private long expiryTime;
}
