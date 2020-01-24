package asymmetric;

import java.math.BigInteger;
import util.CryptoTools;
//import util.RSA;

public class Q4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger c1,c2,n1,n2,n1n2,one, invn1, invn2,a1,a2,x;
//		c1 = new BigInteger("365944767426");
//		c2 = new BigInteger("698856040412");
//		n1 = new BigInteger("1055827021987");
//		n2 = new BigInteger("973491987203");
		
		c1= new BigInteger("2");
		c2= new BigInteger("8");
		n1= new BigInteger("13");
		n2= new BigInteger("11");
		one = new BigInteger("1");
		n1n2 = n1.multiply(n2);
		
		//where
		//c1 = x mod n1;
		//c2 = x mod n2;
		//x formula is x = (c1n2(1/n2 mod n1) + c2n1(1/n1 mod n2) % n1n2) )
		//1/n2 mod n1
		invn2 = n2.modInverse(n1);
		//1/n1 mod n2
		invn1 = n1.modInverse(n2);
		
		a1 = c1.multiply(n2).multiply(invn2);
		a2 = c2.multiply(n1).multiply(invn1);
		x = a1.add(a2).mod(n1n2);
		
		System.out.println("x: " + x);
		
	}

}
