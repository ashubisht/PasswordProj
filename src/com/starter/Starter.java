package com.starter;

import java.nio.file.Paths;

import com.hash.SHA256Runner;
import com.pass.GenerateKey;
import com.pass.GenerateSignature;

public class Starter {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GenerateKey keyGen = new GenerateKey();
		keyGen.generateKey();
		String pathAppend = Paths.get("").toAbsolutePath().toString() + "\\";
		keyGen.save(pathAppend, "");
		GenerateSignature genSignKeys = new GenerateSignature();
		genSignKeys.generateSign();
		genSignKeys.saveSignKeys(pathAppend);
		
		String hashedMessage = SHA256Runner.hash("Some sample data");
		System.out.println(hashedMessage);
	}

}
