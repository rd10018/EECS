package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




public class Stateful extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;
	StatefulDb s1 = new StatefulDb();
	static File file = new File("/cs/home/rd110018/4413/ctrl/Geo.txt");

	int r_value ;
	
	
	public Stateful(Socket client)
	{
		this.client = client;
	}
	
	
	
	public void run()
	{
		log.printf("Connected to %s:%d\n", client.getInetAddress(), client.getPort());
		try
		{
		Scanner in = new Scanner(client.getInputStream());
		PrintStream out = new PrintStream(client.getOutputStream(), true);
		String request = in.nextLine();
		String[] token = request.split("\\s+");
		double latitude = Double.parseDouble(token[0]);
		double longitude = Double.parseDouble(token[1]);
		int cookie = Integer.parseInt(token[2]);
		
		if(cookie <= -1) // fresh cookie will be alloted to the client since ha has passed -1 or lesser number as the cookie value
		{
			do
			{
			r_value = (int)(Math.random() * 1000 ) + 1;
			}while(s1.cookie_check(r_value));
			out.println("your cookie is : " + r_value);
			s1.setMap(r_value, latitude, longitude);
			s1.addCookie(r_value);
			}
		
		if(cookie > 0) //cookie will be assigned between 1 and 1000 by the database so eliminating the possibility of 0 to be assigned as cookie
		{
			if(s1.cookie_check(cookie))
			{
				double[] temp1 = s1.getMap().get(cookie);
				double[] temp2 = new double[2];
				temp2[0] = latitude;
				temp2[1] = longitude;
				s1.setMap(cookie, latitude, longitude);
				int value  = 0;
				String answer = "";
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
				double lat1 = temp1[0];
				double long1 = temp1[1];
				double lat2 =  temp2[0];
				double long2 = temp2[1];
				String coordinates = lat1 + " " + long1 + " " + lat2 +  " " + long2;
				
				geo_out.println(coordinates);
				while(geo_in.hasNext())
				{
					out.println( geo_in.nextLine());
				}				
			}
			
			
			else
			{
				// the case where user supplies a positive cookie but that is not provided by the server to it.
			}
		}
		
		}

		catch (Exception e)
		{
			log.println("Error: " + e);
		}
		
		
		try {client.close();} catch (Exception e) {log.print(e);}
		log.printf("Dis-Connected to %s:%d\n", client.getInetAddress(), client.getPort());
		
		
	}

	public static void main(String[] args) throws Exception
	{
		int port = 0;
		//InetAddress host = InetAddress.getLocalHost(); //getLoopBackAddress(); 
		InetAddress host = InetAddress.getLoopbackAddress();
		ServerSocket server = new ServerSocket(port, 0, host);
		log.printf("Server listening on %s:%d\n", server.getInetAddress(),server.getLocalPort());
		while(file.exists())  
		{
			Socket client = server.accept();
			new Stateful(client).start();
		}
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
