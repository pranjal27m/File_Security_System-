package project.client;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Secretkey {

	public static SecretKey generateSecretKey() {
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		
		keyGen.init(256);

		
		SecretKey secretKey = keyGen.generateKey();

		return secretKey;
	}

	public static String keyToString(SecretKey Key) {
		
		byte encoded[] = Key.getEncoded();

	
		String encodedKey = Base64.getEncoder().encodeToString(encoded);

		return encodedKey;
	}

	public static SecretKey decodeKeyFromString(String keyStr) {
		
		byte[] decodedKey = Base64.getDecoder().decode(keyStr);

		
		SecretKey originalsecretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		return originalsecretKey;
	}
}