package com.sh.carexx.common.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;

public final class RSAUtils {
	private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);

	public static final String CHARSET = "UTF-8";
	public static final String RSA = "RSA";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";
	private static final int RSA_KEY_SIZE = 1024;
	private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
	private static final String SHA1_WITH_RSA = "SHA1WithRSA";

	public static Map<String, byte[]> genKeyPair() throws BizException {
		try {
			SecureRandom secureRandom = new SecureRandom();
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(RSA_KEY_SIZE, secureRandom);
			KeyPair kp = kpg.generateKeyPair();
			Key publicKey = kp.getPublic();
			byte[] publicKeyBytes = publicKey.getEncoded();
			Key privateKey = kp.getPrivate();
			byte[] privateKeyBytes = privateKey.getEncoded();
			Map<String, byte[]> map = new HashMap<String, byte[]>();
			map.put(PUBLIC_KEY, publicKeyBytes);
			map.put(PRIVATE_KEY, privateKeyBytes);
			return map;
		} catch (Exception e) {
			logger.error("RSA生成密钥对失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static byte[] encrypt(byte[] src, byte[] publicKey) throws BizException {
		try {
			Key key = getPublicKey(publicKey);
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(src);
		} catch (Exception e) {
			logger.error("RSA加密失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static byte[] encryptWithPrivateKey(byte[] src, byte[] privateKey) throws BizException {
		try {
			Key key = getPrivateKey(privateKey);
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(src);
		} catch (Exception e) {
			logger.error("RSA私钥加密失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static byte[] decrypt(byte[] cryptText, byte[] privateKey) throws BizException {
		try {
			Key key = getPrivateKey(privateKey);
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(cryptText);
		} catch (Exception e) {
			logger.error("RSA解密失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static PublicKey getPublicKey(byte[] key) throws BizException {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (Exception e) {
			logger.error("RSA初始化公钥失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static PrivateKey getPrivateKey(byte[] key) throws BizException {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			return privateKey;
		} catch (Exception e) {
			logger.error("RSA初始化私钥失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static byte[] sign(String content, byte[] privateKey) throws BizException {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKey);
			KeyFactory keyf = KeyFactory.getInstance(RSA);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			Signature signature = Signature.getInstance(SHA1_WITH_RSA);
			signature.initSign(priKey);
			signature.update(content.getBytes(CHARSET));
			byte[] signed = signature.sign();
			return signed;
		} catch (Exception e) {
			logger.error("RSA私钥签名失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static boolean verify(String content, String sign, byte[] publicKey) throws BizException {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));
			Signature signature = Signature.getInstance(SHA1_WITH_RSA);
			signature.initVerify(pubKey);
			signature.update(content.getBytes(CHARSET));
			return signature.verify(Base64Utils.decode(sign.getBytes(CHARSET)));
		} catch (Exception e) {
			logger.error("RSA公钥验签失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}
}