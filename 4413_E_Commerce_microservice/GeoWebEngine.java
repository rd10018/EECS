package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class GeoWebEngine {

private static GeoWebEngine geoWebEngine = null;
static File file = new File("F:\\EECS 4413\\ProjectA\\files\\Geo.txt");
	
	private GeoWebEngine()
	{
	}
	

	public static GeoWebEngine getInstance()
	{
		if (geoWebEngine == null) geoWebEngine = new GeoWebEngine();
		return geoWebEngine;
	}
	
	
	public String geoCompute(String lat1, String lon1 , String lat2, String lon2) throws Exception
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
		Socket geo = new Socket(host, port);
		Scanner geo_in = new Scanner(geo.getInputStream());
		PrintStream geo_out = new PrintStream(geo.getOutputStream(), true);
		
		String coordinates = lat1 + " " + lon1 + " " + lat2 +  " " + lon2;
		
		geo_out.println(coordinates);
		while(geo_in.hasNext())
		{
			 final_answer += geo_in.nextLine();
		}
		ff.close();
		geo.close();
		geo_in.close();
		return final_answer;
		
		
	}
}
