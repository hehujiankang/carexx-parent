package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.AESUtils;
import com.sh.carexx.common.util.Base64Utils;
import com.sh.carexx.common.util.RSAUtils;

@Service
public class SecurityManager {
	@Value("${rsa.priKey}")
	private String rsaPriKey;

	@Value("${rsa.pubKey}")
	private String rsaPubKey;

	@Value("${aes.secretKey}")
	private String aesSecretKey;

	/**
	 * 
	 * Function:AES加密 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:23:37 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String encryptWithAES(String plainText, byte[] key) throws BizException {
		byte[] cryptBytes = AESUtils.encrypt(plainText, Base64Utils.decode(key));
		return Base64Utils.encode2String(cryptBytes);
	}

	/**
	 * 
	 * Function:AES加密，使用本地配置密钥 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:23:42 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String encryptWithAES(String plainText) throws BizException {
		return this.encryptWithAES(plainText, this.decryptDataWithRSA(this.rsaPriKey));
	}

	/**
	 * 
	 * Function:AES解密 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:23:45 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public byte[] decryptDataWithAES(String cryptText, byte[] key) throws BizException {
		byte[] decryptBytes = AESUtils.decrypt(Base64Utils.decode2Bytes(cryptText), Base64Utils.decode(key));
		return decryptBytes;
	}

	/**
	 * 
	 * Function:AES解密，使用本地配置密钥 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:23:49 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public byte[] decryptDataWithAES(String cryptText) throws BizException {
		return this.decryptDataWithAES(cryptText, this.decryptDataWithRSA(this.rsaPriKey));
	}

	/**
	 * 
	 * Function:RSA公钥加密 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:23:55 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String encryptDataWithRSA(byte[] plainText, String publicKey) throws BizException {
		byte[] cryptBytes = RSAUtils.encrypt(plainText, Base64Utils.decode2Bytes(publicKey));
		return Base64Utils.encode2String(cryptBytes);
	}

	/**
	 * 
	 * Function:RSA公钥加密，使用本地配置 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:04 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String encryptDataWithRSA(byte[] plainText) throws BizException {
		return this.encryptDataWithRSA(plainText, this.rsaPubKey);
	}

	/**
	 * 
	 * Function:RSA私钥解密 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:08 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public byte[] decryptDataWithRSA(String cryptText, String privateKey) throws BizException {
		byte[] decryptBytes = RSAUtils.decrypt(Base64Utils.decode2Bytes(cryptText),
				Base64Utils.decode2Bytes(privateKey));
		return decryptBytes;
	}

	/**
	 * 
	 * Function:RSA私钥解密，使用本地配置<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:11 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public byte[] decryptDataWithRSA(String cryptText) throws BizException {
		return this.decryptDataWithRSA(cryptText, this.rsaPriKey);
	}

	/**
	 * 
	 * Function:RSA私钥签名 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:15 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String signWithRSAPrivateKey(String content, String privateKey) throws BizException {
		byte[] signBytes = RSAUtils.sign(content, Base64Utils.decode2Bytes(privateKey));
		return Base64Utils.encode2String(signBytes);
	}

	/**
	 * 
	 * Function:RSA私钥签名，使用本地配置 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:15 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String signWithRSAPrivateKey(String content) throws BizException {
		return this.signWithRSAPrivateKey(content, this.rsaPriKey);
	}

	/**
	 * 
	 * Function:RSA公钥验签<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:20 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public boolean verifySignWithRSA(String content, String sign, String publicKey) throws BizException {
		return RSAUtils.verify(content, sign, Base64Utils.decode2Bytes(publicKey));
	}

	/**
	 * 
	 * Function:RSA公钥验签，使用本地配置<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月8日上午11:24:20 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public boolean verifySignWithRSA(String content, String sign) throws BizException {
		return this.verifySignWithRSA(content, sign, this.rsaPubKey);
	}

	/**
	 * 
	 * Function:生成随机密钥 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月13日下午4:34:04 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public byte[] getRandomSalt() throws BizException {
		byte[] randomKeyBytes = AESUtils.genRandomKey();
		return Base64Utils.encode(randomKeyBytes);
	}
}
