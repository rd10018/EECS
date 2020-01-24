package foundation;

import util.CryptoTools;

public class C_Exhaustive {

	public static void main(String[] args) throws Exception {
		
		// for answers key  = 22
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		byte[] test = new byte[ct.length];
		int[] freq_computed = new int[26];
		int key = 0;
		int final_key = 0;
		double dot_pro = 0.0;
		double highest_dot_pro = 0.0;
		for(key = 0; key <= 25; key++ )
		{
			for (int i = 0; i < ct.length; i++)
			{
				int tmp = (ct[i] - 'A' - key) % 26;
				if (tmp < 0) tmp+= 26;
				test[i] = (byte) (tmp + 'A');
			}
			freq_computed = CryptoTools.getFrequencies(test);
			//computing the dot-product of the frequency vectors
			for(int i=0; i< freq_computed.length; i++)
			{
				dot_pro += freq_computed[i] * CryptoTools.ENGLISH[i];
			}
			if(dot_pro >= highest_dot_pro)
			{
				highest_dot_pro = dot_pro;
				final_key = key;
			}
			System.out.printf("for key =  %d, dot-product = %.3f   \n", key,  dot_pro);
			//System.out.printf("for key =  %d, TEST = %s\n ", key,  new String(test));
			//updating the value back to initial for other computation 
			dot_pro = 0.0;
		}
		
		
		byte[] pt = new byte[ct.length];
		//decrypting the ciphertext based on the key associated with the highest value dot-product obtained above
		for (int i = 0; i < pt.length; i++)
		{
			int tmp = (ct[i] - 'A' - final_key) % 26;
			if (tmp < 0) tmp+= 26;
			pt[i] = (byte) (tmp + 'A');
		}
		System.out.println("the correct key based on the highest dot_product is -> " + final_key);
		System.out.println("This is the correct_ciphertext ->");
		System.out.println(new String(pt));
	}

}
