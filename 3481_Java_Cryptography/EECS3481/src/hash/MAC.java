package hash;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import util.CryptoTools;

public class MAC
{

	public static void main(String[] args) throws Exception
	{
		String message = "No one can make you feel inferior without your consent.";
		MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] hash = md.digest(message.getBytes());
		
		//byte[] ky = "troubleentirethr".getBytes();
		byte[] ky = "themselvesforwar".getBytes();
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		System.out.println("size of hash is" + hash.length);
		byte[] ct = cipher.doFinal(hash);
		System.out.println("MAC = " + CryptoTools.bytesToHex(ct));
		

	}

}