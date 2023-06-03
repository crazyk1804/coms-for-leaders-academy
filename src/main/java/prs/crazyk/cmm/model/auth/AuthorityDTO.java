package prs.crazyk.cmm.model.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter @Setter
public class AuthorityDTO implements GrantedAuthority {
	private long idx;
	private String authority;
	private String authorityName;
}
