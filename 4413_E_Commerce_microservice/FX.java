package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonParser;

public class FX extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;
	
	public FX(Socket client)
	{
		this.client = client;
	}
	
	public void run()
	{
		log.printf("Connected to %s:%d\n", client.getInetAddress(), client.getPort());
		// TODO use try with resources
		try
		{
		Scanner in = new Scanner(client.getInputStream());
		PrintStream out = new PrintStream(client.getOutputStream(), true);
		String request = in.nextLine();
		String response;
		if (request.matches("\\d+"))
		{
			URL url = new URL("https://api.exchangeratesapi.io/latest?base=CAD");
			Scanner http = new Scanner(url.openStream());
			String payload = "";
			while (http.hasNextLine()) payload += http.nextLine();
			// response = payload;
			JsonParser parser = new JsonParser();
			payload = parser.parse(payload).getAsJsonObject().get("rates").toString();
			payload = parser.parse(payload).getAsJsonObject().get("USD").toString();
			response = "" + Integer.parseInt(request)*Double.parseDouble(payload);
			
			
		}
		else
		{
			response = "Don't understand: " + request;
		}
		out.println(response);
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
		InetAddress host = InetAddress.getLoopbackAddress() ; 
		
		ServerSocket server = new ServerSocket(port, 0, host);
		
		log.printf("Server listening on %s:%d\n", server.getInetAddress(),server.getLocalPort());
		File file = new File("/cs/home/rd110018/4413/ctrl/FX.txt");
		FileOutputStream f1 = new FileOutputStream(file);
		String data = host.toString() + " " + server.getLocalPort() ;
		
		f1.write(data.getBytes());
		//f1.write(port);
		while(file.exists()) 
		{
			Socket client = server.accept();
			new FX(client).start();
		}
		f1.close();
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
