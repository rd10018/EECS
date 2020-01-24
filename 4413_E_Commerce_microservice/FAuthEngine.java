package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class FAuthEngine {

private static FAuthEngine fAuthEngine = null;
static File file = new File("F:\\EECS 4413\\ProjectA\\files\\Auth.txt");
	
	private FAuthEngine()
	{
	}
	

	public static FAuthEngine getInstance()
	{
		if (fAuthEngine == null) fAuthEngine = new FAuthEngine();
		return fAuthEngine;
	}
	
	
	public String fAuthCompute(String username, String password) throws Exception
	{
		
		int value  = 0;
		String answer = "";
		String final_answer = "";
		FileInputStream ff = new FileInputStream(file);
		while((value = ff.read()) != -1)
		{
		answer += (char) value;
		}
		String[] user = answer.trim().split("\\s+");
		String[] v1 = user[0].split("/"); 
		InetAddress host =  InetAddress.getByName(v1[1]);
		int port  =  Integer.parseInt(user[1]);
		Socket fauth = new Socket(host, port);
		Scanner fauth_in = new Scanner(fauth.getInputStream());
		PrintStream fauth_out = new PrintStream(fauth.getOutputStream(), true);
		
		String credentials = username + " " + password ;
		
		fauth_out.println(credentials);
		while(fauth_in.hasNext())
		{
			 final_answer += fauth_in.nextLine();
		}
		ff.close();
		fauth.close();
		fauth_in.close();
		return final_answer;
		
		
	}
}
