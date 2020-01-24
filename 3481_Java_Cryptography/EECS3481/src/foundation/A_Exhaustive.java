package foundation;

import java.math.BigInteger;

import util.CryptoTools;

public class A_Exhaustive {

	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/MSG3.ct");
		int counter = 0;
		int[] alpha_container = new int[26]; // an array for computing the alpha values that are co-prime with 26
		int[] beta = new int[26];
		
		
		for(int i=0; i< beta.length; i++)
		{
			beta[i] = i;
		}
		
		
		
		// Note: alpha[0] will never be used since alpha has range 1-25 
		for(int i = 0; i<=25; i++)
		{
			int gcd = BigInteger.valueOf(i).gcd(BigInteger.valueOf(26)).intValue();
			if(gcd == 1)
			{
				alpha_container[i] ++;
			}
		}
		
		// to compute the length of the alpha[]
		for(int i=0;i< alpha_container.length; i++)
		{
			if(alpha_container[i] == 1)
			{
				counter++;
			}
		}
		
		int[] alpha = new int[counter];
		for(int i=0, j=0;i< alpha_container.length && j< counter; i++)
		{
			if(alpha_container[i] == 1)
			{
				alpha[j] = i;
				j++;
			}
		}
		
		System.out.println("Printing Alpha ->");
		for(int i=0; i< alpha.length; i++)
		{
			System.out.println(alpha[i]);
		}
		
		System.out.println("Printing Beta ->");
		for(int i=0; i< beta.length; i++)
		{
			System.out.println(beta[i]);
		}
		System.out.println("the length of the ct is -> " + ct.length);
		
	
		
		
		byte[] test = new byte[ct.length];
		for(int i=0; i < alpha.length; i++)
		{
			for(int j=0; j< beta.length; j++ )
			{
				int a = alpha[i];
				//System.out.printf("the value of a is: %d\n ", a);
				int b = beta[j];
				//System.out.printf("the value of b is: %d\n ", b);
				System.out.printf("Testing for values a = %d and b = %d\n", a,b);
				for(int k = 0; k< ct.length; k++)
				{
					
					BigInteger a_big = BigInteger.valueOf(a);
					BigInteger mod_26 = BigInteger.valueOf(26);
					char ct_char = (char) ct[k];
					//System.out.print("ct = "+ ct_char + " ");
					BigInteger inverse = a_big.modInverse(mod_26);
					int pt_int =  (ct_char - 'A'  - b + 26) *  inverse.intValue();
					//System.out.println("pt = "+ (char) (pt_int% 26 + 'A'));
					test[k] = (byte) ((char) (pt_int% 26 + 'A'));
					
				 
				}
				System.out.println("ct =" + new String(ct));
				System.out.println("pt = " + new String(test) );
				//System.out.println(new String(test));
				
			}
		}
		
		
		
		
		
		
		
//		
//		System.out.println("The following values are co-prime with 26 -> ");
//		for(int i=0;i< alpha_container.length; i++)
//		{
//			
//			if(alpha_container[i] == 1)
//			{
//				System.out.println(i);
//			}
//		}
//		
		
		
		
		
		
	}

}
