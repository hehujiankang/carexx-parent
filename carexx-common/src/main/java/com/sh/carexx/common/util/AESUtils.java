package com.sh.carexx.common.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;

public final class AESUtils {
	private static Logger logger = LoggerFactory.getLogger(AESUtils.class);

	public static final String CHARSET = "UTF-8";
	public static final String AES = "AES";
	private static final int AES_KEY_SIZE = 128;
	private static final int AES_KEY_LEN = 16;
	private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static byte[] encrypt(String data, byte[] key) throws BizException {
		if (key == null || key.length != AES_KEY_LEN) {
			logger.error("AES密钥无效");
			throw new BizException(ErrorCode.SYS_ERROR);
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, AES);
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(data.getBytes(CHARSET));
		} catch (Exception e) {
			logger.error("AES加密失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static byte[] decrypt(byte[] cryptText, byte[] key) throws BizException {
		if (key == null || key.length != AES_KEY_LEN) {
			logger.error("AES密钥无效");
			throw new BizException(ErrorCode.SYS_ERROR);
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, AES);
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(cryptText);
		} catch (Exception e) {
			logger.error("AES解密失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
	}

	public static byte[] genRandomKey() throws BizException {
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance(AES);
		} catch (NoSuchAlgorithmException e) {
			logger.error("AES生成密钥失败", e);
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.init(AES_KEY_SIZE, random);
		Key key = keyGen.generateKey();
		return key.getEncoded();
	}
}
