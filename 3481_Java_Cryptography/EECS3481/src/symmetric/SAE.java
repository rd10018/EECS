//package symmetric;
//
//import java.math.BigInteger;
//import java.security.Key;
//import java.security.spec.AlgorithmParameterSpec;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import util.CryptoTools;
//
//public class SAE
//{
//
//	public static void main(String[] args) throws Exception
//	{
//		
//		String plaintext = "";
//		//the complete ciphertext is  7AA38A029E773CBBC188A9FCEADAE99DA560B784C99AFEF2
//		
//		byte[] ct0 = CryptoTools.hexToBytes("7AA38A029E773CBB");
//		byte[] ct1 = CryptoTools.hexToBytes("C188A9FCEADAE99D");
//		byte[] ct2 = CryptoTools.hexToBytes("A560B784C99AFEF2");
//		byte[] ky =  CryptoTools.hexToBytes("4F75725269676874");
//		byte[] iv =  CryptoTools.hexToBytes("496E566563746F72");
//		
//		
//		
//		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
//	   
//		
//		
//		// FIRST STEP FOR CONVERING CT0 to PT0 using IV
//
//		Key secret = new SecretKeySpec(ky, "DES");
//		String x = CryptoTools.bytesToBin(iv);
//		System.out.println("the binary form of IV is  " + x);
//		String y = CryptoTools.bytesToBin(ct0);
//		System.out.println("the binary form of ct0 is " + y);
//		String temp = CryptoTools.xor(x,y);
//		System.out.println("the value of xor is      " + temp);
//		byte[] ct_bytes = new BigInteger(temp, 2).toByteArray();
//		System.out.println("the size of ct bytes is " + ct_bytes.length);
//		cipher.init(Cipher.DECRYPT_MODE, secret );
//		byte[] bk = cipher.doFinal(ct_bytes);
//		System.out.println("BK = " + new String(bk) + "<");				
//		plaintext += new String(bk);
//		
//// -------------------------------------------------------------------------nal bread	
//		
//		//computing for Pt1
//		
//		 secret = new SecretKeySpec(ky, "DES");
//		 x = CryptoTools.bytesToBin(ct0);
//		System.out.println("the binary form of ct0 is  " + x);
//		 y = CryptoTools.bytesToBin(ct1);
//		System.out.println("the binary form of ct1 is  " + y);
//		 temp = CryptoTools.xor(x,y);
//		System.out.println("the value of xor is        " + temp);
//		 ct_bytes = new BigInteger(temp, 2).toByteArray();
//		byte[] ct11 = new byte[8];
//		for(int i=1;i< ct_bytes.length ; i++)
//		{
//			System.out.print(ct_bytes[i] + " ");
//			ct11[i - 1] = ct_bytes[i];
//		}
//		
//		System.out.println();
//		System.out.println("the size of ct bytes is " + ct_bytes.length);
//		cipher.init(Cipher.DECRYPT_MODE, secret );
//		 bk = cipher.doFinal(ct11);
//		System.out.println("BK = " + new String(bk) + "<");				
//		plaintext += new String(bk);		
//
//		//COMPUTING PT2 using ct1 and ct2
//		secret = new SecretKeySpec(ky, "DES");
//		 x = CryptoTools.bytesToBin(ct1);
//		System.out.println("the binary form of ct1 is  " + x);
//		 y = CryptoTools.bytesToBin(ct2);
//		System.out.println("the binary form of ct2 is  " + y);
//		 temp = CryptoTools.xor(x,y);
//		System.out.println("the value of xor is        " + temp);
//		 ct_bytes = new BigInteger(temp, 2).toByteArray();
//		System.out.println("the size of ct bytes is " + ct_bytes.length);
//		cipher.init(Cipher.DECRYPT_MODE, secret );
//		 bk = cipher.doFinal(ct_bytes);
//		System.out.println("BK = " + new String(bk) + "<");				
//		plaintext += new String(bk);
//		
//		System.out.println("Plaintext =  " + plaintext);
//	}
//
//}
