//package symmetric;
//
//import java.security.Key;
//import java.security.spec.AlgorithmParameterSpec;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import util.CryptoTools;
//
//public class Q4
//{
//
//	public static void main(String[] args) throws Exception
//	{
//
//		byte[] ct0 = CryptoTools.hexToBytes("437DBAB5607137A5");
//		byte[] ct1 = CryptoTools.hexToBytes("CFC1031114634087");
//		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
//		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
//		
//		
//		Key secret = new SecretKeySpec(ky, "DES");
//		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
//	   
//		
//// -----------------------------------------------  Traditio		
//		// FIRST STEP FOR CONVERING CT0 to PT0
////		String iv_original = CryptoTools.bytesToBin(iv);
////		System.out.println("the value of iv_original is "  + iv_original);
////		String iv_negated = CryptoTools.bitwiseComplement(iv_original);
////		System.out.println("the value of iv_negated is " +iv_negated );
////		String iv_negated_hex =  CryptoTools.binToHex(iv_negated);
////		System.out.println( "This is the negated IV_hexadecimal " + iv_negated_hex);
////		
////		byte[] iv_negated1 = CryptoTools.hexToBytes(iv_negated_hex);
////		//AlgorithmParameterSpec aps = new IvParameterSpec(CryptoTools.hexToBytes(iv_negated_hex));
////		AlgorithmParameterSpec aps = new IvParameterSpec(iv_negated1);
////		cipher.init(Cipher.DECRYPT_MODE, secret,aps );
////		byte[] bk = cipher.doFinal(ct0);
////		System.out.println("BK = " + new String(bk) + "<");				
//		
//		
//// -------------------------------------------------------------------------nal bread	
//		
//		//computing for Pt1
//		
//		String iv_original = CryptoTools.bytesToBin(ct0);
//		System.out.println("the value of iv_original is "  + iv_original);
//		String iv_negated =  CryptoTools.bitwiseComplement(iv_original);
//		System.out.println("the value of iv_negated is " + iv_negated);
//		String iv_negated_hex = CryptoTools.binToHex(iv_negated);
//		System.out.println( "This is the negated IV_hexadecimal" + iv_negated_hex);
//
//		byte[] iv_negated1 = CryptoTools.hexToBytes(iv_negated_hex);
//		//AlgorithmParameterSpec aps = new IvParameterSpec(CryptoTools.hexToBytes(iv_negated_hex));
//		AlgorithmParameterSpec aps = new IvParameterSpec(iv_negated1);
//		cipher.init(Cipher.DECRYPT_MODE, secret,aps );
//		System.out.println("the size of ct1 is " + ct1.length);
//		byte[] bk = cipher.doFinal(ct1);
//		System.out.println("BK = " + new String(bk) + "<");
//		
//
//	}
//
//}
