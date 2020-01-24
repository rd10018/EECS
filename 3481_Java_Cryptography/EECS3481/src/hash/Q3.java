
package hash;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import util.CryptoTools;
    

public class Q3 {
	

   public static byte[] xor(byte[]k_bytes , byte[]opadA)
   {
	byte[] result ;   
	String y = CryptoTools.bytesToBin(k_bytes);
	String z = CryptoTools.bytesToBin(opadA);
	String xor = "";
	for (int i=0;i<y.length();i++)
	{
		xor += Integer.parseInt(y.charAt(i)+"") ^ Integer.parseInt(z.charAt(i)+"");
	}
	 
	  String s = CryptoTools.binToHex(xor);
	  result = CryptoTools.hexToBytes(s);
	  System.out.println(result.length + "  is the length of result");
	  return result;
   }
 
   public static void main(String[] args) throws NoSuchAlgorithmException {
	  
	 
	   String m = "Mainly cloudy with 40 percent chance of showers";
	   //String m = "The quick brown fox jumps over the lazy dog";
	  
	  String key = "This is an ultra-secret key";
	   //String key = "key";
	  //since we are using SHA-1, block size is 64 bytes so append to the right of the key with required zeros

	  String oA ="5c";
	  String iA = "36";
	  String oB = "36";
	  String iB = "5c";
	  for(int i=0;i<63;i++)
	  {
		  oA += "5c";
		  iA += "36";
		  oB += "36";
		  iB += "5c";
	  }
	  byte[] opadA = CryptoTools.hexToBytes(oA);
	  byte[] ipadA = CryptoTools.hexToBytes(iA);
	  byte[] opadB = CryptoTools.hexToBytes(oB);
	  byte[] ipadB = CryptoTools.hexToBytes(iB);
	  
	  byte[] k_bytes = new byte[64];
	  byte[] key_original = key.getBytes();
	  for(int i=0;i<key_original.length;i++)
	  {
		  k_bytes[i] = key_original[i];
	  }
	  int temp = k_bytes.length;
	  if(temp <64)
	  {
		  for (int i= temp; i<64; i++)
		  {
			  k_bytes[i] = 0;
		  }
		  
	  }
	
	 byte[] o_key_padA = xor(k_bytes, opadA);
	 byte[] i_key_padA = xor(k_bytes, ipadA);
	 
	 MessageDigest md = MessageDigest.getInstance("SHA-1");
	 String i = CryptoTools.bytesToHex(i_key_padA) + CryptoTools.bytesToHex(m.getBytes()); 
	 byte[] innerHash = md.digest(CryptoTools.hexToBytes(i));
	 String o = CryptoTools.bytesToHex(o_key_padA) + CryptoTools.bytesToHex(innerHash); 
	 byte[] outerHash = md.digest(CryptoTools.hexToBytes(o));

	 System.out.println(CryptoTools.bytesToHex(outerHash));
	 
	 System.out.println("doing the reverse HMAC");
	 
	 
	 byte[] o_key_padB = xor(k_bytes, opadB);
	 byte[] i_key_padB = xor(k_bytes, ipadB);

	 i = CryptoTools.bytesToHex(i_key_padB) + CryptoTools.bytesToHex(m.getBytes()); 
	 innerHash = md.digest(CryptoTools.hexToBytes(i));
	 o = CryptoTools.bytesToHex(o_key_padB) + CryptoTools.bytesToHex(innerHash); 
	 outerHash = md.digest(CryptoTools.hexToBytes(o));
	 System.out.println(CryptoTools.bytesToHex(outerHash));
	 
	 
	 
	 
	
	  
 
       
	   
      
   }
}
