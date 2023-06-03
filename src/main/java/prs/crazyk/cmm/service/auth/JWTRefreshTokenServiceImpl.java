package prs.crazyk.cmm.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import prs.crazyk.cmm.exception.TokenRefreshException;
import prs.crazyk.cmm.model.auth.JWTRefreshToken;
import prs.crazyk.cmm.repository.JWTRefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JWTRefreshTokenServiceImpl {
    @Value("#{ new Long('${jwt.refresh.expiry.duration}')}")
    private long expiryDuration;

    private final JWTRefreshTokenRepository repository;

    public JWTRefreshToken issueRefreshToken(String userId) {
        JWTRefreshToken token = JWTRefreshToken.builder()
            .userId(userId)
            .refreshToken(UUID.randomUUID().toString())
            .expiryTime(Instant.now().toEpochMilli() + expiryDuration)
            .build();
        repository.insertOrUpdateUserRefreshToken(token);
        return token;
    }

    public void verifyRefreshToken(String userId, String tokenValue) {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(tokenValue))
            throw new TokenRefreshException("user id and token value must not be empty");

        JWTRefreshToken token = repository.selectUserRefreshToken(userId);
        if(!tokenValue.equals(token.getRefreshToken()))
            throw new TokenRefreshException(tokenValue, "not valid token");

        if(token.getExpiryTime() < Instant.now().toEpochMilli())
            throw new TokenRefreshException(tokenValue, "expired");
    }

    public void discardRefreshToken(String userId) {
        repository.deleteRefreshToken(userId);
    }
}
