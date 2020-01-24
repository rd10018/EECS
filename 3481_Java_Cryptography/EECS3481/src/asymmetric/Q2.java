package asymmetric;
import java.math.BigInteger;
import java.security.SecureRandom;

import util.CryptoTools;
    

public class Q2 {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   BigInteger d ;
   private BigInteger e;
   private BigInteger n ;
   private BigInteger p;
   private BigInteger q;
   private BigInteger phi;
   // generate an N-bit (roughly) public and private key
   Q2() {
	   //QUESTION 2
	   n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
	   e  = new BigInteger("74327");
	   p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127888522373061586445417642355843316524942445924294144921649080401518286829171");
	   q = n.divide(p);
	   //System.out.println("this is q  " + q);
	   //System.out.println("this is the n " + p.multiply(q));
	   phi = (p.subtract(one)).multiply(q.subtract(one));
	   //System.out.println("the value of phis is " + phi);
	   d = e.modInverse(phi);
	   //System.out.println("the va d is " + d);
	   
	   
   }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(e, n);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(d, n);
   }

 
   public static void main(String[] args) {

      Q2 key = new Q2();
      //ACTIVITY -C  QUESTION 2
      BigInteger ct = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629529910525828464329792615002602782366786531253275463358840412867833406256467153345139501952173409955322129689670345445632775574301781800376545448990332608558103266831217073027652061091790342124418143422318965525239492387183438956");      

      BigInteger decrypt = key.decrypt(ct);

      System.out.println("encrypted = " + ct);
      byte[] pt = decrypt.toByteArray();
      System.out.println("the length of the byte array is " + pt.length);
      for(int i=0;i< pt.length; i++)
      {
    	  System.out.print((char)(pt[i]));
      }
      System.out.println();
      //System.out.println(hex);
      
   }
}
