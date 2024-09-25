package kr.plea.echomodule.encrypter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.plea.echomodule.service.AsyncService;

public class MemberIdEncryptor {

	private static final Logger log = LoggerFactory.getLogger(AsyncService.class);
	private static final String SECRET_KEY = "MySecretKey12345";
	private static final String INIT_VECTOR = "RandomInitVector";

	public static Long decrypt(String encryptedId) {
		try {
			// 실제 복호화 로직
			String decryptedString = aesDecrypt(encryptedId);
			return Long.parseLong(decryptedString);
		} catch (Exception e) {
			log.info("Error decrypting: " + encryptedId);
		}
		return null;
	}

	public static String aesEncrypt(String plainText) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String aesDecrypt(String encryptedText) throws Exception {
		// SecretKeySpec와 IvParameterSpec 생성
		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));

		// Cipher 인스턴스 생성
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

		// Base64 디코딩 및 복호화
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		// 복호화된 문자열 반환
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}
}
