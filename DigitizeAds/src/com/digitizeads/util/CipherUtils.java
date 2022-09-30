package com.digitizeads.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CipherUtils {

	static Log log = LogFactory.getLog(CipherUtils.class);

	private static byte[] key = { 0x01, 0x08, 0x00, 0x00, 0x01, 0x02, 0x00,
			0x00, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	private static byte[] phoneKey = { 0x00, 0x02, 0x00, 0x05, 0x01, 0x09,
			0x08, 0x06, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	private static byte[] personalEmailKey = { 0x00, 0x02, 0x00, 0x01, 0x02,
			0x00, 0x01, 0x03, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	private static byte[] companyNameKey = { 0x02, 0x03, 0x00, 0x03, 0x01,
			0x09, 0x08, 0x01, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	private static byte[] officialEmailKey = { 0x05, 0x08, 0x08, 0x07, 0x08,
			0x04, 0x05, 0x09, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	private static byte[] idKey = { 0x12, 0x12, 0x08, 0x07, 0x08, 0x04, 0x05,
			0x09, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	private static byte[] counsellingDataKey = { 0x07, 0x20, 0x12, 0x20, 0x08,
			0x04, 0x05, 0x09, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";
	
	private static byte[] cookieUNameKey = { 0x02, 0x03, 0x00, 0x03, 0x01,
		0x09, 0x08, 0x01, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";
	
	private static byte[] cookiePSecKey = { 0x02, 0x03, 0x00, 0x03, 0x01,
		0x09, 0x08, 0x01, 0x01, 0x02, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00 };// "thisIsASecretKey";

	public static String encrypt(String strToEncrypt) {
		try {
			if (strToEncrypt != null && !"".equals(strToEncrypt)
					&& !"null".equalsIgnoreCase(strToEncrypt)) {
				log.debug("CiperUtil :  Encrypting string");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				final String encryptedString = Base64.encodeBase64String(cipher
						.doFinal(strToEncrypt.getBytes()));
				return encryptedString;
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("Error while encrypting", e);
		}
		return null;

	}

	public static String decrypt(String strToDecrypt) {
		try {
			if (strToDecrypt != null && !"".equals(strToDecrypt)
					&& !"null".equalsIgnoreCase(strToDecrypt)) {
				log.debug("CiperUtil :  Decrupting string");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
				final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				final String decryptedString = new String(cipher.doFinal(Base64
						.decodeBase64(strToDecrypt)));
				return decryptedString;
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("Error while decrypting", e);

		}
		return null;
	}

	public static String encryptId(String strToEncrypt) {
		try {
			if (strToEncrypt != null && !"".equals(strToEncrypt)
					&& !"null".equalsIgnoreCase(strToEncrypt)) {
				log.debug("CiperUtil :  Encrypting string");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				final SecretKeySpec secretKey = new SecretKeySpec(idKey, "AES");

				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				// final String encryptedString =
				// Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
				final String encryptedString = Base64
						.encodeBase64String(strToEncrypt.getBytes());
				return encryptedString;
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("Error while encrypting", e);
		}
		return null;

	}

	public static String decryptId(String strToDecrypt) {
		try {
			if (strToDecrypt != null && !"".equals(strToDecrypt)
					&& !"null".equalsIgnoreCase(strToDecrypt)
					&& strToDecrypt.length() > 3) {
				log.debug("CiperUtil :  Decrupting string");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
				final SecretKeySpec secretKey = new SecretKeySpec(idKey, "AES");

				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				// final String decryptedString = new
				// String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
				final String decryptedString = new String(
						Base64.decodeBase64(strToDecrypt));
				return decryptedString;
			} else {
				return strToDecrypt == null || strToDecrypt.equals("null") ? "0"
						: strToDecrypt;
			}

		} catch (Exception e) {
			log.error("Error while decrypting", e);
			return strToDecrypt == null || strToDecrypt.equals("null") ? "0"
					: strToDecrypt;
		}
	}

	public static String encryptPhone(String strToEncrypt) {
		try {
			if (strToEncrypt == null || "null".equalsIgnoreCase(strToEncrypt)
					|| "".equals(strToEncrypt))
				return "";
			log.debug("CiperUtil :  Encrypting string");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(phoneKey, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher
					.doFinal(strToEncrypt.toLowerCase().getBytes()));
			return encryptedString;
		} catch (Exception e) {
			log.error("Error while encrypting", e);
		}
		return null;

	}

	public static String decryptPhone(String strToDecrypt) {
		try {
			if (strToDecrypt == null || "null".equalsIgnoreCase(strToDecrypt)
					|| "".equals(strToDecrypt))
				return "";
			log.debug("CiperUtil :  Decrupting string");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(phoneKey, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64
					.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			log.error("Error while decrypting", e);

		}
		return null;
	}

	

	public static String encryptEmail(String strToEncrypt) {
		try {
			if (strToEncrypt == null || "null".equalsIgnoreCase(strToEncrypt)
					|| "".equals(strToEncrypt))
				return "";
			log.debug("CiperUtil :  Encrypting string");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(officialEmailKey,
					"AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher
					.doFinal(strToEncrypt.toLowerCase().getBytes()));
			return encryptedString;
		} catch (Exception e) {
			log.error("Error while encrypting", e);
		}
		return null;

	}

	public static String decryptEmail(String strToDecrypt) {
		try {
			if (strToDecrypt == null || "null".equalsIgnoreCase(strToDecrypt)
					|| "".equals(strToDecrypt))
				return "";
			/*System.out.println("CiperUtil :  Decrypting decryptOfficalEmail : "
					+ strToDecrypt);*/
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(officialEmailKey,
					"AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64
					.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			log.error("Error while decrypting", e);

		}
		return null;
	}

	public static String encryptCookieUName(String strToEncrypt) {
		try {
			if (strToEncrypt == null || "null".equalsIgnoreCase(strToEncrypt)
					|| "".equals(strToEncrypt))
				return "";
			log.debug("CiperUtil :  Encrypting string");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(cookieUNameKey,
					"AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher
					.doFinal(strToEncrypt.getBytes()));
			return encryptedString;
		} catch (Exception e) {
			log.error("Error while encrypting", e);
		}
		return null;

	}

	public static String decryptCookieUName(String strToDecrypt) {
		try {
			if (strToDecrypt == null || "null".equalsIgnoreCase(strToDecrypt)
					|| "".equals(strToDecrypt))
				return "";
			// System.out.println("CiperUtil :  Decrypting decryptCompanyName : "+strToDecrypt);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(cookieUNameKey,
					"AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64
					.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			log.error("Error while decrypting", e);

		}
		return null;
	}
	
	
	public static String encryptCookiePSec(String strToEncrypt) {
		try {
			if (strToEncrypt == null || "null".equalsIgnoreCase(strToEncrypt)
					|| "".equals(strToEncrypt))
				return "";
			log.debug("CiperUtil :  Encrypting string");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(cookiePSecKey,
					"AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher
					.doFinal(strToEncrypt.getBytes()));
			return encryptedString;
		} catch (Exception e) {
			log.error("Error while encrypting", e);
		}
		return null;

	}

	public static String decryptCookiePSec(String strToDecrypt) {
		try {
			if (strToDecrypt == null || "null".equalsIgnoreCase(strToDecrypt)
					|| "".equals(strToDecrypt))
				return "";
			// System.out.println("CiperUtil :  Decrypting decryptCompanyName : "+strToDecrypt);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(cookiePSecKey,
					"AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			final String decryptedString = new String(cipher.doFinal(Base64
					.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			log.error("Error while decrypting", e);

		}
		return null;
	}
	
	/*public static void main(String[] arg){
		String encId = encryptId("40");
		System.out.println(encId);
	}*/
}
