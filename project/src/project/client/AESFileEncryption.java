package project.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESFileEncryption {
	public static void main(String[] args, String path, String key, String filename) throws Exception {
		
		encryptMethod(path,key,filename);
		
	}
	
	static void encryptMethod(String path, String key,String filename) {
		try {
			
		
			FileInputStream inFile = new FileInputStream(path+ "\\" + filename);

			
			FileOutputStream outFile = new FileOutputStream(path+ "\\" + filename+ ".encrypted");

			
			byte[] salt = new byte[8];
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.nextBytes(salt);
			FileOutputStream saltOutFile = new FileOutputStream(path+ "\\" + "salt.enc");
			saltOutFile.write(salt);
			saltOutFile.close();

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, 65536,256);
			SecretKey secretKey = factory.generateSecret(keySpec);
			SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

			//
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secret);
			AlgorithmParameters params = cipher.getParameters();

			
			FileOutputStream ivOutFile = new FileOutputStream(path + "\\" +"iv.enc");
			byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			ivOutFile.write(iv);
			ivOutFile.close();

			byte[] input = new byte[64];
			int bytesRead;

			while ((bytesRead = inFile.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null)
					outFile.write(output);
			}

			byte[] output = cipher.doFinal();
			if (output != null)
				outFile.write(output);

			inFile.close();
			outFile.flush();
			outFile.close();

			System.out.println("File Encrypted.");
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}