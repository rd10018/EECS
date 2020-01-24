package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q2
{

	public static void main(String[] args) throws Exception
	{
		byte[] ct = "ED8BD7C2DDD2D8EAB5843C39CFD8BB02".getBytes();
		
		byte[] ky = CryptoTools.hexToBytes("30313233343536373839616263646566");
		byte[] iv = CryptoTools.hexToBytes("31323334353637383930313233343536");
		
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
	
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		System.out.println("size of ct is" + ct.length);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("BK = " + new String(bk) + "<");
		

	}

}
