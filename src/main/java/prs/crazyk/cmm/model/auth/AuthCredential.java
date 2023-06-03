package prs.crazyk.cmm.model.auth;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthCredential {
    @NotEmpty
    private String identity;
    @NotNull
    private String password;
}
