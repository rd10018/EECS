package hash;

public class Q5 {

	public static void main(String[] args)
	{
	double x= 23;
	double days_year = 365;
	double e =  2.71828;
	double exponent = (-1 * Math.pow(x, 2))/( 2 * days_year);
	double prob_different = Math.pow(e, exponent);
	double prob_same = 1 - prob_different;
	//System.out.println("exponent is" + exponent);
	System.out.println("probability that their birthday is on a different date is  " + prob_different);
	System.out.println("Therefore, for " + (int)x + " number of people, proabability of same birthday is " + prob_same);
	
	
	double x_factor = 1.777 * Math.sqrt(days_year);
	System.out.println("the value of x-factor is " + (int)(Math.ceil(x_factor)));
	
	}
}
