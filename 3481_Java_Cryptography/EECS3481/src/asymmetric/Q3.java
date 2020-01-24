package asymmetric;
import java.math.BigInteger;
import java.security.SecureRandom;
    

public class Q3 {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   BigInteger d ;
   private BigInteger e;
   private BigInteger n ;
   private BigInteger p;
   private BigInteger q;
   private BigInteger phi;

   Q3() {
	   
	   //QUESTION 3
	   e  = new BigInteger("65537");
	   q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
	   phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
	   p = phi.divide(q.subtract(one)).add(one);
	 
	   n = p.multiply(q);
	   System.out.println("the value of phis is " + phi);
	   d = e.modInverse(phi);
	   
   }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(e, n);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(d, n);
   }

  public static void main(String[] args) {

      Q3 key = new Q3();
        //ACTIVITY - C Question 3
      BigInteger ct = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");

      BigInteger decrypt = key.decrypt(ct);


      System.out.println("encrypted = " + ct);
      byte[] pt = decrypt.toByteArray();
      System.out.println("the length of the byte array is " + pt.length);
      for(int i=0;i< pt.length; i++)
      {
    	  System.out.print((char)pt[i]);
      }
      System.out.println();

   }
}
