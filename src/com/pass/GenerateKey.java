package com.pass;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;

public class GenerateKey {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	//Invoke the method. Starter method
	public static void main(String[] args) {
		GenerateKey generateKey = new GenerateKey();
		try{
			generateKey.generateKey();
			LocalDate date = LocalDate.now();
			generateKey.save("G:\\Keys\\Encryption\\"+date.toString(), "");
		}catch(Exception e){
			System.out.println("Exception occurred while trying to generate keys. "+e.getMessage());
		}
	}
	
	//Append numbers to prevent keys overwrite while writing
	private String getPath(String path, int appender){
		File file = new File(path);
		if(file.exists()){
			String newPath = path+"-"+(++appender);
			File newFile = new File(newPath);
			if(newFile.exists()){
				return getPath(path, appender);
			}else{
				newFile.mkdir();
				return newPath;
			}
		}else{
			file.mkdir();
			return path;
		}
	}
	
	//Get the correct path from getPath method and then save the keys
	public void save(String path, String nameAppender) throws Exception{
		String savePath = getPath(path, 0);	
		FileOutputStream privateKeyStream = new FileOutputStream(savePath+"//"+nameAppender+"privateKey");
		privateKeyStream.write(privateKey.getEncoded());
		privateKeyStream.close();
		
		FileOutputStream publicKeyStream = new FileOutputStream(savePath+"//"+nameAppender+"publicKey");
		publicKeyStream.write(publicKey.getEncoded());
		publicKeyStream.close();
		
	}

	//Keys generation using KeyPairGenerator using RSA algorithm
	public void generateKey() throws Exception {
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		
		KeyPair kp = kpg.generateKeyPair();
		
		publicKey = kp.getPublic();
		privateKey = kp.getPrivate();
		
		System.out.println("Public Key generated: Algo: "+publicKey.getAlgorithm() + " Format: "+ publicKey.getFormat());
		System.out.println("Private Key generated: Algo: "+privateKey.getAlgorithm() + " Format: "+ privateKey.getFormat());
		
	}

	/**
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	
	
}
