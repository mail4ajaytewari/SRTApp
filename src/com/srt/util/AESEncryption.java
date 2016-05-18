package com.srt.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * <p>Utility class to encrypt and decrypt String</p>
 * @author Ajay
 *
 */
public class AESEncryption {
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[]{'P','A','5','5','w','0','r','D','!','K','e','Y','8','3','1','4'};
	
	/**
	 * </p>This method decrypt the input string</p>
	 * @param inputString
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String inputString) throws Exception {
		try {
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = cipher.doFinal(inputString.getBytes());
			String encryptedValue = new Base64().encodeToString(encVal);
			return encryptedValue;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <p>This method decrypt the encrypted string</p>
	 * @param encryptedValue
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedValue) throws Exception{
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.decodeBase64(encryptedValue);
		byte[] decValue = cipher.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		
		return decryptedValue;
	}
	
	/**
	 * <p>This method generates the encryption/decryption key based on AES algorithm</p>
	 * @return
	 * @throws Exception
	 */
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}
}
