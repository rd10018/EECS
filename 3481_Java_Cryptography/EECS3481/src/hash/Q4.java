
package hash;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import util.CryptoTools;
    

public class Q4 {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   static BigInteger d ;
   private static BigInteger e;
   private static BigInteger n ;



 
   public static void main(String[] args) throws NoSuchAlgorithmException {

	 byte[] hash = CryptoTools.hexToBytes("5ae9b7f211e23aac3df5f2b8f3b8eada");
       
      
   }
}
