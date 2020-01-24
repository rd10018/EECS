
package hash;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
    

public class Q1 {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   static BigInteger d ;
   private static BigInteger e;
   private static BigInteger n ;



 
   public static void main(String[] args) throws NoSuchAlgorithmException {

	   n= new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
	   e  = new BigInteger("74327");
	   d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");

       String m = "Meet me at 5 pm tomorrow";   
       
       
 //HASH the signature with SHA512 algorithm.
       
    //   byte[] signed = sign.toByteArray();
       MessageDigest md = MessageDigest.getInstance("SHA-512");
       byte[] hash = md.digest(m.getBytes());
       
       BigInteger pt = new BigInteger(hash);
       //sign the plaintext (means to encrypt the plaintext with the private key )
       BigInteger sign = pt.modPow(d, n);
       
      
       //System.out.println("this is the signature to be sent  " +  sign.toString() );

       //verifying with plaintext as well as the signature received 
       BigInteger pt1 = sign.modPow(e, n);
       System.out.println("This is the hash after decrypting -->" + pt1.toString());
       byte[] reverseHash = md.digest(m.getBytes());
       BigInteger pt2 =  new BigInteger(reverseHash);
       System.out.println("this is the hash of the plaintext -->" + pt2 );
       
       if (pt1.equals(pt2))
       {
    	   System.out.println("the hashes are equal");
       }
       else
       {
    	   System.out.println("The message has been tampered!!");
       }
       
      
   }
}
