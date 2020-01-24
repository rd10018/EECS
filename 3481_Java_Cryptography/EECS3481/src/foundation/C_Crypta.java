package foundation;

import util.CryptoTools;

public class C_Crypta {

	public static void main(String[] args) throws Exception {
		
		// for answers key  = 22
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");

// in case the ciphertext file is in lowercase, use the following method to convert it into upper-case first		
		//		for(int i=0; i< ct.length; i++)
//		{
//			int upper = ct[i] - 32;
//			ct[i] =  (byte)((char)upper)  ;
//		}
//		
		
		byte[] freq_character = new byte[26];
		double freq_ratio = 0.0;
		double highest_freq = 0.0;
		int highest_index = 0;
		char highest_freq_char = 'A'; //initial value 
		for(int i=0; i< ct.length; i++)
		{
			int index = ct[i] - 'A';
			freq_character[index]++;
		}
		System.out.println("The frequencies of each of the characters in the ciphertext are as follows: ");
		for(int i=0;i< freq_character.length;i++)
		{
			freq_ratio = (double) freq_character[i] / ct.length;
			if(freq_ratio >= highest_freq)
			{
				highest_freq = freq_ratio;
				highest_index = i;
			}
			System.out.printf("%c -> %.3f\n ", (char) (65 + i) , freq_ratio );
		}
		highest_freq_char = (char) (65 + highest_index);
		System.out.printf("The highest Frequency occuring ciphertext character is %c with freqeuncy %.3f\n", highest_freq_char, highest_freq);
		int key = highest_freq_char - 'E';
		if(key < 0)
		{
			key+= 26;
		}
		System.out.printf("Therefore, the value of key is %c - E = %d\n",highest_freq_char, key );
		
		// deducing the plaintext based on the key confirmed above
		
		byte[] pt = new byte[ct.length];
		for (int i = 0; i < ct.length; i++)
		{
			int tmp = (ct[i] - 'A' - key) % 26;
			if (tmp < 0) tmp+= 26;
			pt[i] = (byte) (tmp + 'A');
		}	
		System.out.printf("Plaintext based on key = %d is as follows:\n" , key);
		System.out.println(new String(pt));
		
		
	}

}
