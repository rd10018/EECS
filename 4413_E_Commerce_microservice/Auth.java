package service;


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

import g.Util;

public class Auth extends Thread
{
	public static PrintStream log = System.out;
	private Socket client;

	
	public Auth(Socket client)
	{
		this.client = client;
	}
	

	public void run()
	{
		String response = "FAILURE"; //default value
		
		log.printf("Connected to %s:%d\n", client.getInetAddress(), client.getPort());
		try
		{
		Scanner in = new Scanner(client.getInputStream());
		PrintStream out = new PrintStream(client.getOutputStream(), true);
		String request = in.nextLine();
		
		
		String hash_computed = "";
		Boolean compute =  false;
		if (request.matches("^+[A-Za-z0-9]+\\@+[A-Za-z0-9]+\\.+[A-Za-z0-9]{2,6}+\\s+[A-Za-z0-9]{2,11}$"))
		{
			String[] credentials =  request.split("\\s");
			String username = credentials[0];
			String password =  credentials[1];

			log.printf("The credentials supplied by the client are: %s : %s\n", username, password );
			
			String dbURL = "jdbc:sqlite:/cs/home/rd110018/4413/pkg/sqlite/Models_R_US.db";
			Connection connection = DriverManager.getConnection(dbURL);
			
			String query = "select * from Client where name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,  username);
			ResultSet rs = statement.executeQuery();

			AuthBean bean = new AuthBean();
			while (rs.next())
			{
				//out.println("value of rs.next() is true after all");
				compute = true;
				bean.setName(rs.getString("name"));
				bean.setHash(rs.getString("hash"));
				bean.setCount(rs.getInt("count"));
				bean.setSalt(rs.getString("salt"));
				bean.setPostalCode(rs.getString("postalCode"));
				bean.setProvince(rs.getString("province"));
				bean.setCity(rs.getString("city"));
				bean.setStreet(rs.getString("street"));
				bean.setId(rs.getInt("id"));
				
			}
			
			//username.equals(bean.getName())
			if(! (bean.getName() == null) )	
			{	
				//log.println("entry not present in the database");
				hash_computed = Util.hash(password, bean.getSalt(), bean.getCount());
				log.println("the computed hash is : " + hash_computed);
			if (hash_computed.equals( bean.getHash()))
			{
				response = "OK";
				
			}
			}	
//			String format = "json";
//			if (format.equals("xml"))
//			{
//				JAXBContext context = JAXBContext.newInstance(TaxBean.class);
//				Marshaller m = context.createMarshaller();
//				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				m.marshal(bean, baos);
//				response = baos.toString();
//			} else
//			{
//				Gson gson = new Gson();
//				response = gson.toJson(bean);
//			}
		}
//		else
//		{
//			response = "FAILURE";
//		}
		out.println(response);
		}
		 catch (Exception e)
		{
			log.println("Error: " + e);
		}
		try {client.close();
	
		} catch (Exception e) {log.print(e);}
		
		log.printf("Dis-Connected to %s:%d\n", client.getInetAddress(), client.getPort());

		
	}

	public static void main(String[] args) throws Exception
	{
		
		
		int port = 0;
		//InetAddress host = InetAddress.getLocalHost(); //getLoopBackAddress(); 
		InetAddress host = InetAddress.getLoopbackAddress();
		ServerSocket server = new ServerSocket(port, 0, host);
		log.printf("Server listening on %s:%d\n", server.getInetAddress(),server.getLocalPort());
		File file = new File("/cs/home/rd110018/4413/ctrl/Auth.txt");
		FileOutputStream f1 = new FileOutputStream(file);
		//String data = host.toString() + " " + port ;
		String data = host.toString() + " " + server.getLocalPort() ;
		f1.write(data.getBytes());
		//f1.write(port);
			while(file.exists()) 
		{
			Socket client = server.accept();
			new Auth(client).start();
		}
		f1.close();
		server.close();
		// TODO log server shutdown
		log.printf("Server on %s:%d has shut down!!\n", server.getInetAddress(),server.getLocalPort());
	}

}
