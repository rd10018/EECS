package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import util.CryptoTools;


public class Q4_1 {

	private static final byte[] PASSWORD_MD5_HASH = CryptoTools.hexToBytes("5ae9b7f211e23aac3df5f2b8f3b8eada");
	

	public static void main(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest digester = MessageDigest.getInstance("MD5");
		
		// Using https://crackstation.net/, can crack D1Q5.PASSWORD_MD5_HASH to "crypto".
		System.out.println(Arrays.equals(digester.digest("crypto".getBytes()), Q4_1.PASSWORD_MD5_HASH) + "\n");
	}
}