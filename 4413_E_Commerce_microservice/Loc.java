package service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;


public class Loc extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;
	
	public Loc(Socket client)
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
		String response ="";


		String API_key = "AIzaSyDZ13d14cXk3VnljJtKLnMsJaut4JMElu0";
		
		
		
		
		if (!request.isEmpty())
		{
			String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
			
			url +=  request.replace(" ","+") + "&key=" + API_key;
			URL u1 = new URL(url);
			Scanner sin = new Scanner(u1.openConnection().getInputStream());

			while(sin.hasNextLine())
			{
				out.println(sin.nextLine());
			}
	       

		}
		else
		{
			response = "Don't understand: " + request;
		}
		//out.println(response);
		} catch (Exception e)
		{
			log.println("Error: " + e);
		}
		try {client.close();} catch (Exception e) {log.print(e);}
		log.printf("Dis-Connected to %s:%d\n", client.getInetAddress(), client.getPort());

		
	}

	public static void main(String[] args) throws Exception
	{

		int port = 0;
		//InetAddress host = InetAddress.getLocalHost();  
		InetAddress host = InetAddress.getLoopbackAddress();
		ServerSocket server = new ServerSocket(port, 0, host);
		log.printf("Server listening on %s:%d\n", server.getInetAddress(),server.getLocalPort());
		File file = new File("/cs/home/rd110018/4413/ctrl/Loc.txt");
		FileOutputStream f1 = new FileOutputStream(file);
		//String data = host.toString() + " " + port ;
		String data = host.toString() + " " + server.getLocalPort() ;
		f1.write(data.getBytes());
		//f1.write(port);
			while(file.exists()) 
		{
			Socket client = server.accept();
			new Loc(client).start();
		}
		f1.close();
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
