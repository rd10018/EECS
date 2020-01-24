package service;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//import javax.servlet.http.HttpServletRequest;





public class Gateway extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;
	//StatefulDb s1 = new StatefulDb();
	static File Geo_file = new File("/cs/home/rd110018/4413/ctrl/Geo.txt");
	static File Auth_file = new File("/cs/home/rd110018/4413/ctrl/Auth.txt");
	static File Loc_file = new File("/cs/home/rd110018/4413/ctrl/Loc.txt");
	static File Quote_file = new File("/cs/home/rd110018/4413/ctrl/Quote.txt");
	

	
	
	//pool of  the cookies 
	int r_value ;
	
	
	public Gateway(Socket client)
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
		log.println("This is the request from the client " + request);
		 for (String tmp = in.nextLine(); tmp.length() != 0; tmp = in.nextLine());	
		
		String[] token = request.split("\\s+");
		String[] a1 = token[1].toString().split("\\?");
		String service = a1[0].substring(1, a1[0].length());
		log.println("this is the name of the service  ---> " + service);
		String[] parameters = a1[1].toString().split("\\&");
		
		
		if (service.matches("Geo"))
		{
			String[] temp = parameters[0].split("\\="); 
			String lat1 = temp[1].toString();
			 temp = parameters[1].split("\\=");
			String long1 = temp[1].toString();
			 temp = parameters[2].split("\\=");
			String lat2 = temp[1].toString();
			 temp = parameters[3].split("\\=");
			String long2 = temp[1].toString();
			int value  = 0;
			String answer = "";
			FileInputStream ff = new FileInputStream(Geo_file);
			while((value = ff.read()) != -1)
			{
			answer += (char) value;
			}
			log.println("the value is " + answer );
			String[] user = answer.trim().split("\\s+");
			
			
			String[] v1 = user[0].split("/"); 
			log.println("the string host is :" + v1[1] );
			InetAddress host =  InetAddress.getByName(v1[1]);
			
			int port  =  Integer.parseInt(user[1]);
			Socket geo = new Socket(host, port);
			Scanner geo_in = new Scanner(geo.getInputStream());
			PrintStream geo_out = new PrintStream(geo.getOutputStream(), true);
			
			String coordinates = lat1 + " " + long1 + " " + lat2 +  " " + long2;
			
			
			geo_out.println(coordinates);
			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type: text/plain");
			out.println();
			//geo_out.write(coordinates);
			//log.println("Ths is the final response from the geo server\n");
			while(geo_in.hasNext())
			{
				//out.println("Geo Answer : " + geo_in.nextLine());
				out.println( geo_in.nextLine());
			}
		}
		
		if(service.matches("Auth"))
		{
			String[] temp = parameters[0].split("\\="); 
			String username = temp[1].toString();
			 temp = parameters[1].split("\\=");
			String password = temp[1].toString();
			int value  = 0;
			String answer = "";
			FileInputStream ff = new FileInputStream(Auth_file);
			while((value = ff.read()) != -1)
			{
			answer += (char) value;
			}
			log.println("the value is " + answer );
			String[] user = answer.trim().split("\\s+");
			
			
			String[] v1 = user[0].split("/"); 
			log.println("the string host is :" + v1[1] );
			InetAddress host =  InetAddress.getByName(v1[1]);
			
			int port  =  Integer.parseInt(user[1]);
			Socket auth = new Socket(host, port);
			Scanner auth_in = new Scanner(auth.getInputStream());
			PrintStream auth_out = new PrintStream(auth.getOutputStream(), true);
			
			String credentials = username + " " + password;
			
			
			auth_out.println(credentials);
			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type: text/plain");
			out.println();
			while(auth_in.hasNext())
			{
				
				//out.println("Auth Answer : " + auth_in.nextLine());
				out.println(auth_in.nextLine());
			}
		}
		
		
		if(service.matches("Quote"))
		{
			String[] temp = parameters[0].split("\\="); 
			String id = temp[1].toString();
			 temp = parameters[1].split("\\=");
			String format = temp[1].toString();
			int value  = 0;
			String answer = "";
			FileInputStream ff = new FileInputStream(Quote_file);
			while((value = ff.read()) != -1)
			{
			answer += (char) value;
			}
			log.println("the value is " + answer );
			String[] user = answer.trim().split("\\s+");
			
			
			String[] v1 = user[0].split("/"); 
			log.println("the string host is :" + v1[1] );
			InetAddress host =  InetAddress.getByName(v1[1]);
			
			int port  =  Integer.parseInt(user[1]);
			Socket quote = new Socket(host, port);
			Scanner quote_in = new Scanner(quote.getInputStream());
			PrintStream quote_out = new PrintStream(quote.getOutputStream(), true);
			
			String credentials = id + " " + format;
			
			
			quote_out.println(credentials);
			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type: text/plain");
			out.println();
			while(quote_in.hasNext())
			{
				
				//out.println("Auth Answer : " + auth_in.nextLine());
				out.println(quote_in.nextLine());
			}
		}
				
		if(service.matches("Loc"))
		{
			String[] temp = parameters[0].split("\\="); 
			String address = temp[1].toString();
			int value  = 0;
			String answer = "";
			FileInputStream ff = new FileInputStream(Loc_file);
			while((value = ff.read()) != -1)
			{
			answer += (char) value;
			}
			//log.println("the value is " + answer );
			String[] user = answer.trim().split("\\s+");
			
			
			String[] v1 = user[0].split("/"); 
			//log.println("the string host is :" + v1[1] );
			InetAddress host =  InetAddress.getByName(v1[1]);
			
			int port  =  Integer.parseInt(user[1]);
			Socket loc = new Socket(host, port);
			Scanner loc_in = new Scanner(loc.getInputStream());
			PrintStream loc_out = new PrintStream(loc.getOutputStream(), true);
			
			String credentials = address;
			
			
			loc_out.println(credentials);
			out.println("HTTP/1.1 200 OK");
			out.println("Content-Type: text/plain");
			out.println();
			while(loc_in.hasNext())
			{
				out.println(loc_in.nextLine());
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
		// log.printf("Server listening on %s:%d\n", server.getInetAddress(), server.getLocalPort());
		log.printf("Server listening on %s:%d\n", server.getInetAddress(),server.getLocalPort());


		while(Geo_file.exists() && Auth_file.exists() && Quote_file.exists() && Loc_file.exists() ) // 
		{
			log.println("All of the required Files Exist!!!");
			Socket client = server.accept();
			new Gateway(client).start();
		}
		//f1.close();
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
