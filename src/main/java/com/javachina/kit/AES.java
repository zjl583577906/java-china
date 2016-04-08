package com.javachina.kit;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import blade.kit.Base64;

/**
 * Aes encryption
 */
public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(String myKey) {
		
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			// System.out.println(key.length);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			// System.out.println(key.length);
			// System.out.println(new String(key,"UTF-8"));
			secretKey = new SecretKeySpec(key, "AES");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.encodeBytes(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.decode(strToDecrypt)));

		} catch (Exception e) {

			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static void main(String args[]) {
		final String strToEncrypt = "My text to encrypt";
		final String strPssword = "java";
		AES.setKey(strPssword);
		
		String jiami = AES.encrypt(strToEncrypt.trim());

		System.out.println("Encrypted: " + jiami);

		String jiemi = AES.decrypt(jiami);
		
		System.out.println("Decrypted : " + jiemi);

	}

}