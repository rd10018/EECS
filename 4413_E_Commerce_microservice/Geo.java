package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Geo extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;
	
	public Geo(Socket client)
	{
		this.client = client;
	}
	
	// function to convert each of the four values into radians.
	public double degree2radian(double degree)
	{
		return degree * (Math.PI /180);
	}
	
	public void run()
	{
		log.printf("Connected to %s:%d\n", client.getInetAddress(), client.getPort());
		try
		{
		Scanner in = new Scanner(client.getInputStream());
		PrintStream out = new PrintStream(client.getOutputStream(), true);
		String request = in.nextLine();
		log.println(request);
		String[] token = request.split("\\s+"); 
		String response;	
		double t1, n1, t2, n2;
		t1 = Double.parseDouble(token[0]);
		n1 = Double.parseDouble(token[1]);
		t2 = Double.parseDouble(token[2]);
		n2 = Double.parseDouble(token[3]);
		log.printf("here are the coordiantes entered by the client -> %.5f:%.5f , %.5f:%.5f\n", t1, n1, t2, n2);
		
		//converting each of those values into radians and storing them into array
		double[] converted = new double[4];
		converted[0] = degree2radian(t1);
		converted[1] = degree2radian(n1);
		converted[2] = degree2radian(t2);
		converted[3] = degree2radian(n2);
		
		
		//placing values back into their original terms
		t1 =converted[0];
		n1 =converted[1];
		t2 =converted[2];
		n2 =converted[3];
		
		
		//performing calculations 
		double Y = Math.cos(t1) * Math.acos(t2);
		double temp1 = Math.pow(Math.sin((t2-t1)/2), 2);
		double temp2 = Y * Math.pow(Math.sin((n2-n1)/2), 2);
		double X = temp1 + temp2;
		double result = 12742 * Math.atan2(Math.sqrt(X), Math.sqrt(1 - X));
		response = "" + result;
		out.println("The distance between the two points is: -> " +  response);
		log.println(response);
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
		InetAddress host = InetAddress.getLocalHost();
		//InetAddress host = InetAddress.getLoopbackAddress() ; 
		
		ServerSocket server = new ServerSocket(port, 0, host);
		
		log.printf("Server listening on %s:%d\n", server.getInetAddress(),server.getLocalPort());
		File file = new File("/cs/home/rd110018/4413/ctrl/Geo.txt");
		FileOutputStream f1 = new FileOutputStream(file);
		String data = host.toString() + " " + server.getLocalPort() ;
		
		f1.write(data.getBytes());
		//f1.write(port);
		while(file.exists()) 
		{
			Socket client = server.accept();
			new Geo(client).start();
		}
		f1.close();
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
