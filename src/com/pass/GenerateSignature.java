package com.pass;

import java.security.PrivateKey;
import java.security.Signature;
import java.time.LocalDate;

public class GenerateSignature {
	
	private PrivateKey privateKey;
	private Signature signature;
	private GenerateKey genKey;
		
	public static void main(String[] args) {
		GenerateSignature generateSign = new GenerateSignature();
		try{
			generateSign.generateSign();
			LocalDate date = LocalDate.now();
			generateSign.savePvtKey("G:\\Keys\\Encryption\\Signature"+date.toString());
		}catch(Exception e){
			System.out.println("Exception occurred while trying to generate keys. "+e.getMessage());
		}
	}
	
	private void savePvtKey(String path) throws Exception{
		genKey.save(path, "signature");
	}

	public void generateSign() throws Exception {
		
		genKey = new GenerateKey();
		genKey.generateKey();
		privateKey = genKey.getPrivateKey();
		
		signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		
		System.out.println("Digital Signature generated: Algo: "+ 	signature.getAlgorithm() + " Provider: " + signature.getProvider());
		
	}

}
