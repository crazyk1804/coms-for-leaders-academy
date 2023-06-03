package prs.crazyk.cmm.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    protected String message;
    protected UserFriendlyRank userFriendly;

    public UserFriendlyRank getUserFriendly() {
        if(userFriendly==null) return UserFriendlyRank.notAtAll;
        return userFriendly;
    }
}
