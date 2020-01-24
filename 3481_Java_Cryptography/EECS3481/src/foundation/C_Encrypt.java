package foundation;

import util.CryptoTools;

public class C_Encrypt {

	public static void main(String[] args) throws Exception
	{
		int key = 19;
		byte[] raw = CryptoTools.fileToBytes("data/MSG1.pt");
		byte[] pt = CryptoTools.clean(raw);
		CryptoTools.bytesToFile(pt, "data/MSG1.clean");
		byte[] ct = new byte[pt.length];
		String MD5_actual = "2C422B741EF90FD4424EBC83692398B0";
		
		
		//encrypting using Caesar-cipher with key k = 19
		for (int i = 0; i < pt.length; i++)
		{
			ct[i] = (byte) ((pt[i] - 'A' + key) % 26 + 'A');
		}
		
		CryptoTools.bytesToFile(ct, "data/MSG1.ct");
		//computing MD5 of the ciphertext
		String MD5_computed = CryptoTools.getMD5(ct);
		
		
		System.out.println("This is the actual MD5   " + MD5_actual);
		System.out.println("This is the computed MD5 " + MD5_computed);
		if(MD5_computed.equals(MD5_actual))
		{
			System.out.println("Yes, both of them are equal.");
		}
		else
		{
			System.out.println("Oops, try again!!!");
		}
		
//		double ioc_pt = CryptoTools.getIC(pt);
//		double ioc_ct = CryptoTools.getIC(ct);
//		//System.out.println(ioc_pt);
//		System.out.printf("This is the index of coincidence of the clean plaintext -> %.4f\n", ioc_pt);
//		System.out.printf("This is the index of coincidence of the ciphertext -> %.4f\n", ioc_ct);
		
	}

	   
	
}
