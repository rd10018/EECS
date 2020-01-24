package asymmetric;


import java.math.BigInteger;
import java.security.SecureRandom;
    

public class Q1 {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger d ;
   private BigInteger e;
   private BigInteger n ;

   // generate an N-bit (roughly) public and private key
   Q1() {
	   //Question 1 
	   n= new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
	   e  = new BigInteger("74327");
	   d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
	   	   
   }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(e, n);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(d, n);
   }


   public static void main(String[] args) {
 
      Q1 key = new Q1();
     // create random message, encrypt and decrypt
   
      //ACTIVITY -C  QUESTION 1
      BigInteger ct = new BigInteger("87014856975716299121085087309577038316883175412853820115551293556230488405826385706604303724175236985573832006395540199066061101502996745421485579743246846982636317440505885092956723199407403632041108913018671613508572002898008615700858579079601105011909417884801902333329415712320494308682279897714456370814");
      BigInteger decrypt = key.decrypt(ct);

     // System.out.println("message   = " + );
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
