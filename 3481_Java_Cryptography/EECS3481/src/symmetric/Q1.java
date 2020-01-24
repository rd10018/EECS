package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q1
{

	
	public static String convertHexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
		  
		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){
			  
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
			  
		      temp.append(decimal);
		  }
		  System.out.println("Decimal : " + temp.toString());
		  
		  return sb.toString();
	  }
	
	
	
	
	public static void main(String[] args) throws Exception
	{
//		byte[] ct = CryptoTools.hexToBytes("ED8BD7C2DDD2D8EAB5843C39CFD8BB02");
//		
//		byte[] ky = CryptoTools.hexToBytes("30313233343536373839616263646566");
//		byte[] iv = CryptoTools.hexToBytes("31323334353637383930313233343536");
//		
//		Key secret = new SecretKeySpec(ky, "AES");
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
//
//		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
//		byte[] bk = cipher.doFinal(ct);
//		System.out.println("BK11 = " + new String(bk));
//		
//		
//		
//		
//		
//		
//		
//		byte[] ky11 = "manifest".getBytes();
//		
//		Key secret11 = new SecretKeySpec(ky11, "DES");
//		Cipher cipher1 = Cipher.getInstance("DES/ECB/NoPadding");
//		cipher1.init(Cipher.DECRYPT_MODE, secret11);
//		byte[] pt = cipher1.doFinal(bk);
//		
//		//System.out.println("CT = " + CryptoTools.bytesToHex(ct));
//		
//	//	byte[] ct
//		//cipher.init(Cipher.DECRYPT_MODE, secret);
//		//byte[] bk = cipher.doFinal(ct);
//		System.out.println("BK = " + new String(pt) + "<");
//		
//		
		System.out.println(convertHexToString("741A041D543504"));
		

	}

}
