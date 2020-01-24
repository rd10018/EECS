package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q3
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
		byte[] ct3 =  CryptoTools.hexToBytes("B2ACD6ADF010DDC4");  //ct3
		byte[] ky  =  "CSE@YORK".getBytes(); // key = 64 bits
		byte[] iv = CryptoTools.hexToBytes("4E51297B424F90D8"); //ct2
		
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		//cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		
		//cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
		byte[] pt2 = cipher.doFinal(ct3);
		
		System.out.println("PT2 = " + convertHexToString(CryptoTools.bytesToHex(pt2)));
		
		//ct1 =  ????????????????
		//ct2 = 4E51297B424F90D8 pt2 = SEL_11A
		//ct3 = B2ACD6ADF010DDC4
		
		//testing decrypting ct3 to ct2 using cbc
		//byte[] ct2 = CryptoTools.hexToBytes("????????????????4E51297B424F90D8B2ACD6ADF010DDC4"); 
		//byte[] ct3 = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
		
		//byte[] ct1_computed = cipher.doFinal(ct2);
		
		//System.out.println("ct1_computed = " + new String(ct1_computed) + "<");


	}

}
