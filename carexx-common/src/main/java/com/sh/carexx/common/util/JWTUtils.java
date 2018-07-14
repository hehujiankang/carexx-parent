package com.sh.carexx.common.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtUtils {
    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final static long expiredTime = 60 * 60;

    private final static String key = "secret";

    private JwtUtils() {

    }

    public static String getToken(ClaimInfo claimInfo) throws Exception {
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        long iat = System.currentTimeMillis();
        long exp = iat + expiredTime * 1000L;
        String token = JWT.create()
                .withHeader(header)
                .withIssuedAt(new Date(iat))
                .withExpiresAt(new Date(exp))
                .withClaim("userId", claimInfo.getUserId())
                .withClaim("userName", claimInfo.getUserName())
                .sign(Algorithm.HMAC256(key));
        return token;
    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
            jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            return jwt.getClaims();
        } catch (Exception ex) {
            logger.error("Verify jwt token failed, token={}", token, ex);
            return null;
        }
    }

    static class ClaimInfo {
        private String userId;

        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static void main(String[] args) {
        JwtUtils.ClaimInfo info = new JwtUtils.ClaimInfo();
        info.setUserId("1");
        info.setUserName("1");
        try {
            System.out.print(JwtUtils.getToken(info));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
