package com.sh.carexx.common.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	private static final String DEFAULT_SUBJECT = "auth";
	private static final String KEY_ALG = "AES";

	private static SecretKey generalKey(String secretKey) {
		byte[] secretKeyBytes = Base64Utils.decode2Bytes(secretKey);
		SecretKey key = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, KEY_ALG);
		return key;
	}

	public static String createJWT(String id, int ttlSeconds, String secretKey) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey(secretKey);
		JwtBuilder builder = Jwts.builder().setId(id).setSubject(DEFAULT_SUBJECT).setIssuedAt(now)
				.signWith(signatureAlgorithm, key);
		if (ttlSeconds >= 0) {
			long expMillis = nowMillis + ttlSeconds * 1000;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate);
		}
		return builder.compact();
	}

	public static String parseJWT(String jwt, String secretKey) throws Exception {
		SecretKey key = generalKey(secretKey);
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		return claims.getId();
	}
}
