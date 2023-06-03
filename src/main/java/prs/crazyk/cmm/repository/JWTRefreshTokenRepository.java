package prs.crazyk.cmm.repository;

import org.springframework.stereotype.Repository;
import prs.crazyk.cmm.model.auth.JWTRefreshToken;

@Repository("JWTRefreshTokenRepository")
public interface JWTRefreshTokenRepository {
    JWTRefreshToken selectUserRefreshToken(String userId);
    int insertOrUpdateUserRefreshToken(JWTRefreshToken token);
    int deleteRefreshToken(String userId);
}
