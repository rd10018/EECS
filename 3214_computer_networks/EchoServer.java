/* 
 * Name: Rishab Dhamija
 * Student number: 213223334
 * course: EECS 3214 Fall 2018
 * Assignment #: 1
 * Date: 30 september, 2018
 * Source: EECS 3214 Fall 2018 course webpage 
 * Source: https://docs.oracle.com/javase/tutorial/networking/sockets/examples.EchoServer.java

 */
import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        // portNumber variable holds the port number passed as argument from the command line
        int portNumber = Integer.parseInt(args[0]);
        
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
           )
           
        {
         	//i variable holds the integer converted value passed by the client's first out.println() method ie. the integer value
        	int i = Integer.parseInt(in.readLine());
        	//i variable holds the string value passed by the client's second out.println() method
            String inputLine = in.readLine();
            //creating a loop to pass the string's value i number of times to the readLine() of the client for display to the console.
            for(int count = 0; count <i; count++)
            {
            	//sending the string value to the client
            	out.println(inputLine);
                	
            }
                    	
            	
            	
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}