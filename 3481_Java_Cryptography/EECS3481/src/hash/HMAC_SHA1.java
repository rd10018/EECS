package hash;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class HMAC_SHA1 {
	private static final String HMAC_SHA1 = "HmacSHA1";

	public static String calculateHMAC(String data, String key)
	    throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
	    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA1);
	    Mac mac = Mac.getInstance(HMAC_SHA1);
	    mac.init(secretKeySpec);
	    return CryptoTools.bytesToHex(mac.doFinal(data.getBytes()));
	}

	public static void main(String[] args) throws Exception {
	    String hmac = calculateHMAC("The quick brown fox jumps over the lazy dog", "key");
	    System.out.println(hmac);
	}
}
