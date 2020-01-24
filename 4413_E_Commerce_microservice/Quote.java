package service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

public class Quote extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;
	
	public Quote(Socket client)
	{
		this.client = client;
	}
	
	public void run()
	{
		log.printf("Connected to %s:%d\n", client.getInetAddress(), client.getPort());
		String id = "";
		try
		{
		Scanner in = new Scanner(client.getInputStream());
		PrintStream out = new PrintStream(client.getOutputStream(), true);
		String request = in.nextLine();
		String response = "";
		boolean status = false;
		if (request.matches("^[A-Za-z0-9\\_]{8,10}\\s+(json|xml)$"))
		{
			String[] token = request.split("\\s+");
			id = token[0];
			
			String format = token[1];
			String dbURL = "jdbc:sqlite:/cs/home/rd110018/4413/pkg/sqlite/Models_R_US.db";
			Connection connection = DriverManager.getConnection(dbURL);
			
			String query = "select id, name , cost from Product where id = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,  id);
			ResultSet rs = statement.executeQuery();

			
		
			Product bean = new Product();
			if (rs.next())
			{
				status = true;
				bean.setId(rs.getString("id"));

				bean.setName(rs.getString("name"));
				
				bean.setCost(rs.getDouble("cost"));

			}
			else
			{
				String temp =  id  + " not found";
				//String temp = " not found";	
				bean.setId(temp);
				bean.setName("");
				bean.setCost(0.0);
			}
		
			
				if (format.equals("xml"))
			{
				JAXBContext context = JAXBContext.newInstance(Product.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				m.marshal(bean, baos);
				response = baos.toString();
			} else
			{
				Gson gson = new Gson();
				response = gson.toJson(bean);
			}
			
			
		}
		
		out.println(response);
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
		File file = new File("/cs/home/rd110018/4413/ctrl/Quote.txt");
		FileOutputStream f1 = new FileOutputStream(file);
		String data = host.toString() + " " + server.getLocalPort() ;
		//String data = host.toString() + " " + port ;
		f1.write(data.getBytes());
		//f1.write(port); 
			while(file.exists()) 
		{
			Socket client = server.accept();
			new Quote(client).start();
		}
		f1.close();
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
