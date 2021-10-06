package project.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESFileDecryption {
	public static void main(String[] args,String filename,String path,String key) throws Exception {

		decryptMethod(filename,path, key);
	}
	
	static void decryptMethod(String filename,String path, String key) {
			
		try {

			FileInputStream saltFis = new FileInputStream(path+"\\"+"salt.enc");
			byte[] salt = new byte[8];
			saltFis.read(salt);
			saltFis.close();

		
			FileInputStream ivFis = new FileInputStream(path+"\\"+"iv.enc");
			byte[] iv = new byte[16];
			ivFis.read(iv);
			ivFis.close();

			SecretKeyFactory factory = SecretKeyFactory
					.getInstance("PBKDF2WithHmacSHA1");
			
			KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, 65536,
					256);
			SecretKey tmp = factory.generateSecret(keySpec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
			FileInputStream fis = new FileInputStream(path +"\\"+filename);
			FileOutputStream fos = new FileOutputStream(path+"\\"+"decrypted.txt");
			byte[] in = new byte[64];
			int read;
			while ((read = fis.read(in)) != -1) {
				byte[] output = cipher.update(in, 0, read);
				if (output != null)
					fos.write(output);
			}

			byte[] output = cipher.doFinal();
			if (output != null)
				fos.write(output);
			fis.close();
			fos.flush();
			fos.close();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}