//package symmetric;
//
//import java.security.Key;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//
//import util.CryptoTools;
//
//public class Q5
//{
//
//	public static void main(String[] args) throws Exception
//	{
//		
//		//byte[] key = "FACEBOOK".getBytes();
//		  byte[] key =  CryptoTools.hexToBytes("706F6C6974696373");
//		// ACTIVITY B KEY in hexadecimal
//		//byte[] key =  CryptoTools.hexToBytes("636F6E74696E7565");
//		//byte[] ct_file = CryptoTools.fileToBytes("data/Q5MSG.ct");
//		
//		
//		// -------------PASTE THE CIPHERTEXT HERE
//		
//	    	byte[] ct = CryptoTools.hexToBytes("B694B40CAFCEC10E334F7891490EAB78F5738F05FCA7457CFA2055E605CEF0B6295D7B14138DF3078C87722267F11429D860AB60DC246A75FFE855FA4F245090AA06A7BD5CA74A2F1112FF766FAA2D3DDDD6DAFB897E2FA4005CEA01F7360A9E106247D7EF6ED715A17F9403A7CADE3E228B93D59CC84585B76ADADA5F6924986D7177DA3CEE4F103B92A230EDFF78B61A3240E0937A66F6D0D5478EA930654EAD57D29D85404DF097CE0DB5E074B6242822514C47904F4F4A8D372D000E891A0387AC0D8AE4347621BDA74BD819213032BE91D9C916D994477A975AEB1863C4AAEC887D4C747A98C2858993503ECCBFBE5A0FFFCEE48E8C23BD48192E8ECE34988AA32E244D035D8C2224A3DEE3C8CF1AE69DF7D7F5FB0182EC03B76294D0DE46F94D55645BE65172A8EF051F62297907E935C40FAB0AC34F9FBBAA099E5F1F81BD8423A52645AC706E8634CA41A0A84DFA3C53355758570F10DB5223EB69A951E82A0C97CC33A247182184E445ED25E5CBF3AF94B6484C3FED27112DAA52CD25265CF56E169E02DC7D11B4CE6DBED448D706501A25C88AFE1E22BC518B578186C85428126CB4B87446F862B7BC71B080B5F74F659C1FC3669BEC0EF09571117078A42BC5E44080B0701267F7F2269ED7E15D2D65FD0101C13F4E99F39678524D223B25E3CE17819B60F9250E40299B09F08513DDF649F77B1E58863FACFB7E76719BEB51031C930BB527301D8D65A9F21B4BC60C0280C875704B959FE721C09F3D38D3B30C32175E4E53207A6CC4A2399E9D3C99BDB585255A0BACCE39B9BCC4B7BE0D106DD6A2645262E3587F4F0D41D66C5E0E53D830BBF6BC89D7244EBB9FBBA8606C99AF56324ED0D1352D527DDD30A7B2AC4A110F67F9DF41D0A924FF96FE3015792F327912A736A5E410152731FB03C300E3E0AAD13BFB789AE6B0704757D550A5B9932EAA2AB00B3492F3D9FE8794A8D79E0AAD43A5A98ABC247C66D5DFDEAC6FB6D4CD0D6737F480613B170E217A7EFFF16DC1BFEED6625F5095D2C5E249C41A3A94FD6DD217BACF9528DAF6FA60F7642E88469EF181FC7E3520DE41B478835771464BD1AC8EB5FF6551E0085A97152FF0DEB2E29CCA235BFFC48EEABEF80EF30B1E70DD86FAD6191BD3B7E9A108849D2F73CC7E67D325DE5906BAAEB16FE9FF5273DAEEACCB33178CFB805F0B0C322AC5B801B114F501E6D770A38C6740428F7E5BA03A7FBDFC7894DAA65D4BDD3F10CA01BA462B546157E34FCB625CE9AE3A9B35A355264FA4ED2D267E71BB2F960B4F8E4379D5F12322ED0BD97ACA3C801F81C9D9730C16959BCB9B720491C041887E4377CFDD3A4C159FBC7DC49648884312BA94C3F006ED9743807FEAACFEBA264046B3927C7FB3C283B2B20A02BC8A532651CDB29E25F714C5EFFA1C2E9601FD7BB9999A1F736A1D8BC714D47F18C9A32D960E85CF16470BEEC5F46FA29DFC90EB65A308C317BC0CADEC008C7CF44C0E4951983D7861185B305F55B5A404F22F52D31A692A33DDCABB6F8F0F287AEFF05A62712A58BFE4D2533F18DF4D7BE736782CA0276C0FE8A63AC6C5C298EF53F5E4D793");
//		//byte[] ct = CryptoTools.hexToBytes("8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284");
//		// -- ACTIVITY B CIPHERTEXT
////			byte[] ct = CryptoTools.hexToBytes("520A82827D1FF553E75854F2A83A12DBAFEEE3A47B4160124A1500808771FF04725FB58E50BA2AEC0408655C50B00157DEFF222E8B60AA0D772D60444D860AF77AB301F0B044527B7E182A1D70BF3D6316165C693E2F018540B3F47FF3E60F5B60D6B84646A689017B99980D7C3C701AA656F0F86881861D3E4A6DB98A38749F1755516950DA9F54F0F80974BADC33EAD1CD51BB128B5FFAAF691C42BC6469C64993586E1166EEBC361D29A018CD7D636A22A7748AB125091EA924E4DB600816BDFDE887384AAD28E68DA1909C8B9280001CA096D61091EA4D14D1107757D3F2163A6E50469843E8A74F6DCD2AA5CB47E8CDE040EA1B5B18DC308AECE88E82184FCF2D545A22B4B29DB9827469BE2CCD4ED8B8E8B8A7FD90826BFDBF2667DBCAB2FB56060ED279AADF70BE0CE2DAC8D3E73B1E5419AF44EB5B9DEB590C90123B6AADC93016CBB1CCCF297D4E0B1E89E2B3D0B5A7C2E63F1BFD27D5E59C58DA93576A87C45C4DD1F4BFCAB477497C0BA9ABEC9992596A462A8D961D2F9A62CC08F9BF70146BB19CD7257A896AA7788447E86F4926BC2ED810F885AAB9136C295D52B3C08F419341DE9A48C369EE6F97CC879F1C7FCFBE1EEB586DACB0C52D9518F48BB30BE66DC4E3904D373D9C7D42D7BDF109713AC036FF14B621D877698C735F7868C1EDDDFE98843CB3186584BBA3F901FDDF47E163D5C58BBC7515121FC49249AF177F0832DC54E07DD1D8EB6A633B165F64F0529CCA6D63AA10EBB52024B0BFBC69F223B24B083782760448572D390C7ED53F440E3867DA31E1061BE7626BF67394C2A4D2F70BF9A33EF8657D6253F55CB8446119245F4FCB501FC8A017C9BF7AFC5AA379B104940BF94EC9274F1BCB12065C1AE3CC88E7D971B5D78DAE05BB700D4626EB59AD73B3D10BD3C3D7722ADE5F38CCEFA282AB4A9021A0CA6E2687BDEEF7EE1C5AF821548EE87F497BF885AAB9136C295D3BEF9D62CEB97F88E0B250187DBE25BDB302B79D7E00A79EA5911D2FB8C1A6CC22EEFE98787CBDC4D4FFDB4B645F213A4881C3F4332DEAFA5C8CD30C88987EC248275F5053299CCD22DC6554472E96CBD07B031C01B5896A109319C9BB2F2E0D678093ACBB9454576A5FE041DA0BE4381AD16A4AF56DAA75B8708366115F6A48FB0405E02941673ADB212B05ECC9AF0090799310DBDF1A9979605AC1398A36AC29841298775E24A5184717F25206DAA1BF20F51E38306D1D334B6B572DF20A3505660FE17A49E023B8DDD2F362A0355510DE46A31DA6EDDCE2F1A51EE4A0D07D1002CCF23728FA5E0E64CC753DC623637349EB52B0577DBE2445D2BB5B223862AA26D37FF4C27E954324D82DE4AC5EB3ADDD5BD2DB96ABAEC3989A668CBB8DEF7399A105EBB239523A1EDF473188BB186D4F9FE8BDD12C993CC6F9CFADDF3702CBBA1DBF323F1F0938E47D3D4980F171E38E89933EFA69973DFCF6E67A95C5E8A810C2348F9254D302B9DF977E6E94C6C1A59B806E408F94ECEC2D5E2059855DA2A61C51CB84015FEBA752EC7B371A3F0B0488A7CF575EFDF82693B81EF3F217D5A7C8BD8BCC0C4E404EBDCEFAAA8D4F");
//
//		System.out.println();
//		//System.out.println("the length of the ct in bytes from fileToBytes is ->  " + ct_file.length);
//		System.out.println("the length of the ct in bytes from hexToBytes is ->  " + ct.length);
//		System.out.println("the length of the key in bytes is " + key.length);
//		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
//		
//		//producing the bitwise complement of key K
//		String key_original = CryptoTools.bytesToBin(key);
//		//System.out.println("this is the original key in bytes " + key);
//		System.out.println("this is the original key bitwise" + key_original);
//		String  key_negated = CryptoTools.bitwiseComplement(key_original);
//		System.out.println("this is the negated key bitwise " + key_negated);
//		String key_negated_hex = CryptoTools.binToHex(key_negated);
//		byte[] key_negated_byte  = CryptoTools.hexToBytes(key_negated_hex);
//		//System.out.println("this is the negated key in bytes" + key_negated_byte);
//			
//		
//		 		
//		Key	 secret_negated = new SecretKeySpec(key_negated_byte, "DES");
//		cipher.init(Cipher.DECRYPT_MODE, secret_negated);
//		byte[] ct0 = cipher.doFinal(ct);
//		//byte[] ct0_negated = cipher.doFinal(ct1);
//		//System.out.println("Ct0  = " + new String(ct0) + "<");
//		
//		Key	 secret = new SecretKeySpec(key, "DES");
//		cipher.init(Cipher.DECRYPT_MODE, secret);
//		byte[] pt = cipher.doFinal(ct0);
//		System.out.println("PT  = " + new String(pt) + "<");
//	
//	
//		
//		
//		
//		
//		
//
//	}
//
//}