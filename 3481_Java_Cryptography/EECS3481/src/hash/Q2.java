
package hash;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
    

public class Q2 {
	private final static BigInteger one = new BigInteger("1");
   private static BigInteger nA ;
   private static BigInteger eA;
   private static BigInteger pB ;
   private static BigInteger qB ;
   private static BigInteger eB ;
   private static BigInteger mPrime;
   private static BigInteger sPrime;
   private static BigInteger phi;
   private static BigInteger dB;
   private static BigInteger nB;



 
   public static void main(String[] args) throws NoSuchAlgorithmException {
	   
	   nA = new BigInteger("171024704183616109700818066925197841516671277");
	   eA = new BigInteger("1571");

	   pB = new BigInteger("98763457697834568934613");
	   qB = new BigInteger("8495789457893457345793");
	   eB = new BigInteger("87697");
	   mPrime = new BigInteger("418726553997094258577980055061305150940547956");
	   sPrime = new BigInteger("749142649641548101520133634736865752883277237");
	   
 
       
	   //Step 1: compute Bob's Private key and Bob's modulus
	   
	   phi = (pB.subtract(one)).multiply(qB.subtract(one));
	   dB = eB.modInverse(phi);
	   //System.out.println("the value of Bob's private key is " + dB.toString());
	   nB = pB.multiply(qB);
	   //System.out.println("the value of Bob's modulus is " + nB.toString()); 
	   
	   //Step 2: decrypt mPrime using Bob's private key
	   BigInteger mReceived = mPrime.modPow(dB, nB);
	   byte[] pt = mReceived.toByteArray();
	   System.out.println("this is the plaintext sent by the Alice in BigInteger   " + mReceived );

	   
	   //step 3 : decrypt sPrime to get alice's message encrypted with alice's private key
	   BigInteger sReceived = sPrime.modPow(dB,nB);
	   
	   //Step 4 : decrypt sReceived with Alice's public key to get the plaintext alice originally sent
	   BigInteger plaintext = sReceived.modPow(eA, nA);
	   System.out.println("this is the plaintext Orig by the Alice in BigInteger   " + plaintext );
	   
	   
	   //Step 5 : check if both are same --> signifies that received plaintext belongs to alice
	   if (mReceived.equals(plaintext))
	   {
		   System.out.println("Integrity confirmed, its Alice!");
	   }
	   else
	   {
		   System.out.println("Integrity failed, its not Alice!");
	   }
 
      
   }
}
