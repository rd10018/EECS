package asymmetric;

import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;
 
/** Class MillerRabin **/
public class Q5
{
	
	
	public boolean isPrime(BigInteger n, BigInteger base)
    {
		BigInteger mone = new BigInteger("-1");
    	BigInteger one = new BigInteger("1");
    	BigInteger two = new BigInteger("2");
    	boolean result = true;
    	BigInteger exponent  =  n.subtract(one);
    	System.out.println("the very first eponent is  " +  exponent);
    	//BigInteger base = new BigInteger("2");
    	BigInteger temp =  base.modPow(exponent, n);
    	System.out.println("The very first temp is  "  +  temp);
    	if (!(temp.equals(one)))
    	{	
    		System.out.println("1");
    		result = false;
    	}
    	while(result)
    	{
    		exponent = exponent.divide(two);
    		System.out.println("this is the calculated exponent  " +  exponent);
    		temp = base.modPow(exponent, n);
    		System.out.println("this is the calculated temp " + temp); // 4
    		if(!temp.equals(one) && !temp.equals(n.subtract(one)))
    		{
    			System.out.println("2");
    			System.out.println("temp is " + temp + " so stop");
    			result = false;
    		}
    		
    		
    		if ( temp.equals(n.subtract(one)))
    		{
    			System.out.println("Test fails for this base as temp is one less than n");
    			result = false;
    		}
    		if(exponent.mod(two).equals(one) )
    		{
    			System.out.println("exponent is  odd   " + exponent);
    			result = false;
    		}
    		if(result)    			
    		{
    			System.out.println("2.5");
    			//System.out.println("temp is  " + temp );
    		}

    	}
    	return result;
    	
    }   /** Main function **/
    public static void main (String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        //System.out.println("Enter a number for the Miller-Rabin test\n");
        BigInteger n = new BigInteger("1033931178476059651954862004553");
       // BigInteger n = new BigInteger("1729");
        Q5 m1 = new Q5();
        	BigInteger base = new BigInteger("2");
        	boolean result = m1.isPrime(n,base );
            System.out.println("THIS IS THE RESULT  --->>  "    + result +  " for  base =  " + base);
            
    }   
 
    
    
    }
